package org.philmaster.boot;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.philmaster.boot.service.AuthenticationService;
import org.philmaster.boot.service.DatabaseService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LogManager.getLogger();

	@Inject
	private DatabaseService db;

	@Inject
	private AuthenticationService authenticationService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
				.withUser("sa")
				.password("{noop}test")
				.roles("ADMIN");

//		auth.jdbcAuthentication()
//				.dataSource(db.getDataSource())
//				.usersByUsernameQuery("SELECT username, password, enabled FROM account WHERE username=?");
//				.userDetailsService(authenticationService);

//		auth.jdbcAuthentication()
//				.dataSource(db.getDataSource())
//				.usersByUsernameQuery("SELECT username, password, enabled FROM account WHERE username=?")
//				.authoritiesByUsernameQuery("SELECT username, 'ADMIN' FROM account WHERE username=?")
//				.and()
//				.inMemoryAuthentication()
//				.withUser("sa")
//				.password("{noop}test")
//				.roles("ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/", "/login.xhtml", "/javax.faces.resource/**")
				.permitAll()
				.anyRequest()
				.fullyAuthenticated()
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

	}

//	@Inject
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		// db login with encrypted pw and inMemory for testing
//		auth.jdbcAuthentication().dataSource(db.getDataSource()).passwordEncoder(new BCryptPasswordEncoder())
//				.usersByUsernameQuery("SELECT username, password, enabled FROM account WHERE username=?")
//				.authoritiesByUsernameQuery("SELECT username, 'ADMIN' FROM account WHERE username=?").and()
//				.inMemoryAuthentication().withUser("sa").password("{noop}test").roles("ADMIN");
//		// noop password encoder is important for the forbidden http get on rest auth
//	}
//
////	@Configuration
////	@Order(1)
////	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
////		protected void configure(HttpSecurity http) throws Exception {
////			// rest Login
////			http.antMatcher("/test/**").authorizeRequests().anyRequest().hasRole("ADMIN").and().httpBasic().and().csrf()
////					.disable();
////		}
////	}
//
//	@Configuration
//	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			// form login
//			http.authorizeRequests().antMatchers("/", "/login.xhtml", "/javax.faces.resource/**").permitAll()
//					.anyRequest().fullyAuthenticated().and().formLogin().loginPage("/login.xhtml")
//					.defaultSuccessUrl("/index.xhtml").failureUrl("/login.xhtml?error=true").permitAll().and().logout()
//					.logoutSuccessUrl("/login.xhtml").and().csrf().disable();
//
//			// allow to use ressource links like pdf
//			http.headers().frameOptions().sameOrigin();
//		}
//
//	}
}
