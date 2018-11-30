package org.philmaster.boot.session;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;

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

@Named
@SessionScoped
public class SessionBean implements Serializable, ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	private static final long serialVersionUID = 1L;

	private Locale locale;

	@Getter
	private String page, name, username;

	private Client client;

	@Inject
	private DatabaseService db;

	private ObjectContext sessionContext;

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

		System.err.println(event.getGeneratedBy());

		System.err.println("user-> " + username);

		System.err.println(event.getSource());
		sessionContext = db.newContext();
		client = DatabaseService.fetchClient(sessionContext);
		// TODO client setzen
	}

	public Client getClient(ObjectContext context) {
		return context.localObject(client);
	}
}
