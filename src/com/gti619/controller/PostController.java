package com.gti619.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.gti619.model.User;
import com.gti619.service.SecurityConfigService;
import com.gti619.service.UserService;

@Controller
public class PostController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("securityConfigService")
	private SecurityConfigService configService;
	
	private static final Logger log = Logger.getLogger(PostController.class);

	private static final String EMAIL_PATTERN ="^[A-Za-z0-9]*[@][A-Za-z]*[\\.][a-z]{2,3}$";
	private static final String NAME_PATTERN = "^[a-zA-Z\\s]*$" ;
	private static final String LOGIN_PATTERN = "^[a-zA-Z0-9]*$" ;



	/**
	 * Methode qui permet de valider le formulaire d'ajout d'utilisateur
	 * @param role
	 * @param login
	 * @param completeName
	 * @param mail
	 * @param password
	 * @param adminPass
	 * @return
	 */
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
		System.out.println("I am "+getUserName());
		System.out.println("Voici les elements : "+role +"" +login +" "+completeName+" "+mail+" "+password+" "+adminPass );


		Pattern mailpattern = Pattern.compile(EMAIL_PATTERN);
		Matcher mailMatcher = mailpattern.matcher(mail);

		Pattern loginpattern = Pattern.compile(LOGIN_PATTERN);
		Matcher loginMatcher = loginpattern.matcher(login);

		Pattern completeNamepattern = Pattern.compile(NAME_PATTERN);
		Matcher completeNameMatcher = completeNamepattern.matcher(completeName);


		if(mailMatcher.matches() && loginMatcher.matches() && completeNameMatcher.matches()){
			//Validation du formulaire
			String regexPassword = configService.getPasswordPolitique();
			Pattern pattern = Pattern.compile(regexPassword);
			Matcher matcher = pattern.matcher(password);


			// Si valide on proc�de � la mise a jour du mdp
			if(matcher.matches()){
				//1 valider le mot de passe de l'administrateur
				if(userService.validatePasswd(getUserName(), adminPass)){
					// Si valide, proceder a l'ajout de l'utilisateur
					if(!userService.userExist(login,mail)){
						userService.addUser(role,login,completeName,mail,password);
						err="false";
						raison = login +" est ajoute";
						log.info("OPERATION : Création de  l'utilisateur "+login+ " par "+ getUserName() + "("+getRole()+")");
					}else{
						raison = " Login ou adresse mail déjà existante";
						err="true";
						log.info("ATTENTION !!! Tentative de création utilisateur par "+ getUserName() + "("+getRole()+") avec un mail ou un login déjà existant de passe.");
					}
				}
				else{
					raison = " Authentification Admin, mauvais mot de passe.";
					err="true";
					log.info("ATTENTION !!! Tentative de création utilisateur par "+ getUserName() + "("+getRole()+") avec un mauvais mot de passe.");
				}

			}else{
				raison = "Veuillez respecter les politiques de securite de password.";
				err="true";
				log.info("ATTENTION !!! Tentative de création utilisateur par "+ getUserName() + "("+getRole()+") sans respect de la politique de sécurité de mot de passes.");
			}

		}else{
			raison = "Corriger l'une des valeurs suivantes: "+ login+" ou "+completeName+" ou "+mail;
			err="true";
		}

		model.setViewName("/administration");
		model.addObject("error",err);
		model.addObject("raison", raison);
		return model;
	}



	/**
	 * Methode qui permet de valider le formulaire de modification de mot de passe
	 * @param oldPass
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/changePasswd", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView postChangePasswd(
			@RequestParam("oldPass") String oldPass,
			@RequestParam("password") String password){
		ModelAndView model = new ModelAndView();
		String raison = "Ok";
		String err="true";


		System.out.println("Reception du form en poste");
		System.out.println("Voici les elements : "+getUserName()+" "+oldPass+" "+password );
		//Validation du formulaire

		String regexPassword = configService.getPasswordPolitique();
		Pattern pattern = Pattern.compile(regexPassword);
		Matcher matcher = pattern.matcher(password);


		// Si valide on proc�de � la mise a jour du mdp
		if(matcher.matches()){
			//1 V�rifier si l'ancien mot de passe concorde
			if(userService.validatePasswd(getUserName(), oldPass)){
				// Si valide, v�rifier que le nouveau mdp n'a pas d�ja �t� utiliser	// Si valide, proceder � l'ajout de l'utilisateur

				if(!userService.oldPasswordCheckUsed(getUserName(),password)){
					userService.changePassword(getUserName(),password);
					err="false";
					raison = " Mot de passe change";
					log.info(" OPERATION :Changement de mot de passe par "+ getUserName() + "("+getRole()+").");
				}else{
					err="true";
					raison = " Mot de passe deja utilise";
					log.info("ATTENTION !!! Tentative de changement de mot de passe par "+ getUserName() + "("+getRole()+")  avec un mot de passe déjà existant");
				}
			}
			else{
				raison = " Votre mot de passe est eronne";
				err="true";
				log.info("ATTENTION !!! Tentative de changement de mot de passe par "+ getUserName() + "("+getRole()+") avec un mot de passe erroné");
			}}
		else{
			raison = "Politique de mots de passe non respectee.";
			err="true";
			log.info("ATTENTION !!! Tentative de changement de mot de passe par "+ getUserName() + "("+getRole()+") sans respect pour la politique de sécurité.");
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

		//Valider si l'utilisateur existe
		if(username.length()<20 && userService.userExist(username)){
			if(!userService.isUserLocked(username)){
				// generer un id de recovery aleatoire et le stocker dans la bd temporairement le temps que l'utilisateur puisse faire son recovery
				userService.setRecoveryId(username);
				//Redirection de l'utilisateur vers la page setNewPass
				model.addObject("notif", "Votre compte est en cours de reinitialisation. Un Pin secret a ete envoye sur votre @ courriel.");
				model.addObject("user", username);
				model.setViewName("/setNewPass");
				log.info("OPERATION :Demande de changement de mot de passe avec PIN par "+ getUserName() + "("+getRole()+")");
			}
			else{
				raison = "Votre compte est bloque , veuillez contacter l'administrateur pour plus d'information !";
				err="true";
				model.setViewName("/forgetPass");
				model.addObject("error", err);
				model.addObject("user", username);
				model.addObject("raison", "Compte bloque");
				model.addObject("explain", "Un trop grand nombre de tentatives eronnees. ");
				log.info("ATTENTION !!! Tentative de demande de changement de mot de passe avec PIN par "+ getUserName() + "("+getRole()+") sur un compte bloqué.");
			}
		}else{
			raison = "Oups! Le compte utilisateur n'existe pas";
			err="true";
			model.setViewName("/forgetPass");
			model.addObject("error", err);
			model.addObject("user", username);
			model.addObject("explain", "Oups! Le compte utilisateur n'existe pas. ");
			model.addObject("raison", "Oups! Le compte utilisateur n'existe pas. ");
			log.info("ATTENTION !!! Tentative de demande de changement de mot de passe avec PIN par "+ getUserName() + "("+getRole()+") sur en utilisant le login d'un compte inexistant \""+username+"\". ");
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

		String regexPassword = configService.getPasswordPolitique();
		Pattern pattern = Pattern.compile(regexPassword);
		Matcher matcher = pattern.matcher(pass);


		//Valider si l'utilisateur existe
		if(userService.recoveryValide(username, recovery_id) && !userService.isUserLocked(username)){

			// Si valide on procede a la mise a jour du mdp
			if(matcher.matches()){
				//verifier si le mot de passe a pas ete utilise
				if(!userService.oldPasswordCheckUsed(username, pass)){

					//Et remettre le recovery id 
					userService.resetRecoveryId(username);

					// Si le duo usermame recovery_id est valide setter le nouveau MDP
					userService.changePassword(username,pass);

					raison = "Cool! Votre mot de passe a ete reinitialise";
					err="false";
					model.setViewName("/login");
					model.addObject("error", err);
					model.addObject("user", username);
					model.addObject("raison", raison);
					//Redirection de l'utilisateur vers la page setNewPass
					log.info("OPERATION :Changement de mot de passe avec PIN par "+ getUserName() + "("+getRole()+")");


				}else{
					raison = "Ce mot de passe a deja� ete utilise";
					err="true";
					model.addObject("raison", raison);
					model.setViewName("/setNewPass");
					model.addObject("error", err);
					model.addObject("user", username);
					log.info("ATTENTION !!! Tentative de changement de mot de passe avec PIN par "+ getUserName() + "("+getRole()+") avec un mot de passe deja utilise");

				}


			}
			else{
				raison = "Politique de mot de passe non respectee.";
				err="true";
				model.addObject("raison", raison);
				model.setViewName("/setNewPass");
				model.addObject("error", err);
				model.addObject("user", username);
				log.info("ATTENTION !!!  Tentative de changement de mot de passe avec PIN par "+ getUserName() + "("+getRole()+") sans respect de la politique mot de passe");

			}
		}else{
			raison = "Oups! Probleme lors de la reinitialisation. Verifier le PiN ou le nom d'utilisateur";
			err="true";
			model.addObject("raison", raison);
			model.setViewName("/setNewPass");
			model.addObject("error", err);
			model.addObject("user", username);
			log.info("ATTENTION !!!  Tentative de changement de mot de passe avec PIN par "+ getUserName() + "("+getRole()+") avec un PIN ou un login errone");

		}
		return model;
	}




	/**
	 * Permet de verifier le formulaire permettant la mise a jour du regex mdp
	 * @param oldPass
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/regexPass", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView postRegexPass(
			@RequestParam("regex") String regex,
			@RequestParam("paswdAdmin") String adminPass){

		ModelAndView model = new ModelAndView();
		String raison = "Ok";
		String err="true";

		//Validation du mdp administreur

		if(userService.validatePasswd(getUserName(), adminPass)){
			//Si valide, mettre en place le regex dans la base de donnee
			// raison = feedBack positif
			userService.savePolitiquePassword(regex);
			err="false";
			raison="Politique des mots de passe modifiee.";
			log.info("OPERATION : Changement de politique de mot de passe par "+ getUserName() + "("+getRole()+") .");

		}else{
			//Si non valide, inialiser la raison
			err="true";
			raison = "Politique de mot de passe non valide";
			log.info("ATTENTION !!!  Tentative de changement de politique de mot de passe par "+ getUserName() + "("+getRole()+") avec un mot de passe errone.");

		}
		model.addObject("error", err);
		model.addObject("raison", raison);
		return model;
	};



	/**
	 * Permet de verifier le formulaire permettant de setter le nombre de tentative maximale avant de bloquer un compte utilisateur
	 * @param oldPass
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/adminTentativeMax", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView postAdminTentativeMax(
			@RequestParam("nb") String nbtentative,
			@RequestParam("paswdAdmin") String adminPass){

		ModelAndView model = new ModelAndView();
		String raison = "Ok";
		String err="true";

		//Validation du mdp administreur

		if(userService.validatePasswd(getUserName(), adminPass)){

			//Si valide, mettre en place le regex dans la base de donnee
			// raison = feedBack positif
			int nbTentativeMax = Integer.parseInt(nbtentative);
			userService.setTentativeCoMax(nbTentativeMax);
			err="false";
			raison="Politique de connexions modifiee.";
			log.info("OPERATION : Changement du nombre de tentative d authentification errone avant blocage du compte par "+ getUserName() + "("+getRole()+")");
		}else{

			//Si non valide, inialiser la raison
			err="true";
			raison = "Mot de passe non valide";
			log.info("ATTENTION !!!  Tentative de changement du nombre de tentative d authentification errone avant blocage du compte par "+ getUserName() + "("+getRole()+") avec mot de passe errone");

		}

		model.addObject("error", err);
		model.addObject("raison", raison);
		return model;
	};

	

	/**
	 * Permet de verifier le formulaire permettant de r�active un compte
	 * @param oldPass
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/reactiveAccount", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView postReactiveAccount(
			@RequestParam("login") String login,
			@RequestParam("newPass") String newPass,
			@RequestParam("adminPass") String adminPass){

		ModelAndView model = new ModelAndView();
		String raison = "true";
		String err="true";


		String regexPassword = configService.getPasswordPolitique();
		Pattern pattern = Pattern.compile(regexPassword);
		Matcher matcher = pattern.matcher(newPass);


		// Si valide on proc�de � la mise a jour du mdp
		if(matcher.matches()){
			//Validation du mdp administreur
			if(userService.validatePasswd(getUserName(), adminPass)){
				//Si valide, verifier si le mot de passe n'a pas ete utiliser (reflechir si c utile?)
				if(!userService.oldPasswordCheckUsed(login, newPass)){

					// Reactive le compte, Mettre a jour le mdp, nb tentative = 0 (attention si un compte est bloqu�, on ne peut utiliser Mot de passe perdu)
					userService.reactiveAccount(login);

					userService.changePassword(login, newPass);
					// raison = feedBack positif
					err="false";
					raison="Compte "+login+ " reactive";
					log.info("OPERATION :Reactivation du compte "+login+ " par "+ getUserName() + "("+getRole()+") avec un nouveau mot de passe");
				}else{
					err="true";
					raison="Mot de passe deja� utilise";
					log.info("ATTENTION !!! Tentative de reactivation du compte "+login+ " par "+ getUserName() + "("+getRole()+") avec un mot de passe deja utilise.");
				}
			}else{
				//Si non valide, inialiser la raison
				err="true";
				raison="Mot de passe invalide";
				log.info("ATTENTION !!! Tentative de reactivation du compte "+login+ " par "+ getUserName() + "("+getRole()+") avec un  mot de passe errone.");
			}
		}else{
			err="true";
			raison="Polique de mot de passe non respectee";
			log.info("ATTENTION !!!  Tentative de reactivation du compte "+login+ " par "+ getUserName() + "("+getRole()+") sans respect de la politique de mot de passe.");
		}

		ArrayList<User> userList = userService.getUsersDisabled();
		model.addObject("userList", userList);
		model.addObject("error", err);
		model.addObject("raison", raison);
		return model;
	};

	
	/**
	 * Permet de verifier le formulaire d'authentification forte Admin
	 * @param oldPass
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/loginFort", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView postLoginFort(
			@RequestParam("valueCell1") String valueCell1,
			@RequestParam("valueCell2") String valueCell2,
			@RequestParam("valueCell3") String valueCell3,
			@RequestParam("valueCell4") String valueCell4){

		ModelAndView model = new ModelAndView();
		String raison = "Ok";
		String err="true";
		System.out.println(valueCell1);
		System.out.println(valueCell2);
		System.out.println(valueCell3);
		System.out.println(valueCell4);
		System.out.println(RequestContextHolder.getRequestAttributes().getAttribute("idcell1", 1));
		System.out.println(RequestContextHolder.getRequestAttributes().getAttribute("idcell2", 2));
		System.out.println(RequestContextHolder.getRequestAttributes().getAttribute("idcell3", 3));
		System.out.println(RequestContextHolder.getRequestAttributes().getAttribute("idcell4", 4));
		
		ArrayList<Integer> adresses = new ArrayList<>();
		for (int i = 1; i < GetController.NBVALUE_DEFI; i++) {			
			int adresse = (int) RequestContextHolder.currentRequestAttributes().getAttribute("idcell"+i, 1);
			adresses.add(adresse);
		}
		ArrayList<Integer> values = new ArrayList<>();
		values.add(Integer.parseInt(valueCell1));
		values.add(Integer.parseInt(valueCell2));
		values.add(Integer.parseInt(valueCell3));
		values.add(Integer.parseInt(valueCell4));
		
		
		if(!userService.ckeckDefi(getUserName(),values, adresses)){
			raison = "Les donnees sont mauvaises.";
			err="true";
			model.setViewName("redirect:/logout");
			log.info("ATTENTION !!! Tentative d Authentification de "+ getUserName() + "("+getRole()+") par le systeme d authentification forte.");
		}else {	
			model.setViewName("homeAdmin");
			model.addObject("user", getUserName());
			log.info("OPERATION :Authentification de "+ getUserName() + "("+getRole()+") par le systeme d authentification forte.");
		}
		return model;
	
	};

	/**	
	 * Permet de retourner le nom de l'utilisateur en question
	 * @return (String) username
	 */
	private String getUserName(){
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
	public List<String> getRole(){
		Collection<? extends GrantedAuthority> role = SecurityContextHolder.getContext().getAuthentication().getAuthorities();	
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : role) {
			roles.add(a.getAuthority());
		}		
		return roles;
	}


}
