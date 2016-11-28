package com.gti619.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Permet d'initialiser l'appel � la racine d�s le lancement de l'app.
 * @author i7ais
 *
 */
public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	   @Override
	    protected Class<?>[] getRootConfigClasses() {
	        return new Class[] { ResolverConfig.class };
	    }
	  
	    @Override
	    protected Class<?>[] getServletConfigClasses() {
	        return null;
	    }
	  
	    @Override
	    protected String[] getServletMappings() {
	        return new String[] { "/" };
	    }

}
