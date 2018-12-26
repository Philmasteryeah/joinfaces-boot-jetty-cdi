package org.philmaster.boot;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.philmaster.boot.service.DatabaseService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig {

	private static final Logger LOGGER = LogManager.getLogger();

	@Inject
	private DatabaseService db;

	@Inject
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(db.getDataSource())
				.passwordEncoder(plaintextPasswordEncoder())
				.usersByUsernameQuery("SELECT username, password, enabled FROM account WHERE username=?")
				.authoritiesByUsernameQuery("SELECT username, 'ADMIN' as authority FROM account WHERE username=?")
				.and()
				.inMemoryAuthentication()
				.withUser("sa")
				.password("{noop}test")
				.roles("ADMIN");

	}

	@Configuration
	@Order(2)
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/", "/favicon.ico", "/javax.faces.resource/**", "/index.xhtml")
					.permitAll()
					.anyRequest()
					.hasAnyRole("ADMIN", "USER")
					.and()
					.formLogin()
					.loginPage("/login.xhtml")
					.defaultSuccessUrl("/index.xhtml")
					.failureUrl("/login.xhtml?error=true")
					.permitAll()
					.and()
					.logout()
					.logoutSuccessUrl("/login.xhtml")
					.and()
					.csrf()
					.disable();
			http.headers()
					.frameOptions()
					.sameOrigin();
		}
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// not working ?!
			http.antMatcher("/rest/**")
					.authorizeRequests()
					.anyRequest()
					.hasRole("ADMIN")
					.and()
					.httpBasic()
					.and()
					.csrf()
					.disable();
		}
	}

	private PasswordEncoder plaintextPasswordEncoder() {
		// only for testing
		return new PasswordEncoder() {
			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return rawPassword.toString()
						.equals(encodedPassword);
			}

			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}
		};
	}

}
