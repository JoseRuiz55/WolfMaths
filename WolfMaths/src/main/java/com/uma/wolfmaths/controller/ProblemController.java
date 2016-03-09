package com.uma.wolfmaths.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.uma.wolfmaths.constants.WolfmathsConstants;
import com.uma.wolfmaths.constants.WolframAlphaConstants;
import com.uma.wolfmaths.dao.WomaAlumAsigFacade;
import com.uma.wolfmaths.dao.WomaAlumnoFacade;
import com.uma.wolfmaths.dto.Problem;
import com.uma.wolfmaths.entity.WomaAlumno;
import com.uma.wolfmaths.form.ProblemForm;
import com.uma.wolfmaths.service.WolframAlphaService;

@Controller
@RequestMapping(value = "/problem")
public class ProblemController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);
	
	@Autowired
	private WomaAlumnoFacade woma;

	@RequestMapping(value = "/createProblemForm", method = RequestMethod.GET)
	public String createProblemForm(Locale locale, final Map<String, Object> model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		logger.info("createProblemForm -> Inicio");
		
		logger.info("createProblemForm -> Fin");
		ProblemForm problemForm = new ProblemForm();
		problemForm.setAction("INICIO");
		problemForm = this.inicializaFormulario(problemForm);
		model.put("problemForm", problemForm);
		
		return "problem/newProblemForm";
	}
	
	@RequestMapping(value = "/selVarsNumber", method = RequestMethod.POST)
	public String selVarsNumber(@ModelAttribute("problemForm") final ProblemForm problemForm, Locale locale, final Map<String, Object> model) {
		logger.info("Guardamos en el modelo las variables que tendrá el problema");
		
		problemForm.setHayVarsSel(true);
		problemForm.setHayStepsSel(false);
		problemForm.setReadyToSaveProblem(false);
		problemForm.setAction("VARSSELECCIONADAS");
		List<WomaAlumno> lista = null;
		try{
		lista = woma.findAll();
		}catch (Exception e){
			logger.info(e.getMessage());
		}
		this.inicializaFormulario(problemForm);
		model.put("problemForm", problemForm);
		
		return "problem/newProblemForm";
	}
	
	@RequestMapping(value = "/selStepsNumber", method = RequestMethod.POST)
	public String selStepsNumber(@ModelAttribute("problemForm") final ProblemForm problemForm, Locale locale, final Map<String, Object> model) {
		logger.info("Guardamos en el modelo el numero de paso del problema");
		
		problemForm.setHayVarsSel(true);
		problemForm.setHayStepsSel(true);
		problemForm.setReadyToSaveProblem(false);
		problemForm.setAction("STEPSSELECCIONADOS");
		model.put("problemForm", problemForm);
		this.inicializaFormulario(problemForm);
		return "problem/newProblemForm";
	}
	
	@RequestMapping(value = "/readyToSaveProblem", method = RequestMethod.POST)
	public String readyToSaveProblem(@ModelAttribute("problemForm") final ProblemForm problemForm, Locale locale, final Map<String, Object> model) {
		logger.info("Guardamos en el modelo el numero de paso del problema");
		
		problemForm.setHayVarsSel(true);
		problemForm.setHayStepsSel(true);
		problemForm.setReadyToSaveProblem(true);
		problemForm.setAction("READYTOSAVEPROBLEM");
		model.put("problemForm", problemForm);
		this.inicializaFormulario(problemForm);
		return "problem/newProblemForm";
	}
	
	
	@RequestMapping(value = "/ejecutarSentencia", method = RequestMethod.POST)
	@ResponseBody
	public String ejecutarSentencia(@RequestParam String sentenceToExecute, HttpServletRequest req , final Map<String, Object> model) {
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
	public ModelAndView guardarProblema(@ModelAttribute("problemForm") final ProblemForm problemForm, final HttpServletRequest req , final Map<String, Object> model, final RedirectAttributes redirectAttributes) {
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
			Problem problem = problemForm.getProblem();
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
			
			ObjectMapper mapper = new ObjectMapper();
			String jgen = null;
			jgen = mapper.writeValueAsString(problem);
			redirectAttributes.addFlashAttribute("problemJSON", jgen);
			logger.info(jgen.toString());
			Problem p = mapper.readValue(jgen, Problem.class);
			ModelAndView view=new ModelAndView("redirect:/problemRest/guardarProblemaJSON");
			view.addObject("problemJSON", jgen);
			
			return view;
		}catch(Exception e){
			ModelAndView view = null;
			return view;
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
	
	@RequestMapping(value = "/guardarProblemaJSON", method = RequestMethod.POST)
	@ResponseBody
	public String guardarProblemaJSON(@RequestBody final Problem problem) {
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
	
	public ProblemForm inicializaFormulario(ProblemForm problemForm){
		LinkedHashMap<Integer, Integer> inicializadorVariables = new LinkedHashMap<Integer,Integer>();
		inicializadorVariables.put(1, 1);
		inicializadorVariables.put(2, 2);
		inicializadorVariables.put(3, 3);
		inicializadorVariables.put(4, 4);
		problemForm.getVariablesNumberOptionsForm().setOpcionesSelect(inicializadorVariables);
		return problemForm;
	}

}
