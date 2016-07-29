package com.uma.wolfmaths.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uma.wolfmaths.dto.Asignatura;
import com.uma.wolfmaths.dto.Profesor;
import com.uma.wolfmaths.form.RegistrationForm;
import com.uma.wolfmaths.service.WolfMathsService;

@Controller
@RequestMapping(value = "/asignatura")
public class AsignaturaController {
	
	public static final Logger logger = LoggerFactory.getLogger(AsignaturaController.class);
	
	@Autowired
	WolfMathsService wolfMathsService;
	
	@RequestMapping(value = "/nuevaAsignatura", method = RequestMethod.GET)
	public String nuevaAsignatura(Locale locale, final Map<String, Object> model, final HttpServletRequest request) {
		RegistrationForm registrationForm = new RegistrationForm();
		registrationForm.setCreacion(true);
		model.put("registrationForm", registrationForm);
		return "/application/asignaturaPage";
	}
	
	@RequestMapping(value = "/crearAsignatura", method = RequestMethod.POST)
	public String crearAsignatura(Locale locale, @ModelAttribute("registrationForm") final RegistrationForm registrationForm, final HttpServletRequest request) {
		
		Asignatura asignatura = registrationForm.getAsignatura();
		Integer idAsig = wolfMathsService.createNewAsignatura(asignatura);
		logger.info("Se ha creado al asignatura con ID: "+idAsig);
		return "redirect:/inscripcion";
	}
	
	@RequestMapping(value = "verAsignatura/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String verAsignatura(final @PathVariable("id") String id, final Map<String, Object> model, final HttpServletRequest request) {
		
		Asignatura asignatura = wolfMathsService.findAsignatura(Integer.valueOf(id));
		RegistrationForm registrationForm = new RegistrationForm();
		registrationForm.setAsignatura(asignatura);
		registrationForm.setVisualizar(true);
		
		model.put("registrationForm", registrationForm);
		return "/application/asignaturaPage";
	}
	
	
	
	@RequestMapping(value = "/editarAsignatura", method = RequestMethod.POST)
	public String editarAsignatura(Locale locale, @ModelAttribute("registrationForm") final RegistrationForm registrationForm, final Map<String, Object> model, final HttpServletRequest request) {
		registrationForm.setEditar(true);
		model.put("registrationForm", registrationForm);
		return "/application/asignaturaPage";
	}
	
	@RequestMapping(value = "/guardarAsignatura", method = RequestMethod.POST)
	public String guardarAsignatura(Locale locale, @ModelAttribute("registrationForm") final RegistrationForm registrationForm, final HttpServletRequest request) {
		
		Asignatura asignatura = registrationForm.getAsignatura();
		Integer idAsig = wolfMathsService.editAsignatura(asignatura);
		logger.info("Se ha editado la asignatura con ID: "+idAsig);
		return "redirect:/asignatura/verAsignatura/"+idAsig;
	}
	
	@RequestMapping(value = "/eliminarAsignatura/{id}", method = { RequestMethod.POST, RequestMethod.GET })
	public String eliminarAsignatura(final @PathVariable("id") String id, final HttpServletRequest request) {
		
		wolfMathsService.removeAsignatura(Integer.valueOf(id));
		logger.info("Se ha eliminado la asignatura con ID: "+id);
		return "redirect:/inscripcion";
	}

}
