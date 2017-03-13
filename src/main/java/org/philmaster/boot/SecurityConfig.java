package org.philmaster.boot;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().withUser("sa").password("1").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.csrf().disable();
	http.authorizeRequests()
	.antMatchers("resources/**").fullyAuthenticated().and().formLogin()
	.loginPage("/login.xhtml")
	.loginProcessingUrl("/appLogin")
	.usernameParameter("username")
	.passwordParameter("password")
	.defaultSuccessUrl("/index.xhtml").and().logout()
	.logoutUrl("/appLogout").logoutSuccessUrl("/login.xhtml");

    }

}
