package com.gti619.controller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.context.request.RequestContextHolder;

import com.gti619.config.CustomAuthenticationProvider;
import com.gti619.model.User;
import com.gti619.service.UserService;

@Controller
@SessionAttributes
public class GetController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	
		
	
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
		
		RequestContextHolder.currentRequestAttributes().setAttribute("mon test", "test", 1);
		System.out.println(RequestContextHolder.currentRequestAttributes().getAttributeNames(1));
		System.out.println(RequestContextHolder.currentRequestAttributes().getSessionId());
		model.addAttribute("user", getPrincipal());
		model.addAttribute("idSession", RequestContextHolder.currentRequestAttributes().getSessionId());
		
		model.addAttribute("user", getPrincipal());
		model.addAttribute("idSession", RequestContextHolder.currentRequestAttributes().getSessionId());
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
	
	//GET
		@RequestMapping(value = "/regexPass", method = RequestMethod.GET)
		public String getRegexPass(ModelMap model) {
			System.out.println("regexPass");

			model.addAttribute("error", "");
			return "regexPass";
		}
		
		//GET
		@RequestMapping(value = "/adminTentativeMax", method = RequestMethod.GET)
		public String getAdminTentativeMax(ModelMap model) {
			System.out.println("getAdminTentativeMax");
			model.addAttribute("error", "");
			return "adminTentativeMax";
		}
		
		
		//GET
				@RequestMapping(value = "/reactiveAccount", method = RequestMethod.GET)
				public String getReactiveAccount(ModelMap model) {
					ArrayList<User> userList = userService.getUsersDisabled();
				
					System.out.println("getReactiveAccount");
					System.out.println("Attention, il faut r�cup�rer la liste des utilisateurs");
					model.addAttribute("error", "");
					model.addAttribute("userList", userList);
					return "reactiveAccount";
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
			
			// Create a stream to hold the output
			BufferedReader br = null;
			String everything = null;
			try {
				br = new BufferedReader(new FileReader("/tmp/logCo.log"));
			    StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
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
	
	
		
		
	

	/**
	 * Permet de retourner le nom de l'utilisateur en question
	 * @return
	 */
	private String getPrincipal(){		
		UserDetails principal = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		return principal.getUsername();
	}
	
	
	/**
	 * Permet de retourner la liste des roles de l'utilisateur en question			
	 * @return
	 */
	public List<String> getAuthorities(){
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();	
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}		
		return roles;
	}
	
	//GET
		@RequestMapping(value = "/myHome", method = RequestMethod.GET)
		public String getmyHome() {
			System.out.println("myHome");	
			String url = "";
			String userRole = getAuthorities().get(0);
			System.out.println(userRole);	
			if (userRole.equals("ROLE_CERCLE")) {
				url= "/homeCercle";
			} 
			else if (userRole.equals("ROLE_CARRE")) {
				url = "/homeCarre";
			}
			else if (userRole.equals("ROLE_ADMIN")) {
				url = "/homeAdmin";
			}
			return url;
		}
	 
	
}