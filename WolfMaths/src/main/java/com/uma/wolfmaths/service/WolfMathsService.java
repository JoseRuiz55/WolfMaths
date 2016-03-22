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
	protected WomaProfAsigFacade womaProfAsigacade;
	
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
}
