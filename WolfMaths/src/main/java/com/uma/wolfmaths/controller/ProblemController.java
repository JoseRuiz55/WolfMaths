package com.uma.wolfmaths.controller;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.uma.wolfmaths.dao.WomaProblemaFacade;
import com.uma.wolfmaths.dao.WomaProfesorFacade;
import com.uma.wolfmaths.dto.Alumno;
import com.uma.wolfmaths.dto.Problem;
import com.uma.wolfmaths.dto.Profesor;
import com.uma.wolfmaths.dto.TareaAlumno;
import com.uma.wolfmaths.entity.WomaAlumno;
import com.uma.wolfmaths.entity.WomaProblema;
import com.uma.wolfmaths.entity.WomaProfesor;
import com.uma.wolfmaths.form.ProblemForm;
import com.uma.wolfmaths.form.SessionForm;
import com.uma.wolfmaths.form.TareasAlumnoForm;
import com.uma.wolfmaths.service.WolfMathsService;
import com.uma.wolfmaths.service.WolframAlphaService;

@Controller
@RequestMapping(value = "/problem")
public class ProblemController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);
	
	@Autowired
	private WomaAlumnoFacade woma;
	
	@Autowired
	WomaProfesorFacade womaProf;
	
	@Autowired
	WomaProblemaFacade womaProblema;
	
	@Autowired
	WolfMathsService wolfMathsService;
	
	/*@InitBinder
	public void initBinder(final WebDataBinder dataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dataBinder.registerCustomEditor(Profesor.class, "problem.profesor", new PropertyEditorSupport() {
			@Override
	        public void setAsText(String text) throws IllegalArgumentException {
				double value = 0;
				if(!text.equals(""))
				{
					value = Double.parseDouble(text);
				}
	            setValue(value);
	        }                  
	        @Override
	        public String getAsText() {
	        	
	        	double value = (Double)getValue();
	        	String returnedValue = "";
	        	if(value!=0)
	        	{
	        		returnedValue=String.valueOf(value);
	        	}
	            return returnedValue;
	        }
		});
	}*/

	@RequestMapping(value = "/createProblemForm", method = RequestMethod.GET)
	public String createProblemForm(Locale locale, final Map<String, Object> model, final HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		logger.info("createProblemForm -> Inicio");
		HttpSession session = request.getSession();
		logger.info("createProblemForm -> Fin");
		//recoverAndSetSessionForm(model);
		ProblemForm problemForm = new ProblemForm();
		problemForm.setAction("INICIO");
		problemForm = this.inicializaFormulario(problemForm);
		Profesor profesor = (Profesor)session.getAttribute(WolfmathsConstants.USER_SESSION);
		if (profesor == null){
			logger.error("El usuario no logueado como profesor o deslogueado");
			return WolfmathsConstants.VIEWS_HOME;
		}
		
		problemForm.getProblem().setProfesor((Profesor)session.getAttribute(WolfmathsConstants.USER_SESSION));
		model.put("problemForm", problemForm);
		
		return "problem/newProblemForm";
	}
	
	@RequestMapping(value = "/readProblemForm", method = RequestMethod.GET)
	public String readProblemForm(Locale locale, final Map<String, Object> model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		logger.info("createProblemForm -> Inicio");
		
		logger.info("createProblemForm -> Fin");
		//recoverAndSetSessionForm(model);
		ProblemForm problemForm = new ProblemForm();
		
		
		problemForm = wolfMathsService.readProblem(problemForm, womaProblema.find(8), null, womaProf.find(1), null);
		
		problemForm.setHayVarsSel(true);
		problemForm.setHayStepsSel(true);
		problemForm.setReadyToSaveProblem(true);
		problemForm.setAction("READYTOSAVEPROBLEM");
		model.put("problemForm", problemForm);
		this.inicializaFormulario(problemForm);
		return "problem/newProblemForm";
	}
	
	@RequestMapping(value = "/resolveProblemForm", method = RequestMethod.GET)
	public String resolveProblemForm(Locale locale, final Map<String, Object> model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		logger.info("createProblemForm -> Inicio");
		
		logger.info("createProblemForm -> Fin");
		//recoverAndSetSessionForm(model);
		ProblemForm problemForm = new ProblemForm();
		
		
		problemForm = wolfMathsService.readProblem(problemForm, womaProblema.find(8), null, womaProf.find(1), null);
		
		problemForm.setHayVarsSel(true);
		problemForm.setHayStepsSel(true);
		problemForm.setReadyToSaveProblem(true);
		problemForm.setAction("READYTOSAVEPROBLEM");
		model.put("problemForm", problemForm);
		this.inicializaFormulario(problemForm);
		return "problem/newProblemForm";
	}
	
	@RequestMapping(value = "/selVarsNumber", method = RequestMethod.POST)
	public String selVarsNumber(@ModelAttribute("problemForm") final ProblemForm problemForm, Locale locale, final Map<String, Object> model) {
		logger.info("Guardamos en el modelo las variables que tendr谩 el problema");
		//recoverAndSetSessionForm(model);
		
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
		//recoverAndSetSessionForm(model);
		
		problemForm.setHayVarsSel(true);
		problemForm.setHayStepsSel(true);
		problemForm.setReadyToSaveProblem(false);
		problemForm.setAction("STEPSSELECCIONADOS");
		model.put("problemForm", problemForm);
		this.inicializaFormulario(problemForm);
		return "problem/newProblemForm";
	}
	
	@RequestMapping(value = "/readyToSaveProblem", method = RequestMethod.POST)
	public String readyToSaveProblem(@ModelAttribute("problemForm") final ProblemForm problemForm, Locale locale, final Map<String, Object> model, final HttpServletRequest request) {
		logger.info("Guardamos en el modelo el numero de paso del problema");
		//recoverAndSetSessionForm(model);
		
		problemForm.setHayVarsSel(true);
		problemForm.setHayStepsSel(true);
		HttpSession session = request.getSession();
		String rolToConsult = (String) session.getAttribute(WolfmathsConstants.ROLE_SESSION);
		if(rolToConsult!=null && rolToConsult.equals(WolfmathsConstants.PROF_ROLE)){
			Profesor profesor = (Profesor)session.getAttribute(WolfmathsConstants.USER_SESSION);
			if (profesor == null){
				logger.error("El usuario no logueado como profesor o deslogueado");
				return WolfmathsConstants.VIEWS_HOME;
			}else{
				logger.info("Profesor que crea el problema: "+profesor);
				problemForm.setReadyToResolveProblem(false);
				problemForm.setReadyToSaveProblem(true);
				problemForm.setAction("READYTOSAVEPROBLEM");
				problemForm.getProblem().setProfesor((Profesor)session.getAttribute(WolfmathsConstants.USER_SESSION));
			}
			
		}else if(rolToConsult!=null && rolToConsult.equals(WolfmathsConstants.ALUM_ROLE)){
			Alumno alumno = (Alumno)session.getAttribute(WolfmathsConstants.USER_SESSION);
			if (alumno == null){
				logger.error("El usuario no logueado como alumno o deslogueado");
				return WolfmathsConstants.VIEWS_HOME;
			}else{
				logger.info("Alumno que resuelve el problema: "+alumno);
				problemForm.setReadyToSaveProblem(false);
				problemForm.setReadyToResolveProblem(true);
				problemForm.setAction("READYTORESOLVEPROBLEM");
				problemForm.getProblem().setAlumno((Alumno)session.getAttribute(WolfmathsConstants.USER_SESSION));
			}
			
		}else{
			logger.error("Error, el usuario no esta logueado con un perfil v醠ido");
			return WolfmathsConstants.VIEWS_HOME;
		}
		
		model.put("problemForm", problemForm);
		
		model.put("problemForm", problemForm);
		this.inicializaFormulario(problemForm);
		return "problem/newProblemForm";
	}
	
	
	@RequestMapping(value = "/ejecutarSentencia", method = RequestMethod.POST)
	@ResponseBody
	public String ejecutarSentencia(@RequestParam String sentenceToExecute, HttpServletRequest req , final Map<String, Object> model) {
		String result = "";
		logger.info("Entrando en al ejecuci贸n de la sentencia"+sentenceToExecute);
		sentenceToExecute = sentenceToExecute.replace(' ', '+');
		String wolframStepExecution = sentenceToExecute;
		logger.info("Sentencia a ejecutar leida: "+wolframStepExecution);
		try{
			result = WolframAlphaService.getWolframResult(wolframStepExecution);
			
		}catch (Exception e)
		{
			logger.info(e.getMessage());
		}
		logger.info("El resultado de la invocaci贸n de la API es: "+WolfmathsConstants.API_RESULT_SEPARATOR+replaceMoreOrLess(result));
		return "El resultado de la invocaci贸n de la API es: "+WolfmathsConstants.API_RESULT_SEPARATOR+replaceMoreOrLess(result);
	}
	
	public String replaceMoreOrLess(String str){
		return str.replace(WolframAlphaConstants.MORE_OR_LESS, "=");
	}
	
	@RequestMapping(value = "/guardarProblema", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView guardarProblema(@ModelAttribute("problemForm") final ProblemForm problemForm, final HttpServletRequest req , final Map<String, Object> model, final RedirectAttributes redirectAttributes) {
		String result = "";
		logger.info("Entrando en al ejecuci贸n de la sentencia");
		try{
			double x;
			double y;
			double z;
			double k;
			int numVars;
			int numSteps;
			double resultado;
			
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
		
	}
	
	@RequestMapping(value = "/guardarProblemaJSON", method = RequestMethod.POST)
	@ResponseBody
	public String guardarProblemaJSON(@RequestBody final Problem problem) {
		String result = "";
		logger.info("Entrando en al ejecuci贸n de la sentencia");
		try{
			double x;
			double y;
			double z;
			double k;
			int numVars;
			int numSteps;
			double resultado;
		
			
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
	
	
	@RequestMapping(value = "resolverProblema/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String resolverProblema(final @PathVariable("id") String id,
			final Map<String, Object> model, final HttpServletRequest request) throws Exception {
		
		logger.info("createProblemForm -> Inicio");
		HttpSession session = request.getSession();
		logger.info("createProblemForm -> Fin");
		//recoverAndSetSessionForm(model);
		ProblemForm problemForm = new ProblemForm();
		problemForm.setAction("INICIO");
		problemForm = this.inicializaFormulario(problemForm);
		if(WolfmathsConstants.ALUM_ROLE.equals(session.getAttribute(WolfmathsConstants.ROLE_SESSION))){
			Alumno alumno = (Alumno)session.getAttribute(WolfmathsConstants.USER_SESSION);
			if(alumno == null){
				logger.error("No se ha recuperado el alumno de sesion, error de sesi髇");
				return WolfmathsConstants.VIEWS_HOME;
			}
		}else{
			logger.error("No se ha realizado ningun login en la aplicaci髇");
			return WolfmathsConstants.VIEWS_HOME;
		}
		
		//wolfMathsService.readProblem(problemForm, womaProblema, womaAlumno, womaProfesor, intentoAlumno)
		WomaProblema womaProblema = wolfMathsService.getWomaProblemaByIdProblema(Integer.parseInt(id));
		problemForm.getProblem().setAlumno((Alumno)session.getAttribute(WolfmathsConstants.USER_SESSION));
		problemForm.getProblem().setStatement(womaProblema.getEnunciado());
		problemForm.getProblem().setIdProblemResolucion(id);
		model.put("problemForm", problemForm);
		
		return "problem/newProblemForm";
		
	}
	
	@RequestMapping(value = "evaluarResolucionesAlumnosProblema/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String evaluarResolucionesAlumnosProblema(final @PathVariable("id") String id, final Map<String, Object> model, final HttpServletRequest request) {
		
		logger.info("createProblemForm -> Inicio");
		HttpSession session = request.getSession();
		logger.info("createProblemForm -> Fin");
		//recoverAndSetSessionForm(model);
		ProblemForm problemForm = new ProblemForm();
		
		if(WolfmathsConstants.ALUM_ROLE.equals(session.getAttribute(WolfmathsConstants.ROLE_SESSION))){
			Alumno alumno = (Alumno)session.getAttribute(WolfmathsConstants.USER_SESSION);
			if(alumno == null){
				logger.error("No se ha recuperado el alumno de sesion, error de sesi髇");
				return WolfmathsConstants.VIEWS_HOME;
			}
		}
		
		//wolfMathsService.readProblem(problemForm, womaProblema, womaAlumno, womaProfesor, intentoAlumno)
		WomaProblema womaProblema = wolfMathsService.getWomaProblemaByIdSolucionProblema(Integer.parseInt(id));
		WomaProfesor womaProfesor = womaProblema.getWomaProfesorId();
		problemForm.getProblem().setStatement(womaProblema.getEnunciado());
		problemForm = wolfMathsService.readProblem(problemForm, womaProblema, null, womaProfesor, Integer.parseInt(id));
		problemForm.setHayVarsSel(true);
		problemForm.setHayStepsSel(true);
		problemForm.setReadyToGradeProblem(true);
		problemForm.setAction("READYTOGRADEPROBLEM");
		problemForm = this.inicializaFormulario(problemForm);
		model.put("problemForm", problemForm);
		
		
		return "problem/newProblemForm";
	}
	
	/*@RequestMapping(value = "/resolveProblemForm", method = RequestMethod.GET)
	public String resolveProblemForm(Locale locale, final Map<String, Object> model, final HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		logger.info("createProblemForm -> Inicio");
		HttpSession session = request.getSession();
		logger.info("createProblemForm -> Fin");
		//recoverAndSetSessionForm(model);
		ProblemForm problemForm = new ProblemForm();
		problemForm.setAction("INICIO");
		problemForm = this.inicializaFormulario(problemForm);
		Profesor profesor = (Profesor)session.getAttribute(WolfmathsConstants.USER_SESSION);
		if (profesor == null){
			logger.error("El usuario no logueado como profesor o deslogueado");
			return WolfmathsConstants.VIEWS_HOME;
		}
		
		problemForm.getProblem().setProfesor((Profesor)session.getAttribute(WolfmathsConstants.USER_SESSION));
		model.put("problemForm", problemForm);
		
		return "problem/newProblemForm";
	}*/
	
	public ProblemForm inicializaFormulario(ProblemForm problemForm){
		LinkedHashMap<Integer, Integer> inicializadorVariables = new LinkedHashMap<Integer,Integer>();
		inicializadorVariables.put(1, 1);
		inicializadorVariables.put(2, 2);
		inicializadorVariables.put(3, 3);
		inicializadorVariables.put(4, 4);
		problemForm.getVariablesNumberOptionsForm().setOpcionesSelect(inicializadorVariables);
		return problemForm;
	}
	
	public void recoverAndSetSessionForm(final Map<String, Object> model) {
		SessionForm sessionForm = (SessionForm)model.get("sessionForm");
		model.put("sessionForm", sessionForm);

	}

}
