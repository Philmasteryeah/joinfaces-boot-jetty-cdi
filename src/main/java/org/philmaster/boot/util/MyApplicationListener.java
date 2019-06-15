package org.philmaster.boot.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.philmaster.boot.session.SessionBean;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Named
@RequestScoped
public class MyApplicationListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	@Inject
	private SessionBean session;

	// private ObjectContext ctx = DatabaseService.newContext();

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {

		System.err.println(event + " onEvent " + event.getAuthentication());
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		System.err.println(request);

		String clientname = request.getParameter("client");
		System.err.println("client connected " + clientname);
		String username = ((UserDetails) event.getAuthentication()
				.getPrincipal()).getUsername();
		System.err.println("user connected " + username);

		boolean isLoggedIn = session.isInitSession(clientname, username);
		if (!isLoggedIn) {
			System.err.print("error could not login");
			session.logout();
			return;
		}

		System.err.println("logged in");

	}

}