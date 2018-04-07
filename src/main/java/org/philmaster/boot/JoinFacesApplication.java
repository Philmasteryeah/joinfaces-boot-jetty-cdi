package org.philmaster.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootApplication
public class JoinFacesApplication implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	public static void main(String[] args) {
		SpringApplication.run(JoinFacesApplication.class, args);
	}

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		// onLogin
		UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
		System.err.println(userDetails + " ");
	}

}
