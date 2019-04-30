package org.philmaster.boot.session;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class BreadcrumbBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Integer maxHistoryStackSize = 20;

	private static final String URL_REGEX_PREFIX = ".*\\";
	private static final String URL_REGEX_SUFFIX = "\\..*";

	@Getter
	@Setter
	private LinkedList<String> pageStack = new LinkedList<>();

	public void addPageUrl() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
			HttpServletRequest servletRequest = (HttpServletRequest) facesContext.getExternalContext()
					.getRequest();

			String uri = servletRequest.getRequestURI();

			updatePageStack(uri);
		}
	}

	public List<String> pageStackLabels() {
		return Arrays.asList(allPageStackUrls().split(" > "));
	}

	private String urlToName(String url) {
		// /etc/index.xhtml -> index
		return url.replaceAll(URL_REGEX_PREFIX, "")
				.replaceAll(URL_REGEX_SUFFIX, "");
	}

	public String allPageStackUrls() {
		// History
		return pageStack.stream()
				.map(this::urlToName)
				.collect(Collectors.joining(" > "));
	}

	public String getBackUrl() {
		Integer stackSize = pageStack.size();
		if (stackSize == 0)
			return null;
		if (stackSize > 1)
			return pageStack.get(stackSize - 2);

		return pageStack.get(stackSize - 1);
	}

	public boolean hasPageBack() {
		return pageStack.size() > 1;
	}

	private void updatePageStack(String navigationCase) {
		if (navigationCase == null)
			return;

		Integer stackSize = pageStack.size();
		// Special
		// TODO empty stack on menu click

		// If stack is full, then make room by removing the oldest item
		if (stackSize >= maxHistoryStackSize) {
			pageStack.removeLast();
			stackSize = pageStack.size();
		}

		// If the first page visiting, add to stack
		if (stackSize == 0) {
			pageStack.add(navigationCase);
			return;
		}

		// If it appears the back button has been pressed, in other words:
		// If the A -> B -> C, and user navigates from C -> B, then remove C
		if (stackSize > 1 && pageStack.get(stackSize - 2)
				.equals(navigationCase)) {
			pageStack.remove(stackSize - 1);
			return;
		}

		// If we are on the same page
		// If A == A then ignore
		if (pageStack.get(stackSize - 1)
				.equals(navigationCase)) {
			return;
		}

		// In a normal case, we add the item to the stack
		if (stackSize >= 1) {
			pageStack.add(navigationCase);
		}

	}
}
