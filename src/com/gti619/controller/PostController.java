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
public class PostController {

		
		//POST
		@RequestMapping(value = "/adduser", method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView postAddUser(
				@RequestParam("role") String role, 
				@RequestParam("userId") String userId,
				@RequestParam("firstname") String firstname,
				@RequestParam("lastname") String lastname,
				@RequestParam("password") String password,
				@RequestParam("paswdAdmin") String adminPass){
			ModelAndView model = new ModelAndView();
			String raison = "Ok";
			Boolean err=true;
			
			
			System.out.println("Reception du form en poste");
			System.out.println("Voici les elements : "+role +"" +userId +" "+firstname+" "+lastname+" "+password+" "+adminPass );
			//Validation du formulaire
			
			//1 valider le mot de passe de l'administrateur
			
			// Si valide, proceder à l'ajout de l'utilisateur
			
			
			
			model.setViewName("/administration");
			model.addObject("error", err);
			model.addObject("raison", raison);
			return model;
		}
		
		
		
		//POST
				@RequestMapping(value = "/changePasswd", method = RequestMethod.POST)
				@ResponseBody
				public ModelAndView postChangePasswd(
						@RequestParam("username") String username,
						@RequestParam("oldPass") String oldPass,
						@RequestParam("password") String password){
					ModelAndView model = new ModelAndView();
					String raison = "Ok";
					Boolean err=true;
					
					
					System.out.println("Reception du form en poste");
					System.out.println("Voici les elements : "+username+" "+oldPass+" "+password );
					//Validation du formulaire
					
					//1 Vérifier si l'ancien mot de passe concorde
					
					// Si valide, vérifier que le nouveau mdp n'a pas déja été utiliser
					
					// Si valide on procède à la mise a jour du mdp
					
					
					
					model.setViewName("/changePasswd");
					model.addObject("error", err);
					model.addObject("raison", raison);
					return model;
				}
	
}
