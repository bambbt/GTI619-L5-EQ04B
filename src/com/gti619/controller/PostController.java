package com.gti619.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gti619.service.UserService;

@Controller
public class PostController {

	@Autowired
	private UserService userService;

	//POST
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView postAddUser(
			@RequestParam("role") String role, 
			@RequestParam("login") String login,
			@RequestParam("completeName") String completeName,
			@RequestParam("mail") String mail,
			@RequestParam("password") String password,
			@RequestParam("paswdAdmin") String adminPass){
		
		ModelAndView model = new ModelAndView();
		String raison = "Succes !!";

		System.out.println("Reception du form en poste");
		System.out.println("I am "+getPrincipal());
		System.out.println("Voici les elements : "+role +"" +login +" "+completeName+" "+mail+" "+password+" "+adminPass );

		//Validation du formulaire
		
		//1 valider le mot de passe de l'administrateur
		if(userService.validatePasswd(getPrincipal(), adminPass)){
			// Si valide, proceder � l'ajout de l'utilisateur
			userService.addUser(role,login,completeName,mail,password);
		}
		else{
			raison = "Erreur d'authentification Admin " +"Mauvais mot de passe.";
		}


		model.setViewName("/administration");
		model.addObject("notif", true);
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

		//1 V�rifier si l'ancien mot de passe concorde

		// Si valide, v�rifier que le nouveau mdp n'a pas d�ja �t� utiliser

		// Si valide on proc�de � la mise a jour du mdp



		model.setViewName("/changePasswd");
		model.addObject("error", err);
		model.addObject("raison", raison);
		return model;
	}

	/*	
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


}
