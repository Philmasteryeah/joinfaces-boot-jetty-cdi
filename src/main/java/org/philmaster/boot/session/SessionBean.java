package org.philmaster.boot.session;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

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
	private String page, name;

	// hold client and account in session
	private Client client;
	private Account account;

	// session context is only for client and account
	// every context page has its own context
	private ObjectContext sessionContext;

	@Getter
	@Inject
	private DatabaseService db;

// 	@PostConstruct dont works here

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		String clientname = null;
		// TODO get the client parameter from request
		// not working like this
//		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//		clientname = params.get("client");

		String username = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();

		System.err.println("client-> " + clientname);
		System.err.println(event.getGeneratedBy());
		System.err.println("user-> " + username);
		System.err.println(event.getSource());

		initSession(clientname, username);
	}

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

	private void initSession(String clientname, String username) {
		sessionContext = db.newContext();
		if (clientname == null)
			clientname = "default"; // TODO use static
		client = DatabaseService.fetchClientByName(sessionContext, clientname);
		account = DatabaseService.fetchAccountByUsername(sessionContext, username);
	}

	public Client getClient(@Nonnull ObjectContext context) {
		return context.localObject(client);
	}

	public Account getAccount(@Nonnull ObjectContext context) {
		return context.localObject(account);
	}

	public String getUsername() {
		return account.getUsername();
	}
}
