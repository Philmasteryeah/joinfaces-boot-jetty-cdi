package org.philmaster.boot.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class MyApplicationListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		// TODO get client out of param
		ServletRequestAttributes sra = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
		HttpServletRequest request = sra.getRequest();

		System.err.println("req " + request.getRequestURI());

		String clientname = request.getParameter("client");
		System.err.println("client connected " + clientname);

		HttpSession session = request.getSession();
		session.setAttribute("client", clientname);

	}
}
