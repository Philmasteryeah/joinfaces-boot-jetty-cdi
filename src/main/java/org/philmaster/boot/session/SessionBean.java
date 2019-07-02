package org.philmaster.boot.session;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Philmasteryeah
 * 
 *         do not inject this bean into a other session scope or view sope, it
 *         will copy the session
 *
 */

@Named
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	private Locale locale;

	@Getter
	@Setter
	private String page, name, clientname;

	@Getter
	private Client client;

	@Getter
	private Account account;

	@PostConstruct
	void init() {
		System.err.println("session created");
		clientname = getClientNameFromSession();
	}

	public String getClientNameFromSession() {
		return String.valueOf(getHttpSession().getAttribute("client"));
	}

	public HttpSession getHttpSession() {
		ServletRequestAttributes sra = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
		HttpServletRequest request = sra.getRequest();
		return request.getSession();
	}

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

	public String getUsernameFromAuth() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		return principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : null;
	}

	public String logout() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		getHttpSession().invalidate();
		SecurityContextHolder.clearContext();
		externalContext.invalidateSession();
		return "/index.xhtml?faces-redirect=true";

	}

	public Client getLocalClient(ObjectContext context) {
		// TODO add clientname
		if (client == null)
			client = DatabaseService.fetchClientByName(context, null);
		ObjectContext objectContext = client.getObjectContext();
		if (objectContext != null && objectContext.equals(context))
			return client;
		return context.localObject(client);
	}

	public Account getLocalAccount(ObjectContext context) {
		if (account == null)
			account = DatabaseService.fetchAccountByUsername(context, getUsernameFromAuth(), getLocalClient(context));
		if (account != null)
			account.setObjectContext(context);
		return account;
	}

}
