package com.uma.wolfmaths.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/problem")
public class ProblemController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);

	@RequestMapping(value = "/createProblemForm", method = RequestMethod.GET)
	public String createProblemForm(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		logger.info("createProblemForm -> Inicio");
		
		logger.info("createProblemForm -> Fin");
		
		return "problem/newProblemForm";
	}

}
