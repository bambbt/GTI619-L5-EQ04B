package com.gti619.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.gti619.daos.UserHome;



@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{



	@Autowired
	private UserHome userDao;

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;

	private Log log = LogFactory.getLog(CustomAuthenticationProvider.class);



	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			log.debug("Authentification raté");
			System.out.println("Authentification raté");
			throw new BadCredentialsException("Bad credentials");
		}

		String presentedLogin = authentication.getPrincipal().toString();
		String strSalt = userDao.findByUserName(presentedLogin).getSalt();;
		String presentedPassword = authentication.getCredentials().toString();


		String mixedPass = strSalt+presentedPassword;


		//encodage du password
		UserDetails userDetails = userDetailsService.loadUserByUsername(presentedLogin);
		String passHash;
		try {
			passHash = PasswordEncoder.MD5encrypt(mixedPass);

			System.out.println(passHash );

			System.out.println(userDetails.getPassword());
			if (!passHash.contentEquals(userDetails.getPassword())) {
				log.debug("Authentification raté");
				System.out.println("Authentification raté " );
				return null;
			}

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
