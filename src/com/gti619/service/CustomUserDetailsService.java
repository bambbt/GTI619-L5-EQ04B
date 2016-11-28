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
import org.springframework.transaction.annotation.Transactional;

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
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());

		return buildUserForAuthentication(user, authorities);


	}

	// Converts com.gti619.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.gti619.model.User user, 
			List<GrantedAuthority> authorities) {
		return new User(user.getEmail(), 
				user.getMdp(), true, 
				true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Role role) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		setAuths.add(new SimpleGrantedAuthority(role.getNom()));

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	public UserHome getUserDao() {
		return userDao;
	}

	public void setUserDao(UserHome userDao) {
		this.userDao = userDao;
	}

}