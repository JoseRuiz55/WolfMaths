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
import com.uma.wolfmaths.dto.Correccion;
import com.uma.wolfmaths.dto.CorreccionProblemaAlumno;
import com.uma.wolfmaths.dto.TareaAlumno;
import com.uma.wolfmaths.form.TareasAlumnoForm;
import com.uma.wolfmaths.service.WolfMathsService;

@Controller
@RequestMapping(value = "/alumno")
public class AlumnoController {

	private static final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

	@Autowired
	private WolfMathsService wolfMathsService;
	
	@RequestMapping(value = "/obtenerTareasAlumno", method = RequestMethod.GET)
	public String obtenerTareasAlumno(Locale locale, final Map<String, Object> model, final HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		logger.info("createProblemForm -> Inicio");
		HttpSession session = request.getSession();
		logger.info("createProblemForm -> Fin");
		//recoverAndSetSessionForm(model);
		List<TareaAlumno> listaTareasAlumno = null;
		if(WolfmathsConstants.ALUM_ROLE.equals(session.getAttribute(WolfmathsConstants.ROLE_SESSION))){
			Alumno alumno = (Alumno)session.getAttribute(WolfmathsConstants.USER_SESSION);
			listaTareasAlumno = wolfMathsService.getTareasAlumno(alumno);
		}else{
			logger.info("No se pueden obtener tareas para un profesor");
		}
		
		TareasAlumnoForm tareasAlumnoForm = new TareasAlumnoForm();
		tareasAlumnoForm.setListaTareasAlumno(listaTareasAlumno);
		
		model.put("tareasAlumnoForm", tareasAlumnoForm);
		
		return "alumno/tareasAlumno";
	}
	
	@RequestMapping(value = "/consultarCalificaciones", method = RequestMethod.GET)
	public String consultarCalificaciones(Locale locale, final Map<String, Object> model, final HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
			
		logger.info("createProblemForm -> Inicio");
		HttpSession session = request.getSession();
		logger.info("createProblemForm -> Fin");
		//recoverAndSetSessionForm(model);
		List<CorreccionProblemaAlumno> listaCalificacionesAlumno = null;
		if(WolfmathsConstants.ALUM_ROLE.equals(session.getAttribute(WolfmathsConstants.ROLE_SESSION))){
			Alumno alumno = (Alumno)session.getAttribute(WolfmathsConstants.USER_SESSION);
			listaCalificacionesAlumno = wolfMathsService.getCalificacionesAlumno(alumno);
		}else{
			logger.info("No se pueden obtener tareas para un profesor");
		}
		
		TareasAlumnoForm tareasAlumnoForm = new TareasAlumnoForm();
		tareasAlumnoForm.setListaCorreccionProblemaAlumno(listaCalificacionesAlumno);
		
		model.put("tareasAlumnoForm", tareasAlumnoForm);
		
		return "alumno/listaNotasProblemas";
	}
	
	@RequestMapping(value = "consultarHistoricoNotas/{idSolucionProblema}", method = { RequestMethod.POST, RequestMethod.GET })
	public String evaluarResolucionesAlumnosProblema(final @PathVariable("idSolucionProblema") String idSolucionProblema, final Map<String, Object> model, final HttpServletRequest request) {
		List<Correccion> listaHistoricoNotas = null;
		listaHistoricoNotas = wolfMathsService.getHistoricoNotas(idSolucionProblema);
		
		TareasAlumnoForm tareasAlumnoForm = new TareasAlumnoForm();
		tareasAlumnoForm.setListaHistorico(listaHistoricoNotas);
		
		model.put("tareasAlumnoForm", tareasAlumnoForm);
		
		return "alumno/historicoNotas";
	}
	
}
