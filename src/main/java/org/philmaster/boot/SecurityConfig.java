package org.philmaster.boot;

import javax.inject.Inject;

import org.philmaster.boot.service.DatabaseService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig {

	@Inject
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(DatabaseService.INSTANCE.getDataSource())
				.passwordEncoder(plaintextPasswordEncoder())
				.usersByUsernameQuery("SELECT username, password, enabled FROM account WHERE username=?")
				.authoritiesByUsernameQuery("SELECT username, 'ROLE_ADMIN' as authority FROM account WHERE username=?");

	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf()
					.disable();
			http.antMatcher("/rest/**")
					.authorizeRequests()
					.anyRequest()
					.authenticated()
					.and()
					.httpBasic();

		}
	}

	@Configuration
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf()
					.disable();
			
			http.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

			http.authorizeRequests()
					.antMatchers("/")
					.permitAll()
					.antMatchers("/javax.faces.resource/**")
					.permitAll()
					.anyRequest()
					.authenticated()
					.and()
					.formLogin()
					.loginPage("/login.xhtml")
					.defaultSuccessUrl("/index.xhtml", true)
					.failureUrl("/login.xhtml?error")
					.permitAll()
					.and()
					.logout()
					.logoutSuccessUrl("/login.xhtml")
					.clearAuthentication(true)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID");

			http.headers()
					.frameOptions()
					.sameOrigin();
		}
	}

	private PasswordEncoder plaintextPasswordEncoder() {

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
