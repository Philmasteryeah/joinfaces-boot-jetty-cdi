package org.philmaster.boot.session;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.Getter;
import lombok.NonNull;

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

//	@Autowired
//	private HttpServletRequest request;

	@Getter
	private String page, name;

	// hold client and account in session
	private Client client;
	private Account account;

	// session context is only for client and account
	// every context page has its own context
	// private static ObjectContext sessionContext =
	// BaseContext.getThreadObjectContext();
	private ObjectContext context;

	@Getter
	@Inject
	private DatabaseService db;

// 	@PostConstruct dont works here

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
// 		alternative to autowired
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		System.err.println(request);

		String clientname = request.getParameter("client");
		String username = ((UserDetails) event.getAuthentication()
				.getPrincipal()).getUsername();
		// TODO handle the double checked login
		// this should never be false
		// otherwise the sql in spring security is not correct
		boolean isLoggedIn = isInitSession(clientname, username);
		if (!isLoggedIn) {
			System.err.print("error logout");
			logout();
		}
	}

	public String pageNameReadable() {
		// TODO
		return page;
	}

	public String changeLanguage(String locale) {
		this.locale = new Locale(locale);
		FacesContext.getCurrentInstance()
				.getViewRoot()
				.setLocale(this.locale);
		return locale;

	}

	public String logout() {
		// instance can be null here
		// TODO redirect not working
		// clear faces session
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		ec.invalidateSession();

		// clear spring session
		SecurityContextHolder.clearContext();
		String url = "/index.xhtml?faces-redirect=true";
		try {
			ec.redirect(url);
		} catch (IOException e) {
			System.err.println("couldnt redirect");
			e.printStackTrace();
		}
		return url;
	}

	private boolean isInitSession(String clientname, String username) {
		context = db.newContext();
		client = DatabaseService.fetchClientByName(context, clientname);
		account = DatabaseService.fetchAccountByUsername(context, username, client.getName());
		if (account == null)
			return false; // LOGGING no accc
		if (client == null)
			return false; // LOGGING no client

		System.err.println("init session client-> " + client.getName());
		System.err.println("init session user-> " + account.getUsername());

		return true;
	}

	public Account fetchAccountByUsername(String username) {
		System.err.println("fetchAccountByUsername " + username);
		return ObjectSelect.query(Account.class)
				.where(Account.USERNAME.eq(username))
				.selectOne(context);
		// TODO client
	}

	public Client getClient(@NonNull ObjectContext context) {
		return context.localObject(client);
	}

	public Account getAccount(@NonNull ObjectContext context) {
		return context.localObject(account);
	}

	public String getUsername() {
		return account != null ? account.getUsername() : null;
	}
}
