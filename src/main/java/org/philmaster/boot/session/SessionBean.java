package org.philmaster.boot.session;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Specializes;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;

import com.github.adminfaces.template.session.AdminSession;

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
 *         getPage() will return the name of the current page pageNameReadable()
 *         user readable printed for info messages
 *
 */

@Getter
@Setter
@Named
@SessionScoped
@Specializes
public class SessionBean extends AdminSession
		implements Serializable, ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	private static final long serialVersionUID = 1L;

	private Locale locale;
	private String page, name, password;
	private String username;

// 	@PostConstruct dont works here

	public String pageNameReadable() {
		// TODO
		return page;
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

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		// TODO Auto-generated method stub
		UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
		username = userDetails.getUsername();
		System.err.println("user-> " + username);
	}
}
