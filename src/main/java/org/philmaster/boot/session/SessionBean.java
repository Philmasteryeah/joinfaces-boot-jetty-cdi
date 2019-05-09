package org.philmaster.boot.session;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.di.BeforeScopeEnd;
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
	// getter returns the session client
	@Getter
	private Client client;
	@Getter
	private Account account;

	// session context is only for client and account
	// every context page has its own context
	private ObjectContext sessionContext = DatabaseService.INSTANCE.newContext();

//	@PostConstruct
//	private void init() {
//		System.err.println(sessionContext);
//
//		// BaseContext.bindThreadObjectContext(sessionContext);
//		System.err.println("--------@PostConstruct Session---------");
//		System.err.println(sessionContext);
//	}
//
//	@PreDestroy
//	private void destroy() {
//		System.err.println(sessionContext.getGraphManager());
//		System.err.println("--------@PreDestroy  Session---------");
//		System.err.println(sessionContext);
//	}

	public void onRequest(ComponentSystemEvent event) {
		//System.err.println("req" + event);
		// TODO get every request for testing

//		FacesContext fc = FacesContext.getCurrentInstance();
//		System.err.println(fc.getExternalContext()
//				.getRequestParameterMap() + " ");

//		ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication()
//				.getNavigationHandler();
//		nav.performNavigation("includes/error");

//		if (!"admin".equals(fc.getExternalContext()
//				.getSessionMap()
//				.get("role"))) {
//			nav.performNavigation("error");
//		}
	}

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		System.err.println(event + " onEvent " + event.getAuthentication());
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
		// clear faces session
		// clear spring session
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		System.err.println(session.getServletContext()
				.getSessionCookieConfig());

		session.invalidate();
		SecurityContextHolder.clearContext();
		FacesContext.getCurrentInstance()
				.getExternalContext()
				.invalidateSession();
		return "/index.xhtml?faces-redirect=true";

	}

	private boolean isInitSession(String clientname, String username) {
		if (clientname == null || clientname.trim()
				.isEmpty())
			System.err.println("using default client");
		client = DatabaseService.fetchClientByName(sessionContext, clientname);
		if (client == null)
			return false; // LOGGING no client
		account = DatabaseService.fetchAccountByUsername(sessionContext, username, client);
		if (account == null)
			return false; // LOGGING no accc

		System.err.println("init session client-> " + client.getName());
		System.err.println("init session user-> " + account.getUsername());

		//String skin = client.getSkin();
		//System.err.println(skin + " skin loaded from client");
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

	public Client getLocalClient(ObjectContext context) {
		return context != null ? context.localObject(client) : null;
	}

	public Account getLocalAccount(ObjectContext context) {
		return context != null ? context.localObject(account) : null;
	}

	public String getUsername() {
		return account != null ? account.getUsername() : null;
	}
}
