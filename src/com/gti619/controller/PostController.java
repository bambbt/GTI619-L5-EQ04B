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
			// Si valide, proceder a l'ajout de l'utilisateur
			userService.addUser(role,login,completeName,mail,password);
			err="false";
			raison = login +" est ajoute";
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





		// Si valide on proc�de � la mise a jour du mdp

		//1 V�rifier si l'ancien mot de passe concorde
		if(userService.validatePasswd(getPrincipal(), oldPass)){
			// Si valide, v�rifier que le nouveau mdp n'a pas d�ja �t� utiliser	// Si valide, proceder � l'ajout de l'utilisateur

			if(!userService.oldPasswordCheckUsed(getPrincipal(),password)){
				userService.changePassword(getPrincipal(),password);
				err="false";
				raison = " Mot de passe change";
			}else{
				err="true";
				raison = " Mot de passe deja� utilise";
			}
		}
		else{
			raison = " Votre mot de passe est eronne";
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
	 * @throws Exception 
	 */
	@RequestMapping(value = "/forgetPass", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView postForgetPass(
			@RequestParam("uid") String username) throws Exception{
		ModelAndView model = new ModelAndView();
		String raison = "Ok";
		String err="true";
		System.out.println("JE VIENS DE RECEVOIR LE FORMULAIRE "+username);
		String notification = "Votre compte est en cours de reinitialisation. Un Pin secret a �t� envoy� sur votre @ courriel.";


		if(username.length()<20){
			//Valider si l'utilisateur existe
			if(userService.userExist(username)){
				// generer un id de recovery aleatoire et le stocker dans la bd temporairement le temps que l'utilisateur puisse faire son recovery
				userService.setRecoveryId(username);
				//Redirection de l'utilisateur vers la page setNewPass
				model.addObject("notif", notification);
				model.addObject("user", username);
				model.setViewName("/setNewPass");
			}
		}
		else{
			raison = "Oups! Le compte utilisateur n'existe pas";
			err="true";
			model.setViewName("/forgetPass");
			model.addObject("error", err);
			model.addObject("user", username);
			model.addObject("explain", "Votre compte utilisateur est en cours de reinitialisation. Un Pin secret a �t� envoy� sur votre adresse courriel. ");

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
			@RequestParam("uid") String username,
			@RequestParam("recovery") String recovery_id,
			@RequestParam("password") String pass){

		ModelAndView model = new ModelAndView();
		String raison = "Ok";
		String err="true";

		System.out.println("Reception du form de setNewPass en post");
		System.out.println("Voici les elements : " + username +" "+recovery_id+" "+pass);

		//Valider si l'utilisateur existe
		if(userService.recoveryValide(username, recovery_id)){

			//verifier si le mot de passe a pas ete utilise
			if(userService.oldPasswordCheckUsed(username, pass)){

				//Et remettre le recovery id 
				userService.resetRecoveryId(username);

				// Si le duo usermame recovery_id est valide setter le nouveau MDP
				userService.changePassword(username,pass);


				//Redirection de l'utilisateur vers la page setNewPass
				raison = "Cool! Votre mot de passe a ete reinitialise";
			}else{
				raison = "Ce mot de passe a déjà été utilisé";
				err="true";
				model.setViewName("/setNewPass");
				model.addObject("error", err);
				model.addObject("user", username);
			}

			err="false";
			model.setViewName("/login");
			model.addObject("error", err);
			model.addObject("user", username);
		}
		else{
			raison = "Oups! Probleme lors de la reinitialisation";
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
