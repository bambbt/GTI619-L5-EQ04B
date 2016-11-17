package com.gti619.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class Test {
	private String label = "Ceci est un test";
	private ModelAndView model = new ModelAndView("welcome");
	
	// Des quune request est faire sur /welcome
	@RequestMapping("/welcome")
	public ModelAndView test() {
		model.addObject("message", label);
			
		return model;
	}
}
