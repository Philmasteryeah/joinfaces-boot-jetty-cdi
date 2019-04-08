package org.philmaster.boot.session;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
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
	@Setter
	private String layoutSkin;

	@Getter
	@Setter
	private String layoutSkinSelected;

	@Getter
	private List<String> layoutSkins = Arrays.asList("skin-blue", "skin-blue-light", "skin-yellow", "skin-yellow-light",
			"skin-green", "skin-green-light", "skin-purple", "skin-purple-light", "skin-red", "skin-red-light",
			"skin-black", "skin-black-light");

//	@Autowired
//	private HttpServletRequest request;

	@Getter
	private String page, name;

	// hold client and account in session
	// getter returns the session client
	@Getter
	private Client client;
	@Getter
	private Account account;

	// session context is only for client and account
	// every context page has its own context
	//
	// private static ObjectContext sessionContext =
	// BaseContext.getThreadObjectContext();
	private ObjectContext sessionContext;

	@Getter
	@Inject
	private DatabaseService db;

// 	@PostConstruct dont works here

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		System.err.println(request);
		// client from parameter
		String clientname = request.getParameter("client");
		System.err.println("client connected " + clientname);
		String username = ((UserDetails) event.getAuthentication()
				.getPrincipal()).getUsername();
		System.err.println("user connected " + username);
		// this should never be false
		// otherwise the sql in spring security is not correct
		boolean isLoggedIn = isInitSession(clientname, username);
		if (!isLoggedIn) {
			System.err.print("error could not login");
			logout();
			return;
		}
		// logged in now

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
//		ExternalContext ec = FacesContext.getCurrentInstance()
//				.getExternalContext();
//		ec.invalidateSession();

		// clear spring session
		SecurityContextHolder.clearContext();
		return "/index.xhtml?faces-redirect=true";

	}

	private boolean isInitSession(String clientname, String username) {
		if (sessionContext == null)
			sessionContext = db.newContext();
		client = DatabaseService.fetchClientByName(sessionContext, clientname);
		if (client == null)
			return false; // LOGGING no client
		account = DatabaseService.fetchAccountByUsername(sessionContext, username, client.getName());
		if (account == null)
			return false; // LOGGING no accc

		System.err.println("init session client-> " + client.getName());
		System.err.println("init session user-> " + account.getUsername());

		String skin = client.getSkin();
		System.err.println(skin + " skin loaded from client");
		// setLayoutSkin(skin);
		return true;
	}

	public Account fetchAccountByUsername(String username) {
		System.err.println("fetchAccountByUsername " + username);
		return ObjectSelect.query(Account.class)
				.where(Account.USERNAME.eq(username))
				.selectOne(sessionContext);
		// TODO client
	}

	public Client getLocalClient(@NonNull ObjectContext context) {
		return context != null ? context.localObject(client) : null;
	}

	public Account getLocalAccount(@NonNull ObjectContext context) {
		return context != null ? context.localObject(account) : null;
	}

	public String getUsername() {
		return account != null ? account.getUsername() : null;
	}
}
