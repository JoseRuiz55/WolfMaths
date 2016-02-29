package com.uma.wolfmaths.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.uma.wolfmaths.constants.WolfmathsConstants;
import com.uma.wolfmaths.constants.WolframAlphaConstants;
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
	
	
	@RequestMapping(value = "/ejecutarSentencia", method = RequestMethod.POST)
	@ResponseBody
	public String ejecutarSentencia(@RequestParam String sentenceToExecute, HttpServletRequest req , Model model) {
		String result = "";
		logger.info("Entrando en al ejecución de la sentencia"+sentenceToExecute);
		sentenceToExecute = sentenceToExecute.replace(' ', '+');
		String wolframStepExecution = sentenceToExecute;
		logger.info("Sentencia a ejecutar leida: "+wolframStepExecution);
		try{
			result = WolframAlphaService.getWolframResult(wolframStepExecution);
			
		}catch (Exception e)
		{
			logger.info(e.getMessage());
		}
		logger.info("El resultado de la invocación de la API es: "+WolfmathsConstants.API_RESULT_SEPARATOR+replaceMoreOrLess(result));
		return "El resultado de la invocación de la API es: "+WolfmathsConstants.API_RESULT_SEPARATOR+replaceMoreOrLess(result);
	}
	
	public String replaceMoreOrLess(String str){
		return str.replace(WolframAlphaConstants.MORE_OR_LESS, "=");
	}
	
	@RequestMapping(value = "/guardarProblema", method = RequestMethod.POST)
	@ResponseBody
	public String guardarProblema(HttpServletRequest req , Model model) {
		String result = "";
		logger.info("Entrando en al ejecución de la sentencia");
		try{
			double x;
			double y;
			double z;
			double k;
			int numVars;
			int numSteps;
			double resultado;
			numVars = Integer.parseInt((String)req.getAttribute("inputNumVariables"));
			
			HashMap<String, Double> mapVariables = getVariablesValuesFromForm(numVars, req);
			
			numSteps = Integer.parseInt((String)req.getAttribute("inputResolutionSteps"));
			
			HashMap<String,String> mapSteps = getStepsExecutionFromForm(numSteps,req);
			Iterator<Double> itrVariables = mapVariables.values().iterator();
			while(itrVariables.hasNext()){
				int i =0;
				logger.info("Valor Variables "+i+": "+itrVariables.next());
			}
			Iterator<String> itrSteps = mapSteps.values().iterator();
			while(itrSteps.hasNext()){
				int i =0;
				logger.info("Valor Step "+i+": "+itrSteps.next());
			}
			
			
			
			
			
			
			
			return "OK";
		}catch(Exception e){
			return "NOTOK";
		}
		/*sentenceToExecute = sentenceToExecute.replace(' ', '+');
		String wolframStepExecution = sentenceToExecute;
		logger.info("Sentencia a ejecutar leida: "+wolframStepExecution);
		try{
			result = WolframAlphaService.getWolframResult(wolframStepExecution);
			
		}catch (Exception e)
		{
			logger.info(e.getMessage());
		}
		logger.info("El resultado de la invocación de la API es: "+WolfmathsConstants.API_RESULT_SEPARATOR+replaceMoreOrLess(result));
		return "El resultado de la invocación de la API es: "+WolfmathsConstants.API_RESULT_SEPARATOR+replaceMoreOrLess(result);*/
	}
	
	public HashMap<String,Double> getVariablesValuesFromForm(int numVars, HttpServletRequest req) throws Exception{
		
		 HashMap<String,Double> hashVariables = new HashMap<String, Double>();
		 switch (numVars) {
	         case 1:  hashVariables.put(WolfmathsConstants.APP_VARIABLE_X,Double.parseDouble((String)req.getAttribute("inputInitialVarX")));
	                  break;
	         case 2:  hashVariables.put(WolfmathsConstants.APP_VARIABLE_X,Double.parseDouble((String)req.getAttribute("inputInitialVarX")));
			          hashVariables.put(WolfmathsConstants.APP_VARIABLE_Y,Double.parseDouble((String)req.getAttribute("inputInitialVarY")));
			          break;
	         case 3:  hashVariables.put(WolfmathsConstants.APP_VARIABLE_X,Double.parseDouble((String)req.getAttribute("inputInitialVarX")));
			          hashVariables.put(WolfmathsConstants.APP_VARIABLE_Y,Double.parseDouble((String)req.getAttribute("inputInitialVarY")));
			          hashVariables.put(WolfmathsConstants.APP_VARIABLE_Z,Double.parseDouble((String)req.getAttribute("inputInitialVarZ")));
			          break;
	         case 4:  hashVariables.put(WolfmathsConstants.APP_VARIABLE_X,Double.parseDouble((String)req.getAttribute("inputInitialVarX")));
			          hashVariables.put(WolfmathsConstants.APP_VARIABLE_Y,Double.parseDouble((String)req.getAttribute("inputInitialVarY")));
			          hashVariables.put(WolfmathsConstants.APP_VARIABLE_Z,Double.parseDouble((String)req.getAttribute("inputInitialVarZ")));
			          hashVariables.put(WolfmathsConstants.APP_VARIABLE_K,Double.parseDouble((String)req.getAttribute("inputInitialVarK")));
	                  break;
	         default: throw new Exception ("Error al leer el numero de variables valido");
	     }
		 return hashVariables;
		
	}
	
	public HashMap<String,String> getStepsExecutionFromForm(int numSteps, HttpServletRequest req) throws Exception{
		
		 HashMap<String,String> hashSteps = new HashMap<String, String>();
		 switch (numSteps) {
	         case 1:  hashSteps.put(WolfmathsConstants.APP_STEP_1,(String)req.getAttribute("inputStep1"));
	                  break;
	         case 2:  hashSteps.put(WolfmathsConstants.APP_STEP_1,(String)req.getAttribute("inputStep1"));
	         		  hashSteps.put(WolfmathsConstants.APP_STEP_2,(String)req.getAttribute("inputStep2"));
			          break;
	         case 3:  hashSteps.put(WolfmathsConstants.APP_STEP_1,(String)req.getAttribute("inputStep1"));
			          hashSteps.put(WolfmathsConstants.APP_STEP_2,(String)req.getAttribute("inputStep2"));
			          hashSteps.put(WolfmathsConstants.APP_STEP_3,(String)req.getAttribute("inputStep3"));
			          break;
	         case 4:  hashSteps.put(WolfmathsConstants.APP_STEP_1,(String)req.getAttribute("inputStep1"));
			          hashSteps.put(WolfmathsConstants.APP_STEP_2,(String)req.getAttribute("inputStep2"));
			          hashSteps.put(WolfmathsConstants.APP_STEP_3,(String)req.getAttribute("inputStep3"));
			          hashSteps.put(WolfmathsConstants.APP_STEP_4,(String)req.getAttribute("inputStep4"));
	                  break;
	         default: throw new Exception ("Error al leer el numero de variables valido");
	     }
		 return hashSteps;
		
	}

}
