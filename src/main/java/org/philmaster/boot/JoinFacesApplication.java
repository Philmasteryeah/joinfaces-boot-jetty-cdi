package org.philmaster.boot;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

@SpringBootApplication
public class JoinFacesApplication implements ServletContextInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JoinFacesApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.setInitParameter("primefaces.THEME", "admin");
	}

}
