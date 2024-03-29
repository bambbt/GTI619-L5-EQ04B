package com.gti619.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.gti619")
public class ResolverConfig extends WebMvcConfigurerAdapter {
	
	
	 public void configureViewResolvers(ViewResolverRegistry registry) {
		 
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix("/WEB-INF/views/");
	        viewResolver.setSuffix(".jsp");
	        registry.viewResolver(viewResolver);
	    }
	

	/*
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     *
     */
    @Override

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ressources/**").addResourceLocations("/css_js/");
    }


}