package org.philmaster.boot.session;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Philmasteryeah
 * 
 *         switch the content pages with this bean
 * 
 *         its very important to use SessionScope here otherwise it will be
 *         reset to 'main 'after menu click
 * 
 *         getPage() will return the name of the current page
 *         pageNameReadable() user readable printed for info messages
 *
 */

@Getter
@Setter
@Named
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Locale locale;
	private String page;

	@PostConstruct
	void init() {
		locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
		page = "main";
	}

	public String pageNameReadable() {
		return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(StringUtils.capitalize(page)), ' ');
	}

	public String changeLanguage(String locale) {
		this.locale = new Locale(locale);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
		return locale;

	}

	public String logout() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}

}
