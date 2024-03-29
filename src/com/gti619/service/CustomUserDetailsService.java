package com.gti619.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gti619.daos.UserHome;
import com.gti619.model.Role;




@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserHome userDao ;


	@Override
	public UserDetails loadUserByUsername(final String username) 
			throws UsernameNotFoundException {

		com.gti619.model.User user = userDao.findByUserName(username);

		if(user!=null){

			List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());

			return buildUserForAuthentication(user, authorities);
		}
		else
			throw new UsernameNotFoundException("Username not found");
		
	}

	// Converts com.gti619.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.gti619.model.User user, 
			List<GrantedAuthority> authorities) {	

		return new User(user.getLogin(), 
				user.getMdp(), (user.getIsLocked()==0), 
				true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Role role) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		setAuths.add(new SimpleGrantedAuthority("ROLE_"+role.getNom()));

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}