package com.uma.wolfmaths.controller;

import java.text.DateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uma.wolfmaths.constants.WolfmathsConstants;
import com.uma.wolfmaths.dao.WomaAlumnoFacade;
import com.uma.wolfmaths.dto.Alumno;
import com.uma.wolfmaths.dto.Asignatura;
import com.uma.wolfmaths.dto.Profesor;
import com.uma.wolfmaths.form.ProblemForm;
import com.uma.wolfmaths.form.RegistrationForm;
import com.uma.wolfmaths.form.SessionForm;
import com.uma.wolfmaths.service.WolfMathsService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private WolfMathsService wolfMathsService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String rutaDefecto(Locale locale, final Map<String, Object> model, final HttpServletRequest request) {
		HttpSession session = request.getSession();
		String rolToConsult = (String) session.getAttribute(WolfmathsConstants.ROLE_SESSION);
		if(rolToConsult!=null){
			if(rolToConsult.equals(WolfmathsConstants.ALUM_ROLE)){
				return WolfmathsConstants.VIEWS_APPLICATION_USERMAINPAGE;
				
			}else if(rolToConsult.equals(WolfmathsConstants.PROF_ROLE)){
				return WolfmathsConstants.VIEWS_APPLICATION_USERMAINPAGE;
			}else{
				logger.error("Error en la validación de usuario, ROL INEXISTENTE");
				return WolfmathsConstants.VIEWS_HOME;
			}
			
		}else{
			//Usuario no encontrado en BBDD
			logger.info("No hay usuario en sesion");
			SessionForm sessionForm = new SessionForm();
			model.put("sessionForm", sessionForm);
			return WolfmathsConstants.VIEWS_HOME;
		}
		

	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Locale locale, final Map<String, Object> model, final HttpServletRequest request) {
		RegistrationForm registrationForm = new RegistrationForm();
		model.put("registrationForm", registrationForm);
		return "/application/registrationForm";
		

	}
	
	@RequestMapping(value = "/inscripcion", method = RequestMethod.GET)
	public String inscripcion(Locale locale, final Map<String, Object> model, final HttpServletRequest request) {
		RegistrationForm registrationForm = new RegistrationForm();
		List<Asignatura> listaAsignaturas = wolfMathsService.getListaAsignaturas();
		List<Profesor> listaProfesores = wolfMathsService.getListaProfesores();
		registrationForm.setListaAsignaturas(listaAsignaturas);
		registrationForm.setListaProfesores(listaProfesores);
		model.put("registrationForm", registrationForm);
		return "/application/inscriptionPage";
		

	}
	
	public void cleanSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		}
	}
	
	public void recoverAndSetSessionForm(final Map<String, Object> model) {
		SessionForm sessionForm = (SessionForm)model.get("sessionForm");
		model.put("sessionForm", sessionForm);

	}
	
}
