package com.gti619.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.gti619.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class UserSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("user1").password("cercle").roles("CERCLE");
		auth.inMemoryAuthentication().withUser("user2").password("carre").roles("CARRE");
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		
		//auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
		//auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");*/
		
		//On doit crypter le mot de passe
		
		//auth.userDetailsService(new CustomUserDetailsService());
		// Ici, on doit faire appel � la base de donn�es 
		
	}

	
	/**
	 * C'est ici que l'on def les restrictions.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "/home").access("hasRole('USER')")
	  	.antMatchers("/homeAdmin/**").access("hasRole('ADMIN')")
	  	.antMatchers("/homeCarre/**").access("hasRole('CARRE')")
	  	.antMatchers("/homeCercle/**").access("hasRole('CERCLE')")
	  	.antMatchers("/cercle/**").access("hasRole('CERCLE') or hasRole('ADMIN')")
	  	.antMatchers("/carre/**").access("hasRole('CARRE') or hasRole('ADMIN')")
	  	.antMatchers("/cercleA/**").access("hasRole('ADMIN')")
	  	.antMatchers("/carreA/**").access("hasRole('ADMIN')")
	  	.antMatchers("/administration/**").access("hasRole('ADMIN')")
	  	.and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
	  	.usernameParameter("ssoId").passwordParameter("password")
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/Denied");
	}

}
