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
	
		return url.replaceAll(URL_REGEX_PREFIX, "")
				.replaceAll(URL_REGEX_SUFFIX, "");
	}

	public List<String> pageStackUrlNames() {
	
		return pageStack.stream()
				.map(BreadcrumbBean::urlToName)
				.collect(Collectors.toList());
	}

	public String pageName(String page) {
	
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
	
		if (navigationCase.contains("error"))
			return;

	
		if (navigationCase.contains("index")) {
			pageStack.clear();
			return;
		}

	
		if (stackSize >= MAX_STACK_SIZE) {
			pageStack.removeLast();
			stackSize = pageStack.size();
		}

	
		if (stackSize == 0) {
			pageStack.add(navigationCase);
			return;
		}

	
	
		if (stackSize > 1 && pageStack.get(stackSize - 2)
				.equals(navigationCase)) {
			pageStack.remove(stackSize - 1);
			return;
		}

	
	
		if (pageStack.get(stackSize - 1)
				.equals(navigationCase)) {
			return;
		}

	
		if (stackSize >= 1)
			pageStack.add(navigationCase);
	}
}
