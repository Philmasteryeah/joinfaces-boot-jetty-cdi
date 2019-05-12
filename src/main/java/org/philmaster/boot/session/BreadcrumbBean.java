package org.philmaster.boot.session;

import java.io.Serializable;
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

	private static final Integer MAX_STACK_SIZE = 5;
	private static final String URL_REGEX_PREFIX = ".*/";
	private static final String URL_REGEX_SUFFIX = "\\..*";

	@Getter
	@Setter
	private LinkedList<String> pageStack = new LinkedList<>();

	public void addPageUrl() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
			HttpServletRequest servletRequest = (HttpServletRequest) facesContext.getExternalContext()
					.getRequest();

			updatePageStack(servletRequest.getRequestURI());
		}
	}

	private static String urlToName(String url) {
		// /etc/index.xhtml -> index
		return url.replaceAll(URL_REGEX_PREFIX, "")
				.replaceAll(URL_REGEX_SUFFIX, "");
	}

	public void execute(String page) {
		System.err.println(page + " - ");
	}

	public List<String> pageStackUrlNames() {
		// History
		return pageStack.stream()
				.map(BreadcrumbBean::urlToName)
				.collect(Collectors.toList());
	}

	public String pageName(String page) {
		// TODO naming clientDetail -> Client, accountList -> Accounts from properties
		return page;
	}

	public String getBackUrl() {
		int stackSize = pageStack.size();
		if (stackSize == 0)
			return null;
		return (stackSize > 1) ? pageStack.get(stackSize - 2) : pageStack.get(stackSize - 1);
	}

	public boolean hasPageBack() {
		return pageStack.size() > 1;
	}

	private void updatePageStack(String navigationCase) {
		if (navigationCase == null)
			return;

		int stackSize = pageStack.size();
		// skip error
		if (navigationCase.contains("error"))
			return;

		// clear on index
		if (navigationCase.contains("index")) {
			pageStack.clear();
			return;
		}

		// If stack is full, then make room by removing the oldest item
		if (stackSize >= MAX_STACK_SIZE) {
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
		if (stackSize >= 1)
			pageStack.add(navigationCase);
	}
}
