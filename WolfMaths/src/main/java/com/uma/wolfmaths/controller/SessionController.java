package com.uma.wolfmaths.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.wolfmaths.constants.WolfmathsConstants;
import com.uma.wolfmaths.dao.WomaAlumnoFacade;
import com.uma.wolfmaths.dto.Alumno;
import com.uma.wolfmaths.dto.Problem;
import com.uma.wolfmaths.dto.Profesor;
import com.uma.wolfmaths.form.ProblemForm;
import com.uma.wolfmaths.form.RegistrationForm;
import com.uma.wolfmaths.form.SessionForm;
import com.uma.wolfmaths.restcontroller.ProblemRestController;
import com.uma.wolfmaths.service.WolfMathsService;

@Controller
@RequestMapping(value = "/sessionController")
public class SessionController {

	private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

	@Autowired
	private WolfMathsService wolfMathsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Locale locale, @ModelAttribute("sessionForm") final SessionForm sessionForm, final Map<String, Object> model, final HttpServletRequest request) {
		cleanSession(request);
		HttpSession session = request.getSession();
		String username  = sessionForm.getUserNameLogin();
		String password = sessionForm.getPasswordLogin();
		String rolToConsult = wolfMathsService.checkRoleUsername(username);
		if(rolToConsult!=null){
			if(rolToConsult.equals(WolfmathsConstants.ALUM_ROLE)){
				Alumno alumno = wolfMathsService.checkUsernamePasswordWomaAlumno(username, password);
				session.setAttribute(WolfmathsConstants.ROLE_SESSION, WolfmathsConstants.ALUM_ROLE);
				session.setAttribute(WolfmathsConstants.USER_SESSION, alumno);
				session.setAttribute(WolfmathsConstants.LOGED_SESSION, true);
				sessionForm.setRolSeleccionado(WolfmathsConstants.ALUM_ROLE);
				sessionForm.setAlumno(alumno);
				sessionForm.setUserNameLogin(alumno.getUsername());
				sessionForm.setLogueado(true);
				
			}else if(rolToConsult.equals(WolfmathsConstants.PROF_ROLE)){
				Profesor profesor = wolfMathsService.checkUsernamePasswordWomaProfesor(username, password);
				session.setAttribute(WolfmathsConstants.ROLE_SESSION, WolfmathsConstants.PROF_ROLE);
				session.setAttribute(WolfmathsConstants.USER_SESSION, profesor);
				session.setAttribute(WolfmathsConstants.LOGED_SESSION, true);
				sessionForm.setRolSeleccionado(WolfmathsConstants.PROF_ROLE);
				sessionForm.setProfesor(profesor);
				sessionForm.setUserNameLogin(profesor.getUsername());
				sessionForm.setLogueado(true);
			}
			
		}else{
			//Usuario no encontrado en BBDD
			logger.info("El usuario no se encuentra en BBDD");
			return WolfmathsConstants.VIEWS_HOME;
		}
		model.put("sessionForm", sessionForm);
		
		return WolfmathsConstants.VIEWS_APPLICATION_USERMAINPAGE;

	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(Locale locale,  final HttpServletRequest request) {
		cleanSession(request);
		return "redirect:"+WolfmathsConstants.CONTROLLER_HOMECONTROLLER_MAIN;

	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("registrationForm") final RegistrationForm registrationForm, final Map<String, Object> model, final HttpServletRequest request) {
		model.put("registrationForm", registrationForm);
		return "/application/registrationForm";
		

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
