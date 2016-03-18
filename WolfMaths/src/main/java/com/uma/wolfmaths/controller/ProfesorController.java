package com.uma.wolfmaths.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uma.wolfmaths.constants.WolfmathsConstants;
import com.uma.wolfmaths.dto.Alumno;
import com.uma.wolfmaths.dto.ProblemaProfesor;
import com.uma.wolfmaths.dto.Profesor;
import com.uma.wolfmaths.dto.SolucionProblemaAlumno;
import com.uma.wolfmaths.dto.TareaAlumno;
import com.uma.wolfmaths.entity.WomaProblema;
import com.uma.wolfmaths.form.ProblemForm;
import com.uma.wolfmaths.form.ProblemasProfesorForm;
import com.uma.wolfmaths.form.TareasAlumnoForm;
import com.uma.wolfmaths.service.WolfMathsService;

@Controller
@RequestMapping(value = "/profesor")
public class ProfesorController {

	private static final Logger logger = LoggerFactory.getLogger(ProfesorController.class);

	@Autowired
	private WolfMathsService wolfMathsService;
	
	@RequestMapping(value = "/obtenerProblemasProfesor", method = RequestMethod.GET)
	public String obtenerProblemasProfesor(Locale locale, final Map<String, Object> model, final HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		logger.info("createProblemForm -> Inicio");
		HttpSession session = request.getSession();
		logger.info("createProblemForm -> Fin");
		//recoverAndSetSessionForm(model);
		List<ProblemaProfesor> listaProblemasProfesor = null;
		if(WolfmathsConstants.PROF_ROLE.equals(session.getAttribute(WolfmathsConstants.ROLE_SESSION))){
			Profesor profesor = (Profesor)session.getAttribute(WolfmathsConstants.USER_SESSION);
			listaProblemasProfesor = wolfMathsService.getProblemasProfesor(profesor);
		}else{
			logger.info("No se pueden obtener tareas para un profesor");
		}
		
		ProblemasProfesorForm problemasProfesorForm = new ProblemasProfesorForm();
		problemasProfesorForm.setListaProblemaProfesor(listaProblemasProfesor);
		
		model.put("problemasProfesorForm", problemasProfesorForm);
		
		return "profesor/problemasProfesor";
	}
	
	@RequestMapping(value = "obtenerResolucionesAlumnosProblema/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String obtenerResolucionesAlumnosProblema(final @PathVariable("id") String id, final Map<String, Object> model, final HttpServletRequest request) {
		
		//wolfMathsService.readProblem(problemForm, womaProblema, womaAlumno, womaProfesor, intentoAlumno)
		ProblemasProfesorForm problemasProfesorForm = new ProblemasProfesorForm();
		WomaProblema womaProblema = wolfMathsService.getWomaProblemaByIdProblema(Integer.parseInt(id));
		List<SolucionProblemaAlumno> listaSolucionProblemaAlumno = wolfMathsService.getListaProblemaCorreccionAlumno(womaProblema);
		problemasProfesorForm.setListaSolucionProblemaAlumno(listaSolucionProblemaAlumno);
		model.put("problemasProfesorForm", problemasProfesorForm);
		
		return "profesor/listadoSolucionesAlumnos";
	}
	
	
}
