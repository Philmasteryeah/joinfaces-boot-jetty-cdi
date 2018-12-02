package org.philmaster.boot.session;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired
	private HttpServletRequest request;

	@Getter
	private String page, name;

	// hold client and account in session
	private Client client;
	private Account account;

	// session context is only for client and account
	// every context page has its own context
	private ObjectContext context;

	@Getter
	@Inject
	private DatabaseService db;

// 	@PostConstruct dont works here

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
// 		alternative to autowired
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//				.getRequest();
		String clientname = request.getParameter("client");
		String username = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
		// TODO handle the double checked login
		// this should never be false
		// otherwise the sql in spring security is not correct
		boolean isLoggedIn = isInitSession(clientname, username);
		if (!isLoggedIn) {
			System.err.print("error logout");
		}
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
		// clear faces session
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		// clear spring session
		SecurityContextHolder.clearContext();
		return "/index.xhtml?faces-redirect=true";
	}

	private boolean isInitSession(String clientname, String username) {
		// TODO Logging
//		System.err.println("client-> " + clientname);
//		System.err.println("user-> " + username);
		if (clientname == null || username == null)
			return false;
		context = db.newContext();
		if (context == null)
			return false;
		client = DatabaseService.fetchClientByName(context, clientname);
		account = DatabaseService.fetchAccountByUsername(context, username);
		if (account == null || client == null)
			return false;
		return true;
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
