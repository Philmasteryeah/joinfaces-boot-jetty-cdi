package org.philmaster.boot;

import javax.inject.Inject;

import org.philmaster.boot.service.DatabaseService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject
	private DatabaseService db;

	@Inject
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// db login with encrypted pw and inmemory for testing
		auth.jdbcAuthentication()
				.dataSource(db.getDataSource())
				.passwordEncoder(new BCryptPasswordEncoder())
				.usersByUsernameQuery("SELECT username, password, enabled FROM account WHERE username=?")
				.authoritiesByUsernameQuery("SELECT username, 'ADMIN' FROM account WHERE username=?")
				.and()
				.inMemoryAuthentication().withUser("sa").password("{noop}test").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests()
				.antMatchers("/", "/login.xhtml", "/javax.faces.resource/**").permitAll().anyRequest()
				.fullyAuthenticated()
				.and().formLogin()
				.loginPage("/login.xhtml")
				.defaultSuccessUrl("/index.xhtml")
				.failureUrl("/login.xhtml?error=true").permitAll()
				.and().logout()
				.logoutSuccessUrl("/login.xhtml");

		// allow to use ressource links like pdf
		http.headers().frameOptions().sameOrigin();

	}

}
