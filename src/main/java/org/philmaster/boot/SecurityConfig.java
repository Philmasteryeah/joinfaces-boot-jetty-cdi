package org.philmaster.boot;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.philmaster.boot.service.DatabaseService;
import org.philmaster.boot.session.SessionBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
		implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	private static final Logger LOGGER = LogManager.getLogger();

	@Inject
	private DatabaseService db;

	@Inject
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// db login with encrypted pw and inMemory for testing
		auth.jdbcAuthentication().dataSource(db.getDataSource()).passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("SELECT username, password, enabled FROM account WHERE username=?")
				.authoritiesByUsernameQuery("SELECT username, 'ADMIN' FROM account WHERE username=?").and()
				.inMemoryAuthentication().withUser("sa").password("{noop}test").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/", "/login.xhtml", "/javax.faces.resource/**").permitAll().anyRequest()
				.fullyAuthenticated().and().formLogin().loginPage("/login.xhtml").defaultSuccessUrl("/index.xhtml")
				.failureUrl("/login.xhtml?error=true").permitAll().and().logout().logoutSuccessUrl("/login.xhtml");

		// allow to use ressource links like pdf
		http.headers().frameOptions().sameOrigin();

	}

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		// onLogin
		UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
		// session.setUsername(userDetails.getUsername());
		LOGGER.info("onLogin: " + userDetails);
	}

}
