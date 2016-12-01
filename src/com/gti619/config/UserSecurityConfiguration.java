package com.gti619.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gti619.service.UserService;

@Configuration
@EnableWebSecurity
@PropertySource(value = { "classpath:resources/application.properties" })
public class UserSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomSuccessHandler customSuccessHandler;

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired	 
	UserService userService;

	@Autowired
	private Environment environment;
	
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

		//auth.inMemoryAuthentication().withUser("user1").password("cercle").roles("CERCLE");
		//auth.inMemoryAuthentication().withUser("user2").password("carre").roles("CARRE");
		//auth.inMemoryAuthentication().withUser("admin").password( environment.getRequiredProperty("jdbc.password")).roles("ADMIN");

		//auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("USER");
		//auth.inMemoryAuthentication().withUser("dba").password("root123").roles("ADMIN","DBA");*/

		//On doit crypter le mot de passe
		
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider);

		// Ici, on doit faire appel � la base de donnees 

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
		.antMatchers("/adminLog/**").access("hasRole('ADMIN')")
		.antMatchers("/adduser/**").access("hasRole('ADMIN')")
		.antMatchers("/regexPass/**").access("hasRole('ADMIN')")
		.antMatchers("/adminTentativeMax/**").access("hasRole('ADMIN')")
		.antMatchers("/reactiveAccount/**").access("hasRole('ADMIN')")
		.and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
		.usernameParameter("ssoId").passwordParameter("password")
		.and().csrf()
		.and().exceptionHandling().accessDeniedPage("/Denied");
	}

}
