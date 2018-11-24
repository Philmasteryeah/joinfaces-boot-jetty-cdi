package org.philmaster.boot.session;

import java.io.Serializable;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
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

	private static Integer maxHistoryStackSize = 20;

	@Getter
	@Setter
	private Stack<String> pageStack = new Stack<>();

	public void addPageUrl() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (!facesContext.isPostback() && !facesContext.isValidationFailed()) {
			HttpServletRequest servletRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
			String url = servletRequest.getRequestURL().toString();
			String query = servletRequest.getQueryString();
			String fullUrl = query != null && !query.trim().isEmpty() ? url + "?" + query : url;
			updatePageStack(fullUrl);
		}
	}

	public String allPageStackUrls() {
		// History
		return pageStack.stream().map(s -> s.replaceAll("^.+\\/", "").replaceAll("[.].+$", ""))
				.collect(Collectors.joining(" > "));
	}

	public String getBackUrl() {
		Integer stackSize = pageStack.size();
		if (stackSize > 1) {
			return pageStack.get(stackSize - 2);
		}
		// Just in case hasPageBack was not implemented (be safe)
		return pageStack.get(stackSize - 1);
	}

	public boolean hasPageBack() {
		return pageStack.size() > 1;
	}

	private void updatePageStack(String navigationCase) {

		Integer stackSize = pageStack.size();

		// If stack is full, then make room by removing the oldest item
		if (stackSize >= maxHistoryStackSize) {
			pageStack.remove(0);
			stackSize = pageStack.size();
		}

		// If the first page visiting, add to stack
		if (stackSize == 0) {
			pageStack.push(navigationCase);
			return;
		}

		// If it appears the back button has been pressed, in other words:
		// If the A -> B -> C, and user navigates from C -> B, then remove C
		if (stackSize > 1 && pageStack.get(stackSize - 2).equals(navigationCase)) {
			pageStack.remove(stackSize - 1);
			return;
		}

		// If we are on the same page
		// If A == A then ignore
		if (pageStack.get(stackSize - 1).equals(navigationCase)) {
			return;
		}

		// In a normal case, we add the item to the stack
		if (stackSize >= 1) {
			pageStack.push(navigationCase);
			return;
		}

	}
}
