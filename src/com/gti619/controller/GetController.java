package com.gti619.controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GetController {
		
	
	@RequestMapping(value = "/homeAdmin", method = RequestMethod.GET)
	public String getadminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "homeAdmin";
	}
	
	@RequestMapping(value = "/homeCarre", method = RequestMethod.GET)
	public String getHomeCarre(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "homeCarre";
	}
	
	@RequestMapping(value = "/homeCercle", method = RequestMethod.GET)
	public String getHomeCercle(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "homeCercle";
	}

	@RequestMapping(value = "/Denied", method = RequestMethod.GET)
	public String getaccessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "denied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getloginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/carre", method = RequestMethod.GET)
	public String getcarre(ModelMap model) {
		String url = "";
		List<String> userRoles = this.getAuthorities();
		
		if (userRoles.contains("ROLE_CARRE")) {
			url= "/carre";
		} 
		else if (userRoles.contains("ROLE_ADMIN")) {
			url = "/carreA";
		}
		return url;
	}
	
	@RequestMapping(value = "/cercle", method = RequestMethod.GET)
	public String getcercle(ModelMap model) {
		String url = "";
		List<String> userRoles = this.getAuthorities();
		
		if (userRoles.contains("ROLE_CERCLE")) {
			url= "/cercle";
		} 
		else if (userRoles.contains("ROLE_ADMIN")) {
			url = "/cercleA";
		}
		return url;
	}
	
	//GET
	@RequestMapping(value = "/administration", method = RequestMethod.GET)
	public String getAdministration() {
		System.out.println("Administration");
		return "administration";
	}
	


	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	
	//GET
	@RequestMapping(value = "/changePasswd", method = RequestMethod.GET)
	public String getChangePasswd(ModelMap model) {
		System.out.println("changePasswd");
		
		//model.addAttribute("navBar", );
		model.addAttribute("username", getPrincipal());
		
		String role = getRole();
		if(role.equals("ROLE_CARRE")){
			model.addAttribute("navBar", navAdmin);
		}
		else if(role.equals("ROLE_CERCLE")){
			model.addAttribute("navBar",navAdmin );
		}
		else if(role.equals("ROLE_ADMIN")){
			model.addAttribute("navBar",navAdmin );
		}
		
		return "changePasswd";
	}
	
	

	/**
	 * Permet de retourner le nom de l'utilisateur en question
	 * @return
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	
	/**
	 * Permet de retourner la liste des roles de l'utilisateur en question			
	 * @return
	 */
	public List<String> getAuthorities(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		Collection<? extends GrantedAuthority> authorities = ((UserDetails)principal).getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		
		return roles;
	}
	
	public String getRole(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		Collection<? extends GrantedAuthority> authorities = ((UserDetails)principal).getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
		
		return roles.get(0);
	}
		
	String navAdmin = 
	"<nav class=\"navbar navbar-light bg-faded\">"+ "\n" +
	  "<ul class=\"nav navbar-nav\">"+ "\n" +
	  "<li class=\"nav-item active\">"+ "\n" +
	      "<a href=\"<c:url value=\"/homeAdmin\" />\">Home</a>"+ "\n" +
	    "</li>"+ "\n" +
	    "<li class=\"nav-item active\">"+ "\n" +
	      "<a href=\"<c:url value=\"/cercle\" />\">Cercle</a>"+ "\n" +
	    "</li>"+ "\n" +
	    "<li class=\"nav-item\">"+ "\n" +
	 		"<a href=\"<c:url value=\"/carre\" />\">Carre</a>"+ "\n" +
	 	"</li>"+ "\n" +
	    "<li class=\"nav-item\">"+ "\n" +
	      "<a href=\"<c:url value=\"/administration\" />\">Administration</a>"+ "\n" +
	   "</li>"+ "\n" +
	      "<li class=\"nav-item\">"+ "\n" +
	      "<a href=\"<c:url value=\"/changePasswd\" />\">Mon Compte</a>"+ "\n" +
	    "</li>"+ "\n" +
	  "</ul>"+ "\n" +
	"</nav>";	
	
}