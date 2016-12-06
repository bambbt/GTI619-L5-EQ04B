package com.gti619.config;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.gti619.model.User;
import com.gti619.service.SecurityConfigService;
import com.gti619.service.UserService;



@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityConfigService configService;

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;

	private static final Logger log = Logger.getLogger(CustomAuthenticationProvider.class);
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			log.info(new Date()+" => Tentative de connexion avec un utilisateur inexistant.");			
			return null;
		}
		
		

		String presentedLogin = authentication.getPrincipal().toString();
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(presentedLogin);
		
		if(!userDetails.isEnabled())
			return null;
		
		String strSalt = userService.findBylogin(presentedLogin).getSalt();;
		String presentedPassword = authentication.getCredentials().toString();


		String mixedPass = strSalt+presentedPassword;


		//encodage du password
		
		String passHash;
		try {
			passHash = PasswordEncoder.MD5encrypt(mixedPass);
			User user = userService.findBylogin(presentedLogin);
			if (!passHash.contentEquals(userDetails.getPassword())) {

				
				int nbTentativeCoMax = configService.getNbTentativeCoMax();
				if(user.getNbTentativesConn()<nbTentativeCoMax){
					user.setNbTentativesConn(user.getNbTentativesConn()+1);
					log.info("AUTH =>"+user.getNbTentativesConn()+" tentative(s) de l'utilisateur "+presentedLogin+" avec un mauvais mot de passe.");
				}else{
					user.setIsLocked(1);
					log.info("AUTH => Suite à "+nbTentativeCoMax+" tentatives sans succès de l'utilisateur "+presentedLogin+", le compte est bloqué.");
				}
				userService.attachDirty(user);
				return null;
			}
			user.setNbTentativesConn(0);
			userService.attachDirty(user);
			
			log.info("AUTH => L'utilisateur "+presentedLogin+" est connecté avec succés.");
			return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
}
