package com.gti619.controller;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("session")
public class RequestController {
	
	@Autowired
	private  UserSessionController user;
	
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String gethomePage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "welcome";
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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getloginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/carre", method = RequestMethod.GET)
	public String getcarre(ModelMap model) {		
		return "carre";
	}
	
	@RequestMapping(value = "/cercle", method = RequestMethod.GET)
	
	public String getcercle() {
		return "cercle";
	}
	
	@RequestMapping(value = "/administration", method = RequestMethod.GET)
	public String getAdministration() {
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

}