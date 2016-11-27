package com.gti619.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequestController {
		
	
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
	
	//POST
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView postAddUser(
			@RequestParam("role") String role, 
			@RequestParam("userId") String userId,
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("password") String password) {
		ModelAndView model = new ModelAndView();
		String raison = "Ok";
		Boolean err=true;
		
		
		System.out.println("Reception du form en poste");
		System.out.println("Voici les elements : "+role +"" +userId +" "+firstname+" "+lastname+" "+password );
		//Validation du formulaire
		
		//1 valider le mot de passe de l'administrateur
		
		// Si valide, proceder � l'ajout de l'utilisateur
		
		
		
		model.setViewName("/administration");
		model.addObject("error", err);
		model.addObject("raison", raison);
		return model;
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
	 * Permet de retourner les roles de l'utilisateur en question			
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
		
	
}