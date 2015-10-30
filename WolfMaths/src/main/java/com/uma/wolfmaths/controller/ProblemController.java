package com.uma.wolfmaths.controller;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uma.wolfmaths.service.WolframAlphaService;

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
	
	
	@RequestMapping(value = "/ejecutarPaso", method = RequestMethod.POST)
	@ResponseBody
	public String ejecutarPaso(@RequestParam String step,@RequestParam String stepExecution, HttpServletRequest req , Model model) {
		String result = "";
		logger.info("Entrando en al ejecución del paso "+step);
		logger.info("inputStep"+step);
		String wolframStepExecution = stepExecution;
		logger.info("Sentencia a ejecutar leida: "+wolframStepExecution);
		try{
			result = WolframAlphaService.getWolframResult(wolframStepExecution);
			
		}catch (Exception e)
		{
			logger.info(e.getMessage());
		}
		
		return "El resultado de la invocación de la API es: "+result;
	}

}
