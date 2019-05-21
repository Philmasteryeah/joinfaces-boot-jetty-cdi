package org.philmaster.boot.session;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.cayenne.ObjectContext;
import org.philmaster.boot.model.Account;
import org.philmaster.boot.model.Client;
import org.philmaster.boot.service.DatabaseService;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	private Locale locale;

	@Getter
	private String page, name;

	@Getter
	private Client client;

	@Getter
	private Account account;


//	@PostConstruct
//	private void init() {
//		System.err.println(sessionContext);
//
//	
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

//	public void onRequest(ComponentSystemEvent event) {
//	
//	
//
//	
//	
//
////		FacesContext fc = FacesContext.getCurrentInstance();
////		System.err.println(fc.getExternalContext()
////				.getRequestParameterMap() + " ");
//
////		ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication()
////				.getNavigationHandler();
////		nav.performNavigation("includes/error");
//
////		if (!"admin".equals(fc.getExternalContext()
////				.getSessionMap()
////				.get("role"))) {
////			nav.performNavigation("error");
////		}
//	}

//	@Override
//	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
//
//		System.err.println(event + " onEvent " + event.getAuthentication());
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
//				.getRequest();
//		System.err.println(request);
//	
//		String clientname = request.getParameter("client");
//		System.err.println("client connected " + clientname);
//		String username = ((UserDetails) event.getAuthentication()
//				.getPrincipal()).getUsername();
//		System.err.println("user connected " + username);
//	
//	
//		boolean isLoggedIn = isInitSession(clientname, username);
//		if (!isLoggedIn) {
//			System.err.print("error could not login");
//			logout();
//			return;
//		}
//	
//		System.err.println("logged in");
//
//	}

	public String pageNameReadable() {

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

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		System.err.println(session.getServletContext()
				.getSessionCookieConfig());

		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		System.err.println("session" + externalContext.getSessionMap());
		System.err.println("req" + externalContext.getRequestMap());
		System.err.println("user" + externalContext.getRemoteUser());

		session.invalidate();
		SecurityContextHolder.clearContext();

		externalContext.invalidateSession();
		return "/index.xhtml?faces-redirect=true";

	}

	public boolean isInitSession(ObjectContext context, String clientname, String username) {
		if (clientname == null || clientname.trim()
				.isEmpty())
			System.err.println("using default client");

		client = DatabaseService.fetchClientByName(context, clientname);
		if (client == null)
			return false; // LOGGING no client
		account = DatabaseService.fetchAccountByUsername(context, username, client);
		if (account == null)
			return false; // LOGGING no accc

		System.err.println("init session client-> " + client.getName());
		System.err.println("init session user-> " + account.getUsername());

		return true;
	}

//	public Account fetchAccountByUsername(String username) {
//		System.err.println("fetchAccountByUsername " + username);
//		return ObjectSelect.query(Account.class)
//				.where(Account.USERNAME.eq(username))
//				.selectOne(sessionContext);
//	
//	}

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
