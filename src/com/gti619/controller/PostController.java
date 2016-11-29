package com.gti619.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
	@Qualifier("userService")
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
		String raison = "";
		String err = "true";

		System.out.println("Reception du form en poste");
		System.out.println("I am "+getPrincipal());
		System.out.println("Voici les elements : "+role +"" +login +" "+completeName+" "+mail+" "+password+" "+adminPass );

		//Validation du formulaire

		//1 valider le mot de passe de l'administrateur
		if(userService.validatePasswd(getPrincipal(), adminPass)){
			// Si valide, proceder ï¿½ l'ajout de l'utilisateur
			userService.addUser(role,login,completeName,mail,password);
			err="false";
			raison = login +" est ajoutÃ©";
		}
		else{
			raison = " Authentification Admin, mauvais mot de passe.";
			err="true";
		}


		model.setViewName("/administration");
		model.addObject("error",err);
		model.addObject("raison", raison);
		return model;
	}



	//POST
	@RequestMapping(value = "/changePasswd", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView postChangePasswd(
			@RequestParam("oldPass") String oldPass,
			@RequestParam("password") String password){
		ModelAndView model = new ModelAndView();
		String raison = "Ok";
		String err="true";


		System.out.println("Reception du form en poste");
		System.out.println("Voici les elements : "+getPrincipal()+" "+oldPass+" "+password );
		//Validation du formulaire





		// Si valide on procï¿½de ï¿½ la mise a jour du mdp

		//1 Vï¿½rifier si l'ancien mot de passe concorde
		if(userService.validatePasswd(getPrincipal(), oldPass)){
			// Si valide, vï¿½rifier que le nouveau mdp n'a pas dï¿½ja ï¿½tï¿½ utiliser	// Si valide, proceder ï¿½ l'ajout de l'utilisateur
			if(!userService.oldPasswordCheckUsed(getPrincipal(),password)){
				userService.changePassword(getPrincipal(),password);
				err="false";
				raison = " Mot de passe changÃ©";
			}
		}
		else{
			raison = " Authentification Admin, mauvais mot de passe.";
			err="true";
		}


		model.setViewName("/changePasswd");
		model.addObject("error", err);
		model.addObject("raison", raison);
		return model;
	}
	
	
	
	
	/**
	 * Permet de verifier le formulaire de recovery de mdp
	 * @param oldPass
	 * @param password
	 * @return
	 */
		@RequestMapping(value = "/forgetPass", method = RequestMethod.POST)
		@ResponseBody
		public ModelAndView postForgetPass(
				@RequestParam("ssiId") String username){
			
			
			ModelAndView model = new ModelAndView();
			String raison = "Ok";
			String err="true";
			System.out.println("Reception du form de recovery MDP en post");
			System.out.println("Voici les elements : " + username);

			//Valider si l'utilisateur existe
			if(userService.isValideUser(username)){
				// générer un id de recovery aléatoire et le stocker dans la bd temporairement le temps que l'utilisateur puisse faire son recovery
				
		
				//Redirection de l'utilisateur vers la page setNewPass
				model.setViewName("/setNewPass");
				model.addObject("user", username);
			}
			else{
				raison = "Oups! Le compte utilisateur n'existe pas";
				err="true";
				model.setViewName("/forgetPass");
				model.addObject("error", err);
				model.addObject("user", username);
			}
			return model;
		}
		
		
		/**
		 * Permet de verifier le formulaire de recovery de mdp
		 * @param oldPass
		 * @param password
		 * @return
		 */
			@RequestMapping(value = "/setNewPass", method = RequestMethod.POST)
			@ResponseBody
			public ModelAndView postSetNewPass(
					@RequestParam("ssoId") String username,
					@RequestParam("recovery") String recovery_id,
					@RequestParam("newPass") String pass){
				
				ModelAndView model = new ModelAndView();
				String raison = "Ok";
				String err="true";
				
				System.out.println("Reception du form de setNewPass en post");
				System.out.println("Voici les elements : " + username +" "+recovery_id+" "+pass);

				//Valider si l'utilisateur existe
				if(userService.recoveryValide(username, recovery_id)){
				
					// Si le duo usermame recovery_id est valide setter le nouveau MDP
					
			
					//Redirection de l'utilisateur vers la page setNewPass
					raison = "Cool! Votre mot de passe a été réinitialisé";
					err="false";
					model.setViewName("/login");
					model.addObject("error", err);
					model.addObject("user", username);
				}
				else{
					raison = "Oups! Problème lors de la réinitialisation";
					err="true";
					model.setViewName("/setNewPass");
					model.addObject("error", err);
					model.addObject("user", username);
				}
				
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
