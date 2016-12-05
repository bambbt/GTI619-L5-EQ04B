package com.gti619.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.gti619.service.SecurityConfigService;
import com.gti619.service.UserService;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	
	@Autowired
	@Qualifier("securityConfigService")
	private SecurityConfigService configService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}
		
		request.getSession().setMaxInactiveInterval(configService.getTimeoutInactiveSession());
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/*
	 * This method extracts the roles of currently logged-in user and returns
	 * appropriate URL according to his/her role.
	 */
	protected String determineTargetUrl(Authentication authentication) {
		String url = "";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (isCarre(roles)) {
			url = "/homeCarre";
		} 
		else if (isCercle(roles)) {
			url = "/homeCercle";	
		}else if (isAdmin(roles)) {
			url = "/loginFort";
		} else {
			url = "/Denied";
		}

		return url;
	}
	private boolean isCarre(List<String> roles) {
		if (roles.contains("ROLE_CARRE")) {
			return true;
		}
		return false;
	}

	private boolean isCercle(List<String> roles) {
		if (roles.contains("ROLE_CERCLE")) {
			return true;
		}
		return false;
	}

	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}

	

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}