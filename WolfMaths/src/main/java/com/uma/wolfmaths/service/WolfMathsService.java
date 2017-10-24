package com.uma.wolfmaths.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.uma.wolfmaths.constants.WolfmathsConstants;
import com.uma.wolfmaths.dao.*;
import com.uma.wolfmaths.dto.*;
import com.uma.wolfmaths.entity.*;
import com.uma.wolfmaths.form.ProblemForm;
import com.uma.wolfmaths.form.RegistrationForm;
import com.uma.wolfmaths.utils.Mapper;


@Component("wolfMathsService")
public class WolfMathsService {
	
	private static final Logger logger = LoggerFactory.getLogger(WolfMathsService.class);
	
	@Autowired
	protected WomaAlumAsigFacade womaAlumAsigFacade;
	
	@Autowired
	protected WomaAlumnoFacade womaAlumnoFacade;
	
	@Autowired
	protected WomaAsignaturaFacade womaAsignaturaFacade;
	
	@Autowired
	protected WomaCorreccionFacade womaCorreccionFacade;
	
	@Autowired
	protected WomaIntentoProblemaFacade womaIntentoProblemaFacade;
	
	@Autowired
	protected WomaPasoResolucionProblemaFacade womaPasoResolucionProblemaFacade;
	
	@Autowired
	protected WomaProblemaFacade womaProblemaFacade;

	@Autowired
	protected WomaProfAsigFacade womaProfAsigFacade;
	
	@Autowired
	protected WomaProfesorFacade womaProfesorFacade;
	
	@Autowired
	protected WomaSolucionProblemaFacade womaSolucionProblemaFacade;
	
	@Autowired
	protected WomaVariablesProblemaFacade womaVariablesProblemaFacade;
	
	public Integer createProblem(Problem problem, Asignatura asignatura, Profesor profesor){
		Integer id = -1;
		try{
		List <WomaAlumno> lista = null;	
		/*SE RECUPERARAN DE SESION EN EL CONTROLLER*/
		WomaAsignatura womaAsignatura = womaAsignaturaFacade.findAll().get(0);
		//WomaAlumno womaAlumno = womaAlumnoFacade.find(alumno.getId());
		WomaProfesor womaProfesor = womaProfesorFacade.find(profesor.getId());
		

		/*PROCESO CREACION DEL PROBLEMA*/
		WomaProblema womaProblema = Mapper.mapProblemDtoToWomaProblema(problem);
		womaProblema = Mapper.setFKsToWomaProblema(womaProblema, womaAsignatura, womaProfesor);

		womaProblemaFacade.create(womaProblema);

		logger.info("ID womaProblema: "+womaProblema.getIdProb());

		/*
		WomaVariablesProblema womaVariablesProblema = Mapper.mapProblemVariablesToWomaVariablesProblema(problem.getProblemVariables(),problem.getNumVars());

		WomaVariablesProblemaFacade.create(womaVariablesProblema);

		WomaIntentoProblema womaIntentoProblema = Mapper.mapParamsToWomaIntentoProblema(problem.getNumVars(),problem.getComment(), womaVariablesProblema);
		WomaIntentoProblemaFacade.create(womaIntentoProblema);
		
		ArrayList<WomaPasoResolucionProblema> listaWomaPasoResolucionProblema = Mapper.mapProblemParamsToListWomaPasoResolucionProblema(problem.getProblemSteps, problem.getNumSteps);
		for(int i=0;i<listaWomaPasoResolucionProblema.size();i++){
			WomaPasoResolucionProblemaFacade.create(listaWomaPasoResolucionProblema.get(i));
		}*/

		WomaVariablesProblema womaVariablesProblema = Mapper.mapProblemVariablesToWomaVariablesProblema(problem.getVariables(),problem.getNumVars());

		womaVariablesProblemaFacade.create(womaVariablesProblema);

		logger.info("ID womaVariablesProblema: "+womaVariablesProblema.getIdVariablesProblema());

		WomaIntentoProblema womaIntentoProblema = Mapper.mapParamsToWomaIntentoProblema(problem.getNumVars(),problem.getComment(), womaVariablesProblema);
		womaIntentoProblemaFacade.create(womaIntentoProblema);

		logger.info("ID womaIntentoProblema: "+womaIntentoProblema.getIdInteProb());

		WomaSolucionProblema womaSolucionProblema = Mapper.paramsToWomaSolucionProblema(String.valueOf(problem.getResult()), problem.getNumSteps(), problem.getComment(), null, "R", 
			problem.getNumVars(), problem.getVariables(), womaProfesor, null, womaProblema, womaIntentoProblema);

		/*WomaVariablesProblemaFacade.create(womaSolucionProblema.getWomaIntentoProblemaId().getWomaVariablesProblemaId());
		WomaIntentoProblemaFacade.create(womaSolucionProblema.getWomaIntentoProblemaId());*/

		ArrayList<WomaPasoResolucionProblema> listaWomaPasoResolucionProblema = Mapper.mapProblemParamsToListWomaPasoResolucionProblema(problem.getSteps(), problem.getNumSteps());
		for(int i=0;i<listaWomaPasoResolucionProblema.size();i++){
			listaWomaPasoResolucionProblema.get(i).setWomaIntentoProblemaId(womaSolucionProblema.getWomaIntentoProblemaId());
			womaPasoResolucionProblemaFacade.create(listaWomaPasoResolucionProblema.get(i));
			logger.info("ID listaWomaPasoResolucionProblema.get("+i+"): "+listaWomaPasoResolucionProblema.get(i).getIdPasoResoProb());
		}

		womaSolucionProblemaFacade.create(womaSolucionProblema);
		logger.info("ID womaSolucionProblema: "+womaSolucionProblema.getIdSoluProb());

		id = womaProblema.getIdProb();
		logger.info("Problema Creado");


		}
		catch(Exception e){
			logger.info("error: "+e.getMessage());
			id = -1;

		}
		return id;
	}
	
	public ProblemForm readProblem(ProblemForm problemForm, WomaProblema womaProblema, WomaAlumno womaAlumno, WomaProfesor womaProfesor, Integer intentoAlumno){

		problemForm = Mapper.setWomaProblemaToProblemForm(womaProblema, womaAlumno, womaProfesor, intentoAlumno);

		return problemForm;
		
	}
	
	public String checkRoleUsername(String username){
		String result = null;
		if(womaAlumnoFacade.findAlumnoByUsername(username)!=null){
			result = "A";
		}else if(womaProfesorFacade.findProfesorByUsername(username)!=null){
			result = "P";
		}
		return result;
	}
	
	public Alumno checkUsernamePasswordWomaAlumno (String username, String password){
		
		WomaAlumno womaAlumno = null;
		womaAlumno = womaAlumnoFacade.findAlumnoByUsernameAndPassword(username, password);
		Alumno alumnoDto = new Alumno();
		alumnoDto = Mapper.mapWomaAlumnoToAlumnoDto(womaAlumno, alumnoDto);
		return alumnoDto;
	}
	
	public Profesor checkUsernamePasswordWomaProfesor (String username, String password){
		
		WomaProfesor womaProfesor = null;
		womaProfesor = womaProfesorFacade.findProfesorByUsernameAndPassword(username, password);
		Profesor profesorDto = new Profesor();
		profesorDto = Mapper.mapWomaProfesorToProfesorDto(womaProfesor, profesorDto);
		return profesorDto;
	}
	
	public List<TareaAlumno> getTareasAlumno (Alumno alumno){
		List<TareaAlumno> listaTareaAlumno = null;
		WomaAlumno womaAlumno = womaAlumnoFacade.find(alumno.getId());
		List<WomaAlumAsig> listaWomaAlumAsig = womaAlumno.getWomaAlumAsigList();
		
		if(listaWomaAlumAsig != null && !listaWomaAlumAsig.isEmpty() && listaWomaAlumAsig.size()!=0){
			List<WomaAsignatura> listaWomaAsignaturas = new ArrayList<WomaAsignatura>();
			for(int i=0;i<listaWomaAlumAsig.size();i++){
				listaWomaAsignaturas.add(listaWomaAlumAsig.get(i).getWomaAsignaturaId());
			}
			List<WomaProblema> listaWomaProblema = null;
			for(int i =0;i<listaWomaAsignaturas.size();i++){
				listaWomaProblema = womaProblemaFacade.findWomaProblemaByIdAsignatura(listaWomaAsignaturas.get(i).getIdAsig());
			}
			
			if(listaWomaProblema != null && !listaWomaProblema.isEmpty() && listaWomaProblema.size()>0){
				listaTareaAlumno = Mapper.mapListaWomaProblemaToListaTareasAlumno(listaWomaProblema);
			}
			
		}else{
			logger.info("El Alumno no tiene problemas a resolver");
		}
		
		return listaTareaAlumno;
	}
	
	public WomaProblema getWomaProblemaByIdProblema (Integer idProblema){
		WomaProblema womaProblema = womaProblemaFacade.find(idProblema);
		return womaProblema;
	}
	
	public Integer resolveProblem(Problem problem, Asignatura asignatura, Alumno alumno){
		Integer id = -1;
		try{
		List <WomaAlumno> lista = null;	
		/*SE RECUPERARAN DE SESION EN EL CONTROLLER*/
		WomaAsignatura womaAsignatura = womaAsignaturaFacade.findAll().get(0);
		//WomaAlumno womaAlumno = womaAlumnoFacade.find(alumno.getId());
		WomaAlumno womaAlumno = womaAlumnoFacade.find(alumno.getId());
		

		/*PROCESO CREACION DEL PROBLEMA*/
		WomaProblema womaProblema = womaProblemaFacade.find(Integer.parseInt(problem.getIdProblemResolucion()));

		logger.info("ID womaProblema: "+womaProblema.getIdProb());

		/*
		WomaVariablesProblema womaVariablesProblema = Mapper.mapProblemVariablesToWomaVariablesProblema(problem.getProblemVariables(),problem.getNumVars());

		WomaVariablesProblemaFacade.create(womaVariablesProblema);

		WomaIntentoProblema womaIntentoProblema = Mapper.mapParamsToWomaIntentoProblema(problem.getNumVars(),problem.getComment(), womaVariablesProblema);
		WomaIntentoProblemaFacade.create(womaIntentoProblema);
		
		ArrayList<WomaPasoResolucionProblema> listaWomaPasoResolucionProblema = Mapper.mapProblemParamsToListWomaPasoResolucionProblema(problem.getProblemSteps, problem.getNumSteps);
		for(int i=0;i<listaWomaPasoResolucionProblema.size();i++){
			WomaPasoResolucionProblemaFacade.create(listaWomaPasoResolucionProblema.get(i));
		}*/

		WomaVariablesProblema womaVariablesProblema = Mapper.mapProblemVariablesToWomaVariablesProblema(problem.getVariables(),problem.getNumVars());

		womaVariablesProblemaFacade.create(womaVariablesProblema);

		logger.info("ID womaVariablesProblema: "+womaVariablesProblema.getIdVariablesProblema());

		WomaIntentoProblema womaIntentoProblema = Mapper.mapParamsToWomaIntentoProblema(problem.getNumVars(),problem.getComment(), womaVariablesProblema);
		womaIntentoProblemaFacade.create(womaIntentoProblema);

		logger.info("ID womaIntentoProblema: "+womaIntentoProblema.getIdInteProb());

		WomaSolucionProblema womaSolucionProblema = Mapper.paramsToWomaSolucionProblema(String.valueOf(problem.getResult()), problem.getNumSteps(), problem.getComment(), null, "R", 
			problem.getNumVars(), problem.getVariables(), null, womaAlumno, womaProblema, womaIntentoProblema);

		/*WomaVariablesProblemaFacade.create(womaSolucionProblema.getWomaIntentoProblemaId().getWomaVariablesProblemaId());
		WomaIntentoProblemaFacade.create(womaSolucionProblema.getWomaIntentoProblemaId());*/

		ArrayList<WomaPasoResolucionProblema> listaWomaPasoResolucionProblema = Mapper.mapProblemParamsToListWomaPasoResolucionProblema(problem.getSteps(), problem.getNumSteps());
		for(int i=0;i<listaWomaPasoResolucionProblema.size();i++){
			listaWomaPasoResolucionProblema.get(i).setWomaIntentoProblemaId(womaSolucionProblema.getWomaIntentoProblemaId());
			womaPasoResolucionProblemaFacade.create(listaWomaPasoResolucionProblema.get(i));
			logger.info("ID listaWomaPasoResolucionProblema.get("+i+"): "+listaWomaPasoResolucionProblema.get(i).getIdPasoResoProb());
		}

		womaSolucionProblemaFacade.create(womaSolucionProblema);
		logger.info("ID womaSolucionProblema: "+womaSolucionProblema.getIdSoluProb());

		id = womaProblema.getIdProb();
		logger.info("Resolucion Creado");


		}
		catch(Exception e){
			logger.info("error: "+e.getMessage());
			id = -1;

		}
		return id;
	}

	
	public List<ProblemaProfesor> getProblemasProfesor (Profesor profesor){
		List<ProblemaProfesor> listaProblemaProfesor = new ArrayList<ProblemaProfesor>();
		//WomaProfesor womaProfesor = womaProfesorFacade.find(profesor.getId());
		List<WomaProblema> listaWomaProblema = womaProblemaFacade.findWomaProblemaByIdProfesor(profesor.getId());
		logger.info("Se han recuperado :"+listaWomaProblema.size()+" problemas creados por el profesor :"+profesor);
		
		if(listaWomaProblema != null && !listaWomaProblema.isEmpty() && listaWomaProblema.size()!=0){
			for(int i=0;i<listaWomaProblema.size();i++){
				ProblemaProfesor problemaProfesor = new ProblemaProfesor();
				problemaProfesor.setId(listaWomaProblema.get(i).getIdProb());
				problemaProfesor.setIdString(listaWomaProblema.get(i).getIdProb().toString());
				problemaProfesor.setResumen(listaWomaProblema.get(i).getEnunciado());
				problemaProfesor.setAsignatura(Mapper.mapWomaAsignaturaToAsignaturaDto(listaWomaProblema.get(i).getWomaAsignaturaId(), problemaProfesor.getAsignatura()));
				List<WomaSolucionProblema> listaResolucionesAlumnos = womaSolucionProblemaFacade.getListaWomaSolucionProblemaAlumnos(listaWomaProblema.get(i));
				problemaProfesor.setNumeroResolucionesAlumnos(listaResolucionesAlumnos.size());
				listaProblemaProfesor.add(problemaProfesor);
			}
			
		}else{
			logger.info("El Profesor no tiene problemas a resolver");
		}
		
		return listaProblemaProfesor;
	}
	
	public SolucionProblemaAlumno getProblemaCorreccionAlumno (Integer idSolucionProblema){
		
		SolucionProblemaAlumno solucionProblemaAlumno = new SolucionProblemaAlumno();
		WomaSolucionProblema womaSolucionProblema = womaSolucionProblemaFacade.find(idSolucionProblema);
		
		solucionProblemaAlumno.setProblemaCorreccionAlumno(Mapper.mapWomaSolucionProblemaToProblemaCorreccionAlumno(womaSolucionProblema,solucionProblemaAlumno.getProblemaCorreccionAlumno()));
		
		WomaCorreccion womaCorreccion = womaCorreccionFacade.findByWomaSolucionProblema(womaSolucionProblema);
		if(womaCorreccion!=null)
		{
			solucionProblemaAlumno.setCorreccion(Mapper.mapWomaCorrecionToCorrecionDto(womaCorreccion,solucionProblemaAlumno.getCorreccion()));
		}
		
		solucionProblemaAlumno.setAlumno(Mapper.mapWomaAlumnoToAlumnoDto(womaSolucionProblema.getWomaAlumnoId(), solucionProblemaAlumno.getAlumno()));
		solucionProblemaAlumno.setIdProblema(womaSolucionProblema.getWomaProblemaId().getIdProb());
		return solucionProblemaAlumno;
	}
	
	public List<SolucionProblemaAlumno> getListaProblemaCorreccionAlumno(WomaProblema womaProblema){
		
		List<SolucionProblemaAlumno> listaSolucionProblemaAlumno = new ArrayList<SolucionProblemaAlumno>();
		List<WomaSolucionProblema> listaResolucionesAlumnos = womaSolucionProblemaFacade.getListaWomaSolucionProblemaAlumnos(womaProblema);
		for(int i=0;i<listaResolucionesAlumnos.size();i++){
			SolucionProblemaAlumno solucionProblemaAlumno = getProblemaCorreccionAlumno(listaResolucionesAlumnos.get(i).getIdSoluProb());
			listaSolucionProblemaAlumno.add(solucionProblemaAlumno);
		}
		
		return listaSolucionProblemaAlumno;
	}
	
	public WomaProblema getWomaProblemaByIdSolucionProblema (Integer idProblema){
		WomaSolucionProblema womaSolucionProblema = womaSolucionProblemaFacade.find(idProblema);
		return womaSolucionProblema.getWomaProblemaId();
	}

	public Integer correctProblem(NotaProblemRest notaProblemRest) {
		WomaCorreccion womaCorreccion = new WomaCorreccion();
		womaCorreccion.setComentario(notaProblemRest.getComentario());
		womaCorreccion.setNota(notaProblemRest.getNota());
		
		
		WomaSolucionProblema womaSolucionProblema =womaSolucionProblemaFacade.find(Integer.parseInt(notaProblemRest.getIdProblemResolucion()));
		womaCorreccion.setWomaSolucionProblemaId(womaSolucionProblema);
		
		womaCorreccionFacade.create(womaCorreccion);
		
		return womaCorreccion.getIdCorr();
		
	}

	public Integer autoCorregirProblema(String idResolucion) {
		WomaCorreccion womaCorreccion = new WomaCorreccion();
		List<WomaSolucionProblema> listaWomaSolucionProblemaProfesor = womaSolucionProblemaFacade.getSolucionProfesorByIdSolucionAlumno(Integer.parseInt(idResolucion));
		
		if(listaWomaSolucionProblemaProfesor.size()>1){
			logger.error("Se han recuperado varias soluciones finales para un mismo problema, ERROR, se recuperará la última");
		}
		String solucionTotalProfesor = listaWomaSolucionProblemaProfesor.get(listaWomaSolucionProblemaProfesor.size()-1).getSolucionTotal();
		
		
		WomaSolucionProblema womaSolucionProblemaAlumno = womaSolucionProblemaFacade.find(Integer.parseInt(idResolucion));
		String solucionTotalAlumno = womaSolucionProblemaAlumno.getSolucionTotal();
		
		Double solucionProfesor = Double.parseDouble(solucionTotalProfesor);
		Double solucionAlumno = Double.parseDouble(solucionTotalAlumno);
		
		if(solucionProfesor == solucionAlumno){
			womaCorreccion.setNota(WolfmathsConstants.NOTA_AUTOCALCULADA_MISMO_RESULTADO);
			womaCorreccion.setComentario(WolfmathsConstants.NOTA_AUTOCALCULADA_COMENTARIO_DEFAULT);
			womaCorreccion.setWomaSolucionProblemaId(womaSolucionProblemaAlumno);
		}else{
			womaCorreccion.setNota(WolfmathsConstants.NOTA_AUTOCALCULADA_DISTINTO_RESULTADO);
			womaCorreccion.setComentario(WolfmathsConstants.NOTA_AUTOCALCULADA_COMENTARIO_DEFAULT);
			womaCorreccion.setWomaSolucionProblemaId(womaSolucionProblemaAlumno);
		}
		womaCorreccionFacade.create(womaCorreccion);
		logger.info("Correccion creada con ID:"+womaCorreccion.getIdCorr());
		
		return womaCorreccion.getIdCorr();
	}

	public List<CorreccionProblemaAlumno> getCalificacionesAlumno(Alumno alumno) {
		List<CorreccionProblemaAlumno> listaCalificacionesAlumno = new ArrayList<CorreccionProblemaAlumno>();
		
		
		WomaAlumno womaAlumno = womaAlumnoFacade.find(alumno.getId());
		List<WomaSolucionProblema> listaWomaSolucionProblemaAlumno = womaSolucionProblemaFacade.getSolucionesAlumnoByAlumno(womaAlumno);
		for(int i=0;i<listaWomaSolucionProblemaAlumno.size();i++){
			WomaSolucionProblema womaSolucionProblema = listaWomaSolucionProblemaAlumno.get(i);
			CorreccionProblemaAlumno correccionProblemaAlumno = Mapper.mapWomaSolucionProblemaToCorreccionProblemaAlumno(womaSolucionProblema);
			listaCalificacionesAlumno.add(correccionProblemaAlumno);
		}
		
		return listaCalificacionesAlumno;
	}

	public List<Correccion> getHistoricoNotas(String idProblema) {
		WomaSolucionProblema womaSolucionProblema = womaSolucionProblemaFacade.find(Integer.parseInt(idProblema));
		List<WomaCorreccion> listaWomaCorreccion = womaCorreccionFacade.findListCorrecionesByWomaSolucionProblema(womaSolucionProblema);
		List<Correccion> listaHistorico = new ArrayList<Correccion>();
		for(int i=0;i<listaWomaCorreccion.size();i++){
			Correccion correccion = new Correccion();
			correccion = Mapper.mapWomaCorrecionToCorrecionDto(listaWomaCorreccion.get(i), correccion);
			listaHistorico.add(correccion);
		}
		
		return listaHistorico;
	}

	public Integer createNewUser(RegistrationForm registrationForm) {
		Integer idUser = null;
		if(registrationForm.getRol().equals(WolfmathsConstants.REGISTRATION_ROL_ADMINISTRADOR)){
			
			
		}else if(registrationForm.getRol().equals(WolfmathsConstants.REGISTRATION_ROL_PROFESOR)){
			WomaProfesor womaProfesor = new WomaProfesor();
			womaProfesor = Mapper.mapProfesorDtoToWomaProfesor(womaProfesor,registrationForm.getProfesor());
			womaProfesorFacade.create(womaProfesor);
			idUser = womaProfesor.getIdProf();
			
		}else if(registrationForm.getRol().equals(WolfmathsConstants.REGISTRATION_ROL_ALUMNO)){
			WomaAlumno womaAlumno = new WomaAlumno();
			womaAlumno = Mapper.mapAlumnoDtoToWomaAlumno(womaAlumno,registrationForm.getAlumno());
			womaAlumnoFacade.create(womaAlumno);
			idUser = womaAlumno.getIdAlum();
			
		}else{
			logger.info("Error en los parametros de entrada");
		}
		return idUser;
		
	}

	public List<Asignatura> getListaAsignaturas() {
		List<Asignatura> listaAsignaturas = new ArrayList<Asignatura>();
		List<WomaAsignatura> listaWomaAsignatura = womaAsignaturaFacade.findAll();
		for(int i=0;i<listaWomaAsignatura.size();i++){
			listaAsignaturas.add(Mapper.mapWomaAsignaturaToAsignaturaDto(listaWomaAsignatura.get(i), new Asignatura()));
		}
		return listaAsignaturas;
	}

	public List<Profesor> getListaProfesores() {
		List<Profesor> listaProfesores = new ArrayList<Profesor>();
		List<WomaProfesor> listaWomaProfesor = womaProfesorFacade.findAll();
		for(int i=0;i<listaWomaProfesor.size();i++){
			listaProfesores.add(Mapper.mapWomaProfesorToProfesorDto(listaWomaProfesor.get(i), new Profesor()));
		}
		
		return listaProfesores;
	}

	public Integer createNewAsignatura(Asignatura asignatura) {
		WomaAsignatura womaAsignatura = new WomaAsignatura();
		womaAsignatura = Mapper.mapAsignaturaDtoToWomaAsignatura(womaAsignatura, asignatura);
		womaAsignaturaFacade.create(womaAsignatura);
		
		return womaAsignatura.getIdAsig();
	}
	
	public Asignatura findAsignatura(Integer id) {
		WomaAsignatura womaAsignatura = womaAsignaturaFacade.find(id);
		Asignatura asignatura = Mapper.mapWomaAsignaturaToAsignaturaDto(womaAsignatura, new Asignatura());
		
		return asignatura;
	}
	
	public Integer editAsignatura(Asignatura asignatura) {
		WomaAsignatura womaAsignatura = womaAsignaturaFacade.find(asignatura.getId());
		womaAsignatura = Mapper.mapAsignaturaDtoToWomaAsignatura(womaAsignatura, asignatura);
		womaAsignaturaFacade.edit(womaAsignatura);
		
		return womaAsignatura.getIdAsig();
	}
	
	public void removeAsignatura(Integer idAsignatura) {
		WomaAsignatura womaAsignatura = womaAsignaturaFacade.find(idAsignatura);
		womaAsignaturaFacade.remove(womaAsignatura);
	}
	
	public Integer asignarProfesorAAsignatura(Integer idProfesor, Integer idAsignatura){
        WomaAsignatura womaAsignatura = womaAsignaturaFacade.find(idAsignatura);
        WomaProfesor womaProfesor = womaProfesorFacade.find(idProfesor);
        WomaProfAsig womaProfAsig = new WomaProfAsig();

        womaProfAsig.setIdProfAsig(womaProfesor.getIdProf());
        womaProfAsig.setWomaAsignaturaId(womaAsignatura);
        womaProfAsigFacade.create(womaProfAsig);

        logger.info("Relación establecida con ID: "+womaProfAsig.getIdProfAsig());
        return womaProfAsig.getIdProfAsig();
    }
    
    public void desasignarProfesorAAsignatura(Integer idProfesor, Integer idAsignatura){
        WomaAsignatura womaAsignatura = womaAsignaturaFacade.find(idAsignatura);
        WomaProfesor womaProfesor = womaProfesorFacade.find(idProfesor);

        WomaProfAsig womaProfAsig = womaProfAsigFacade.findByAsigAndProf(womaAsignatura, womaProfesor);
        womaProfAsigFacade.remove(womaProfAsig);

        logger.info("Profesor con ID"+womaProfesor.getIdProf()+ " desasignado a la asignatura con ID"+womaAsignatura.getIdAsig());
    }

    public Integer asignarAlumnoAAsignatura(Integer idAlumno, Integer idAsignatura){
        WomaAsignatura womaAsignatura = womaAsignaturaFacade.find(idAsignatura);
        WomaAlumno womaAlumno = womaAlumnoFacade.find(idAlumno);
        WomaAlumAsig womaAlumAsig = new WomaAlumAsig();

        womaAlumAsig.setWomaAlumnoId(womaAlumno);
        womaAlumAsig.setWomaAsignaturaId(womaAsignatura);
        womaAlumAsigFacade.create(womaAlumAsig);

        logger.info("Relación establecida con ID: "+womaAlumAsig.getIdAsumAsig());
        return womaAlumAsig.getIdAsumAsig();
    }
    
    public void desasignarAlumnoAAsignatura(Integer idAsignatura, Integer idAlumno){
        WomaAsignatura womaAsignatura = womaAsignaturaFacade.find(idAsignatura);
        WomaAlumno womaAlumno = womaAlumnoFacade.find(idAlumno);

        WomaAlumAsig womaAlumAsig = womaAlumAsigFacade.findByAsigAndAlum(womaAsignatura, womaAlumno);
        womaAlumAsigFacade.remove(womaAlumAsig);

        logger.info("Alumno con ID"+womaAlumno.getIdAlum()+ " desasignado a la asignatura con ID"+womaAsignatura.getIdAsig());
    }
    
    public List<InscripcionProfesor> recuperarAsignacionesProfesor(Integer idProfesor){
        WomaProfesor womaProfesor = womaProfesorFacade.find(idProfesor);

        List<WomaProfAsig> listaAsignaturasProfesor = womaProfAsigFacade.findByAsigAndProf(womaProfesor);
        List<InscripcionProfesor> listaInscripcionesProfesorDto = new ArrayList<InscripcionProfesor>();
        listaInscripcionesProfesorDto = Mapper.mapListaWomaProfAsigToListaInscripcionesProfesor(listaAsignaturasProfesor,listaInscripcionesProfesorDto);
        return listaInscripcionesProfesorDto;
        }

    public List<InscripcionProfesor> getAsignaturasYRelacionesConProfesor(Integer idProfesor){
        List<WomaAsignatura> listaWomaAsignatura = womaAsignaturaFacade.findAll();
        WomaProfesor womaProfesor = womaProfesorFacade.find(idProfesor);
        List<InscripcionProfesor> listaInscripcionesProfesorDto = recuperarAsignacionesProfesor(idProfesor);
        for(WomaAsignatura womaAsignatura: listaWomaAsignatura){
            boolean encontrado = false;
            boolean fin = false;
            int i = 0;
            while(!encontrado && !fin){
                
                if(listaInscripcionesProfesorDto.get(i).getAsignatura().getId().equals(womaAsignatura.getIdAsig())){
                    encontrado = true;
                }
                if(i==(listaInscripcionesProfesorDto.size()-1)){
                    fin = true;
                }
                i++;
            }

            if(!encontrado){
                listaInscripcionesProfesorDto.add(Mapper.mapWomaAsignaturaToInscripcionProfesorDesasignada(womaAsignatura, womaProfesor));
            }

        }
        return listaInscripcionesProfesorDto;

    }
    
    public void procesarListaAsignacionesProfesor(List<InscripcionProfesor> listaInscripcionesProfesorDto){
        
        for(InscripcionProfesor inscripcionProfesor:listaInscripcionesProfesorDto){
            Integer accion = inscibirDesinscribirInscripcionProfesor(inscripcionProfesor);
            if(accion==null){
                logger.info("Relacion ya añadida y no ha cambiado "+inscripcionProfesor);
            }else if(accion<0){
                logger.info("Se ha borrado la relación: "+inscripcionProfesor);
            }else{
                logger.info("Se ha insertado la relacion: "+inscripcionProfesor+" con ID"+accion);
            }
        }
    }

    public Integer inscibirDesinscribirInscripcionProfesor(InscripcionProfesor inscripcionProfesor){
        WomaProfAsig womaProfAsig = null;
        Integer id = null;

        if(inscripcionProfesor.getId()!=null && !inscripcionProfesor.isInscrito()){
            womaProfAsig = womaProfAsigFacade.find(inscripcionProfesor.getId());
            id = (-1)*womaProfAsig.getIdProfAsig();
            womaProfAsigFacade.remove(womaProfAsig);
        }else if(inscripcionProfesor.getId()!=null && inscripcionProfesor.isInscrito()){
            logger.info("Profesor ya inscrito en la asignatura");

        }else{
            womaProfAsig = new WomaProfAsig();
            womaProfAsig = Mapper.mapInscripcionProfesorDtoToWomaProfAsig(womaProfAsig, inscripcionProfesor);
            womaProfAsigFacade.create(womaProfAsig);
            id = womaProfAsig.getIdProfAsig();
        }

        return id;

    }


    public List<InscripcionAlumno> recuperarAsignacionesAlumno(Integer idAlumno){
        WomaAlumno womaAlumno = womaAlumnoFacade.find(idAlumno);

        List<WomaAlumAsig> listaAsignaturasAlumno = womaAlumAsigFacade.findByIdAlum(womaAlumno);
        List<InscripcionAlumno> listaInscripcionesAlumnoDto = new ArrayList<InscripcionAlumno>();
        listaInscripcionesAlumnoDto = Mapper.mapListaWomaAlumAsigToListaInscripcionesAlumno(listaAsignaturasAlumno,listaInscripcionesAlumnoDto);
        return listaInscripcionesAlumnoDto;
        }

    public List<InscripcionAlumno> getAsignaturasYRelacionesConAlumno(Integer idAlumno){
        List<WomaAsignatura> listaWomaAsignatura = womaAsignaturaFacade.findAll();
        WomaAlumno womaAlumno = womaAlumnoFacade.find(idAlumno);
        List<InscripcionAlumno> listaInscripcionesAlumnoDto = recuperarAsignacionesAlumno(idAlumno);
        for(WomaAsignatura womaAsignatura: listaWomaAsignatura){
            boolean encontrado = false;
            boolean fin = false;
            int i = 0;
            while(!encontrado && !fin){
                
                if(listaInscripcionesAlumnoDto.get(i).getAsignatura().getId().equals(womaAsignatura.getIdAsig())){
                    encontrado = true;
                }
                if(i==(listaInscripcionesAlumnoDto.size()-1)){
                    fin = true;
                }
                i++;
            }

            if(!encontrado){
                listaInscripcionesAlumnoDto.add(Mapper.mapWomaAsignaturaYWomaAlumnoToInscripcionAlumnoDesasignada(womaAsignatura, womaAlumno));
            }

        }
        return listaInscripcionesAlumnoDto;

    }


    public void procesarListaAsignacionesAlumno(List<InscripcionAlumno> listaInscripcionesAlumnoDto){
        
        for(InscripcionAlumno inscripcionAlumno:listaInscripcionesAlumnoDto){
            Integer accion = inscibirDesinscribirInscripcionAlumno(inscripcionAlumno);
            if(accion==null){
                logger.info("Relacion ya añadida y no ha cambiado "+inscripcionAlumno);
            }else if(accion<0){
                logger.info("Se ha borrado la relación: "+inscripcionAlumno);
            }else{
                logger.info("Se ha insertado la relacion: "+inscripcionAlumno+" con ID"+accion);
            }
        }
    }

    public Integer inscibirDesinscribirInscripcionAlumno(InscripcionAlumno inscripcionAlumno){
        WomaAlumAsig womaAlumAsig = null;
        Integer id = null;

        if(inscripcionAlumno.getId()!=null && !inscripcionAlumno.isInscrito()){
            womaAlumAsig = womaAlumAsigFacade.find(inscripcionAlumno.getId());
            id = (-1)*womaAlumAsig.getIdAsumAsig();
            womaAlumAsigFacade.remove(womaAlumAsig);
        }else if(inscripcionAlumno.getId()!=null && inscripcionAlumno.isInscrito()){
            logger.info("Alumno ya inscrito en la asignatura");

        }else{
            womaAlumAsig = new WomaAlumAsig();
            womaAlumAsig = Mapper.mapInscripcionAlumnoDtoToWomaAlumAsig(womaAlumAsig, inscripcionAlumno);
            womaAlumAsigFacade.create(womaAlumAsig);
            id = womaAlumAsig.getIdAsumAsig();
        }

        return id;

    }

	
	
}
