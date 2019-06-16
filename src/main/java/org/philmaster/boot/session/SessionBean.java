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
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.Getter;

/**
 * @author Philmasteryeah
 * 
 *         do not inject this bean into a other session scope or view sope, it
 *         will copy the session
 *
 */

@Named
@SessionScoped
public class SessionBean implements ApplicationListener<InteractiveAuthenticationSuccessEvent>, Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	private Locale locale;

	@Getter
	private String page, name;

	@Getter
	private Client client;

	@Getter
	private Account account;

	@Getter
	private Authentication auth;

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

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		System.err.println(event + " onEvent " + event.getAuthentication());
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		System.err.println(request);

		String clientname = request.getParameter("client");
		System.err.println("client connected " + clientname);
		auth = event.getAuthentication();
		String username = ((UserDetails) auth.getPrincipal()).getUsername();
		System.err.println("user connected " + username);

		boolean isLoggedIn = isInitSession(clientname, username);
		if (!isLoggedIn) {
			System.err.print("error could not login");
			logout();
			return;
		}

		System.err.println("logged in");

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

		if (context == null)
			context = DatabaseService.getContext();

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

	public boolean isInitSession(String clientname, String username) {
		return isInitSession(null, clientname, username);
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
