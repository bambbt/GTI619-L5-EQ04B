package com.gti619.controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.gti619.model.User;
import com.gti619.service.UserService;

@Controller
@SessionAttributes
public class GetController {

	public static final int NBVALUE_DEFI = 5;
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	private static final Logger log = Logger.getLogger(GetController.class);
	/**
	 * Methode permettant de retourner la page de connexion (login)
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getloginPage() {
		return "login";
	}

	/**
	 * Methode permettant de retourner la page de connexion (login)
	 * @return
	 */
	@RequestMapping(value = "/loginFort", method = RequestMethod.GET)
	public String getLoginFort(ModelMap model) {

		//Execution de la requete	
		for (int i = 1; i < NBVALUE_DEFI; i++) {
			int defi = new SecureRandom().nextInt(UserService.NBVALEUR_MATRICE)+1;
			RequestContextHolder.currentRequestAttributes().setAttribute("idcell"+i, defi, 1);
			model.addAttribute("defi"+i, defi);
		}

		//Set les valeurs dans le model
		model.addAttribute("user", getUserName());		
		return "loginFort";
	}


	/**
	 * Methode permettant de retourner la page forgetPAss
	 * @return
	 */
	@RequestMapping(value = "/forgetPass", method = RequestMethod.GET)
	public String getforgetPass(ModelMap model) {
		model.addAttribute("error", false);
		return "forgetPass";
	}


	/**
	 * Methode permettant de retourner la page setNewPass
	 * @return
	 */
	@RequestMapping(value = "/setNewPass", method = RequestMethod.GET)
	public String getSetNewPAss() {
		return "setNewPass";
	}


	/**
	 * Methode permettant de retourner la page home admin
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/homeAdmin", method = RequestMethod.GET)
	public String getadminPage(ModelMap model) {
		if(defiAdminOk()){
			initSession();
			model.addAttribute("user", getUserName());
			return "homeAdmin";
		}
		else{
			return "logout";
		}

	}

	/**
	 * Methode permettatn de retourner le home carre
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/homeCarre", method = RequestMethod.GET)
	public String getHomeCarre(ModelMap model) {
		if(getRole().get(0).equals("ADMIN")){
			if(defiAdminOk()){
				initSession();
				model.addAttribute("user", getUserName());
				return "homeCarre";
			}
			else{
				return "logout";
			}
		}
		else{
			initSession();
			model.addAttribute("user", getUserName());
			return "homeCarre";
		}
	}

	/**
	 * Methode permettant de retourner le home cercle
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/homeCercle", method = RequestMethod.GET)
	public String getHomeCercle(ModelMap model) {
		if(getRole().get(0).equals("ADMIN")){
			if(defiAdminOk()){
				initSession();
				model.addAttribute("user", getUserName());
				return "homeCercle";
			}
			else{
				return "logout";
			}
		}
		else{
			initSession();
			model.addAttribute("user", getUserName());
			return "homeCercle";
		}
	}


	/**
	 * Methode permettant de retourner la page de acces interdit
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Denied", method = RequestMethod.GET)
	public String getaccessDeniedPage(ModelMap model) {
		model.addAttribute("user", getUserName());
		return "denied";
	}



	/**
	 * Methode qui permet de retourner la page carre selon le role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carre", method = RequestMethod.GET)
	public String getcarre(ModelMap model) {
		String url = "";
		List<String> userRoles = this.getRole();

		if (userRoles.contains("ROLE_CARRE")) {
			url= "carre";
		} 
		else if (userRoles.contains("ROLE_ADMIN")) {
			if(defiAdminOk()){
				url = "carreA";
			}
			else{
				url = "logout";
			}

		}
		return url;
	}

	/**
	 * Methode qui permet de retourner la page cercle selon le role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cercle", method = RequestMethod.GET)
	public String getcercle(ModelMap model) {
		String url = "";
		List<String> userRoles = this.getRole();

		if (userRoles.contains("ROLE_CERCLE")) {
			url= "cercle";
		} 
		else if (userRoles.contains("ROLE_ADMIN")) {
			if(defiAdminOk()){
				url = "cercleA";
			}
			else{
				url = "logout";
			}	
		}
		return url;
	}


	/**
	 * Admin, methode qui permet de retourner la page d'administration
	 * d'ajout d'un compte utilisateur
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/administration", method = RequestMethod.GET)
	public String getAdministration(ModelMap model) {
		System.out.println("Administration");
		if(defiAdminOk()){
			model.addAttribute("error", "");
			return "administration";
		}
		else{
			return "logout";
		}
	}


	/**
	 * Admin, methode qui permet de retourner la page d'administration
	 * de modification de la politique de complexite des mdp
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/regexPass", method = RequestMethod.GET)
	public String getRegexPass(ModelMap model) {
		System.out.println("regexPass");
		if(defiAdminOk()){
			model.addAttribute("error", "");
			return "regexPass";
		}
		else{
			return "logout";
		}

	}

	/**
	 * Admin, methode qui permet de retourner la page d'administration
	 * de modification du nombre de tentative maximal avant blocage d'un compte
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminTentativeMax", method = RequestMethod.GET)
	public String getAdminTentativeMax(ModelMap model) {
		if(defiAdminOk()){
			System.out.println("getAdminTentativeMax");
			model.addAttribute("error", "");
			return "adminTentativeMax";
		}
		else{
			return "logout";
		}
	}


	/**
	 * Admin, methode qui permet de retourner la page d'administration
	 * de reactiver une compte utilisateur
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/reactiveAccount", method = RequestMethod.GET)
	public String getReactiveAccount(ModelMap model) {
		if(defiAdminOk()){
			ArrayList<User> userList = userService.getUsersDisabled();
			System.out.println("getReactiveAccount");
			System.out.println("Attention, il faut r�cup�rer la liste des utilisateurs");
			model.addAttribute("error", "");
			model.addAttribute("userList", userList);
			return "reactiveAccount";
		}
		else{
			return "logout";
		}
	}


	/**
	 * Methode qui permet de retourner la page logout
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		if(getRole().get(0).equals("ADMIN")){
			//Remettre defi a zero
			userService.ResetDefiSuccesCheck(getUserName());
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		SecurityContextHolder.clearContext();
		log.info("AUTH L'utilisateur : "+getUserName()+" s'est deconnecte (logout)");
		return "redirect:/login?logout";
	}


	/**
	 * Methode qui permet de retourner la page changePass
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changePasswd", method = RequestMethod.GET)
	public String getChangePasswd(ModelMap model) {
		String url = "";
		if(getRole().get(0).contains("ADMIN")){
			if(defiAdminOk()){
				System.out.println("changePasswd");	
				model.addAttribute("username", getUserName());
				url = "changePasswd";
			}
			else {
				url = "redirect:/logout";
			}
		}
		else if(!getRole().get(0).contains("ADMIN")){
			System.out.println("changePasswd");	
			model.addAttribute("username", getUserName());
			url = "changePasswd";
		}
		return url;
	}


	/**
	 * Admin, methode qui permet de retourner la page d'administration des logs de securtie
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminLog", method = RequestMethod.GET)
	public String getAdminLog(ModelMap model) {
		if(defiAdminOk()){
			System.out.println("adminLog");	
			String OS =System.getProperty("os.name");
			// Create a stream to hold the output

			BufferedReader br = null;
			String everything = null;
			try {
				System.out.println(OS);
				String filePath = "\\logs\\gti619.log";
				if(OS.contentEquals("Linux"))
					filePath = "/logs/gti619.log";
				System.out.println(System.getProperty("catalina.home").toString()+filePath);
				System.out.println(System.getProperty("catalina.base"));
				br = new BufferedReader(new FileReader(System.getProperty("catalina.home")+filePath));	
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					if(line.contains("AUTH") || line.contains("OPERATION") || line.contains("ATTENTION")){
						sb.insert(0,line);
						sb.insert(0,System.lineSeparator());
					}
					line = br.readLine();
				}
				everything = sb.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			model.addAttribute("log_connexion",everything);
			model.addAttribute("log_securite", "Console");
			return "adminLog";
		}
		else{
			return "logout";
		}
	}

	/**
	 * Methode qui permet de retourner le bon home selon le role utilisateur
	 * @return
	 */
	@RequestMapping(value = "/myHome", method = RequestMethod.GET)
	public String getmyHome() {
		System.out.println("myHome");	
		String url = "";
		String userRole = getRole().get(0);
		System.out.println(userRole);	
		if (userRole.equals("ROLE_CERCLE")) {
			url= "homeCercle";
		} 
		else if (userRole.equals("ROLE_CARRE")) {
			url = "homeCarre";
		}
		else if (userRole.equals("ROLE_ADMIN")) {
			if(defiAdminOk()){
				url = "homeAdmin";
			}
			else{
				url="logout";
			}
		}
		return url;
	}


	/**	
	 * Permet de retourner le nom de l'utilisateur en question
	 * @return
	 */
	private String getUserName(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getName();
		return principal.toString();
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

	/**
	 * Procedure affichant les elements presents dans la session utilisateur
	 */
	public void initSession(){
		String  [] attrs  = RequestContextHolder.currentRequestAttributes().getAttributeNames(1);
		System.out.println("Session ID: "+ RequestContextHolder.currentRequestAttributes().getSessionId());
		for (String attr : attrs) {
			System.out.println(attr +" : "+ RequestContextHolder.currentRequestAttributes().getAttribute(attr, 1));
		}
	}

	private boolean defiAdminOk(){
		boolean ok = false;
		if(userService.isDefiReussi(getUserName()))
			ok=true;
		return ok;
	}


}