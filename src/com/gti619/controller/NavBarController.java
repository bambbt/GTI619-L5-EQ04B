package com.gti619.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class NavBarController {

	static final String navBar = 
"<nav class=\"navbar navbar-light bg-faded\">"+
  "<ul class=\"nav navbar-nav\">"+
  "<li class=\"nav-item active\">"+
   "<a href=\"<c:url value=\"/homeAdmin\" />\">Home</a>"+
    "</li><li class=\"nav-item active\"><a href=\"<c:url value=\"/cercle\" />\">Cercle</a></li>"+
    "<li class=\"nav-item\"><a href=\"<c:url value=\"/carre\" />\">Carre</a></li><li class=\"nav-item\">"+
    "<a href=\"<c:url value=\"/administration\" />\">Administration</a></li></ul></nav>";
	
	public String getNavBar(){

		String n = "";
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (roles.contains("ROLE_CARRE")) {
			n= navBar;
		} 
		else if (roles.contains("ROLE_CERCLE")) {
			n= navBar;	
		}else if (roles.contains("ROLE_ADMIN")) {
			n= navBar;
		}
		return n;
	}
	
	
}




