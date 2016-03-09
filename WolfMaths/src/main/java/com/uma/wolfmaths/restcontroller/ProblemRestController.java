package com.uma.wolfmaths.restcontroller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.uma.wolfmaths.constants.WolfmathsConstants;
import com.uma.wolfmaths.constants.WolframAlphaConstants;
import com.uma.wolfmaths.dao.WomaAlumAsigFacade;
import com.uma.wolfmaths.dao.WomaAlumnoFacade;
import com.uma.wolfmaths.dto.Problem;
import com.uma.wolfmaths.entity.WomaAlumno;
import com.uma.wolfmaths.form.ProblemForm;
import com.uma.wolfmaths.service.WolfMathsService;
import com.uma.wolfmaths.service.WolframAlphaService;

@Controller
@RequestMapping(value = "/problemRest")
public class ProblemRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProblemRestController.class);
	
	@Autowired
	private WomaAlumnoFacade woma;
	
	@RequestMapping(value = "/guardarProblemaJSON", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String guardarProblemaJSON(@RequestBody final String problemJSON) {
		String result = "";
		Problem problem = new Problem();
		
		ObjectMapper objectMapper = new ObjectMapper();
		try{
		problem = objectMapper.readValue(problemJSON, Problem.class);
		}catch(JsonParseException jpe){
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Entrando en al ejecución de la sentencia");
		try{
			double x;
			double y;
			double z;
			double k;
			int numVars;
			int numSteps;
			double resultado;
			/*String numVarsString = (String)req.getAttribute("inputNumVariables");
			numVars = Integer.parseInt((String)req.getAttribute("inputNumVariables"));
			String enunciado = problemForm.getProblem().getEnunciado();
			
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
			}*/
			
			logger.info("Valor Numero Variables: "+problem.getNumVars());
			logger.info("Valor Inicial X: "+problem.getVariables().getX());
			logger.info("Valor Inicial X: "+problem.getVariables().getY());
			logger.info("Valor Inicial X: "+problem.getVariables().getZ());
			logger.info("Valor Inicial X: "+problem.getVariables().getK());
			
			logger.info("Valor Numero de Pasos: "+problem.getNumSteps());
			logger.info("Sentencia Paso 1: "+problem.getSteps().getStep1().getStep());
			logger.info("Sentencia Paso 2: "+problem.getSteps().getStep2().getStep());
			logger.info("Sentencia Paso 3: "+problem.getSteps().getStep3().getStep());
			logger.info("Sentencia Paso 4: "+problem.getSteps().getStep4().getStep());
			
			logger.info("Paso Resultado: "+problem.getSteps().getFinalStep().getStep());
			logger.info("Valor Resultado: "+problem.getResult());
			WolfMathsService.createProblem(problem, null, null);
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

}
