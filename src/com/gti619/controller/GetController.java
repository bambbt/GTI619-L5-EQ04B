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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes
public class GetController {
		
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getloginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/forgetPass", method = RequestMethod.GET)
	public String getforgetPass(ModelMap model) {
		model.addAttribute("error", false);
		return "forgetPass";
	}
	
	@RequestMapping(value = "/setNewPass", method = RequestMethod.GET)
	public String getSetNewPAss() {
		return "setNewPass";
	}
	
	
	
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
	public String getAdministration(ModelMap model) {
		model.addAttribute("error", "");

		System.out.println("Administration");
		return "administration";
	}
	


	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		SecurityContextHolder.clearContext();
		return "redirect:/login?logout";
	}
	
	//GET
	@RequestMapping(value = "/changePasswd", method = RequestMethod.GET)
	public String getChangePasswd(ModelMap model) {
		System.out.println("changePasswd");	
		model.addAttribute("username", getPrincipal());
		return "changePasswd";
	}
	
	
	
	//GET
		@RequestMapping(value = "/adminLog", method = RequestMethod.GET)
		public String getAdminLog(ModelMap model) {
			System.out.println("adminLog");	
			//model.addAttribute("log",);
			return "adminLog";
		}
	
	//GET
		@RequestMapping(value = "/myHome", method = RequestMethod.GET)
		public String getmyHome() {
			System.out.println("myHome");	
			String url = "";
			List<String> userRoles = this.getAuthorities();
			
			if (userRoles.contains("ROLE_CERCLE")) {
				url= "/homeCercle";
			} 
			else if (userRoles.contains("ROLE_CARRE")) {
				url = "/homeCarre";
			}
			else if (userRoles.contains("ROLE_ADMIN")) {
				url = "/homeAdmin";
			}
			return url;
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
	
}