package com.uma.wolfmaths.utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uma.wolfmaths.dto.*;
import com.uma.wolfmaths.entity.*;
import com.uma.wolfmaths.form.*;

public class Mapper {

	private static final Logger logger = LoggerFactory.getLogger(Mapper.class);

	/* MAPS PROBLEM DTO TO WOMAPROBLEMA */

	public static final WomaProblema mapProblemDtoToWomaProblema(Problem problemDto) {
		WomaProblema womaProblema = new WomaProblema();
		womaProblema.setEnunciado(problemDto.getStatement());
		womaProblema.setNumVariables(problemDto.getNumVars());

		return womaProblema;

	}

	public static final WomaSolucionProblema paramsToWomaSolucionProblema(String result, int numSteps,
			String comentarioSolucion, String comentarioIntento, String tipoSolucion, int numVars,
			ProblemVariables problemVars, WomaProfesor womaProfesor, WomaAlumno womaAlumno, WomaProblema womaProblema,
			WomaIntentoProblema womaIntentoProblema) {
		WomaSolucionProblema womaSolucionProblema = new WomaSolucionProblema();

		womaSolucionProblema.setComentario(comentarioSolucion);
		womaSolucionProblema.setPasosResolucion(numSteps);
		womaSolucionProblema.setSolucionTotal(result);
		// womaSolucionProblema.setComentario("");
		womaSolucionProblema.setTipoSolucion(tipoSolucion);

		/*
		 * WomaVariablesProblema womaVariablesProblema = new
		 * WomaVariablesProblema(); womaVariablesProblema =
		 * mapProblemVariablesToWomaVariablesProblema(problemVars, numVars);
		 * 
		 * 
		 * WomaIntentoProblema womaIntentoProblema = new WomaIntentoProblema();
		 * womaIntentoProblema = mapParamsToWomaIntentoProblema(numVars,
		 * comentarioIntento, womaVariablesProblema );
		 */

		womaSolucionProblema.setWomaIntentoProblemaId(womaIntentoProblema);

		if (womaAlumno != null && womaProfesor != null) {
			logger.error("Error en la lógica no pueden venir informados ambos actores");
			// throw ExceptionCustom
		} else if (womaAlumno != null) {
			womaSolucionProblema.setWomaAlumnoId(womaAlumno);
		} else if (womaProfesor != null) {
			womaSolucionProblema.setWomaProfesorId(womaProfesor);
		}

		womaSolucionProblema.setWomaProblemaId(womaProblema);

		return womaSolucionProblema;
	}

	public static final WomaIntentoProblema mapParamsToWomaIntentoProblema(int numVars, String comment,
			WomaVariablesProblema womaVariablesProblema) {
		WomaIntentoProblema womaIntentoProblema = new WomaIntentoProblema();

		womaIntentoProblema.setComentario(comment);
		womaIntentoProblema.setNumVariables(numVars);
		womaIntentoProblema.setWomaVariablesProblemaId(womaVariablesProblema);

		return womaIntentoProblema;
	}

	public static final WomaVariablesProblema mapProblemVariablesToWomaVariablesProblema(ProblemVariables problemVars,
			int numVars) {

		WomaVariablesProblema womaVariablesProblema = new WomaVariablesProblema();

		womaVariablesProblema.setXInitial(problemVars.getX());
		womaVariablesProblema.setYInitial(problemVars.getY());
		womaVariablesProblema.setZInitial(problemVars.getZ());
		womaVariablesProblema.setKInitial(problemVars.getK());

		return womaVariablesProblema;

	}

	public static final ArrayList<WomaPasoResolucionProblema> mapProblemParamsToListWomaPasoResolucionProblema(
			ProblemSteps problemSteps, int numSteps) {

		ArrayList<WomaPasoResolucionProblema> listaWomaPasoResolucionProblema = new ArrayList<WomaPasoResolucionProblema>();

		for (int i = 1; i <= numSteps; i++) {
			WomaPasoResolucionProblema womaPasoResolucionProblema = new WomaPasoResolucionProblema();
			womaPasoResolucionProblema = mapProblemStepsDtoToWomaPasoResolucionProblema(problemSteps, i);
			listaWomaPasoResolucionProblema.add(womaPasoResolucionProblema);
		}
		WomaPasoResolucionProblema womaPasoResolucionProblemaFinal = new WomaPasoResolucionProblema();
		womaPasoResolucionProblemaFinal = mapProblemStepsFinalDtoToWomaPasoResolucionProblema(problemSteps,"R",numSteps);
		listaWomaPasoResolucionProblema.add(womaPasoResolucionProblemaFinal);
		return listaWomaPasoResolucionProblema;
	}

	public static final WomaPasoResolucionProblema mapProblemStepsDtoToWomaPasoResolucionProblema(
			ProblemSteps problemSteps, int numStep) {

		WomaPasoResolucionProblema womaPasoResolucionProblema = new WomaPasoResolucionProblema();

		if (numStep == 1) {
			womaPasoResolucionProblema = mapStepDtoToWomaPasoResolucionProblema(problemSteps.getStep1(), numStep);
		} else if (numStep == 2) {
			womaPasoResolucionProblema = mapStepDtoToWomaPasoResolucionProblema(problemSteps.getStep2(), numStep);
		} else if (numStep == 3) {
			womaPasoResolucionProblema = mapStepDtoToWomaPasoResolucionProblema(problemSteps.getStep3(), numStep);
		} else if (numStep == 4) {
			womaPasoResolucionProblema = mapStepDtoToWomaPasoResolucionProblema(problemSteps.getStep4(), numStep);
		}

		return womaPasoResolucionProblema;
	}
	
	public static final WomaPasoResolucionProblema mapProblemStepsFinalDtoToWomaPasoResolucionProblema(
			ProblemSteps problemSteps, String resolution, int numSteps){
		
		WomaPasoResolucionProblema womaPasoResolucionProblema = new WomaPasoResolucionProblema();
		womaPasoResolucionProblema = mapStepFinalDtoToWomaPasoResolucionProblema(problemSteps.getFinalStep(),numSteps,"R");
		return womaPasoResolucionProblema;
	}

	public static final WomaPasoResolucionProblema mapStepDtoToWomaPasoResolucionProblema(Step stepDto, int numStep) {
		WomaPasoResolucionProblema womaPasoResolucionProblema = new WomaPasoResolucionProblema();

		womaPasoResolucionProblema.setComentario(stepDto.getComment());
		womaPasoResolucionProblema.setNumPaso(numStep);
		womaPasoResolucionProblema.setPasoSentencia(stepDto.getStep());

		return womaPasoResolucionProblema;
	}
	
	public static final WomaPasoResolucionProblema mapStepFinalDtoToWomaPasoResolucionProblema(Step stepDto, int numSteps, String resolution) {
		WomaPasoResolucionProblema womaPasoResolucionProblema = new WomaPasoResolucionProblema();

		womaPasoResolucionProblema.setComentario(stepDto.getComment());
		womaPasoResolucionProblema.setNumPaso(numSteps+1);
		womaPasoResolucionProblema.setPasoSentencia(stepDto.getStep());
		womaPasoResolucionProblema.setResolucion(resolution);

		return womaPasoResolucionProblema;
	}

	public static WomaProblema setFKsToWomaProblema(WomaProblema womaProblema, WomaAsignatura womaAsignatura,
			WomaProfesor womaProfesor) {

		womaProblema.setWomaAsignaturaId(womaAsignatura);
		womaProblema.setWomaProfesorId(womaProfesor);

		return womaProblema;
	}

	/* END ----- MAPS PROBLEM DTO TO WOMAPROBLEMA */

	/* MAPS WOMAPROBLEMA TO PROBLEMDTO */
	public static ProblemForm setWomaProblemaToProblemForm(WomaProblema womaProblema, WomaAlumno womaAlumno,
			WomaProfesor womaProfesor, Integer intentoAlumno) {
		ProblemForm problemForm = new ProblemForm();
		problemForm.setProblem(Mapper.setParamsToProblemDto(womaProblema,womaAlumno,womaProfesor,intentoAlumno));

		return problemForm;

	}

	public static Problem setParamsToProblemDto(WomaProblema womaProblema, WomaAlumno womaAlumno,
			WomaProfesor womaProfesor, Integer intentoAlumno) {
		Problem problem = new Problem();

		// Compruebo que soy un profesor
		if (womaAlumno == null || intentoAlumno==null) {
			problem.setComment(womaProblema.getEnunciado());
			problem.setNumVars(womaProblema.getNumVariables());

			List<WomaSolucionProblema> listaWomaSolucionProblema = womaProblema.getWomaSolucionProblemaList();
			WomaSolucionProblema womaSolucionProblema = null;
			boolean solucionEncontrada = false;
			int contadorWomaSolucionProblema = 0;
			while (solucionEncontrada == false) {
				womaSolucionProblema = listaWomaSolucionProblema.get(contadorWomaSolucionProblema);
				if (intentoAlumno == null && womaSolucionProblema.getWomaProfesorId().equals(womaProfesor)) {
					solucionEncontrada = true;
				}else if (intentoAlumno != null && womaSolucionProblema.getIdSoluProb().equals(intentoAlumno)) {
					solucionEncontrada = true;
				} else {
					contadorWomaSolucionProblema++;
				}
			}
			problem = Mapper.setWomaSolucionProblemaToProblemDto(problem, womaSolucionProblema);
		}

		return problem;
	}

	public static Problem setWomaSolucionProblemaToProblemDto(Problem problem,
			WomaSolucionProblema womaSolucionProblema) {
		problem.setIdProblemResolucion(womaSolucionProblema.getIdSoluProb().toString());
		problem.setNumSteps(womaSolucionProblema.getPasosResolucion());
		problem.setResult(Double.valueOf(womaSolucionProblema.getSolucionTotal()));
		// problem.setComment(womaSolucionProblema.getComentario());

		WomaIntentoProblema womaIntentoProblema = womaSolucionProblema.getWomaIntentoProblemaId();

		problem = Mapper.setWomaIntentoProblemaToProblemDto(problem, womaIntentoProblema);

		return problem;
	}

	public static Problem setWomaIntentoProblemaToProblemDto(Problem problem, WomaIntentoProblema womaIntentoProblema) {

		problem.setComment(womaIntentoProblema.getComentario());
		problem.setNumVars(womaIntentoProblema.getNumVariables());

		problem.setVariables(
				Mapper.setWomaIntentoProblemaToProblemVariables(problem.getVariables(), womaIntentoProblema));
		problem.setSteps(Mapper.setWomaIntentoProblemaToProblemSteps(problem.getSteps(), womaIntentoProblema));

		return problem;
	}

	public static ProblemVariables setWomaIntentoProblemaToProblemVariables(ProblemVariables problemVariables,
			WomaIntentoProblema womaIntentoProblema) {
		problemVariables.setX(womaIntentoProblema.getWomaVariablesProblemaId().getXInitial());
		problemVariables.setY(womaIntentoProblema.getWomaVariablesProblemaId().getYInitial());
		problemVariables.setZ(womaIntentoProblema.getWomaVariablesProblemaId().getKInitial());
		problemVariables.setK(womaIntentoProblema.getWomaVariablesProblemaId().getZInitial());

		return problemVariables;

	}

	public static ProblemSteps setWomaIntentoProblemaToProblemSteps(ProblemSteps problemSteps,
			WomaIntentoProblema womaIntentoProblema) {

		List<WomaPasoResolucionProblema> listaWomaPasoResolucionProblema = womaIntentoProblema.getWomaPasoResolucionProblemaList();
		WomaPasoResolucionProblema womaPasoResolucionProblema = null;
		for(int i=0;i<listaWomaPasoResolucionProblema.size();i++){
			womaPasoResolucionProblema = listaWomaPasoResolucionProblema.get(i);
			int numPaso = womaPasoResolucionProblema.getNumPaso();
			String resolution = womaPasoResolucionProblema.getResolucion();
			if(resolution != null && resolution.equals("R")){
				problemSteps = Mapper.setParamsToProblemStepFinalSingular(problemSteps,numPaso,womaPasoResolucionProblema);
			}else{
				problemSteps = Mapper.setParamsToProblemStepSingular(problemSteps,numPaso,womaPasoResolucionProblema);
			}
			
		}
		
		return problemSteps;

	}
	
	public static ProblemSteps setParamsToProblemStepSingular (ProblemSteps problemSteps, int numPaso, WomaPasoResolucionProblema womaPasoResolucionProblema){
		if(womaPasoResolucionProblema.getNumPaso()==1){
			problemSteps.setStep1(Mapper.setWomaPasoResolucionProblemaToStepDto(problemSteps.getStep1(),womaPasoResolucionProblema));
		}else if(womaPasoResolucionProblema.getNumPaso()==2){
			problemSteps.setStep2(Mapper.setWomaPasoResolucionProblemaToStepDto(problemSteps.getStep2(),womaPasoResolucionProblema));
		}else if(womaPasoResolucionProblema.getNumPaso()==3){
			problemSteps.setStep3(Mapper.setWomaPasoResolucionProblemaToStepDto(problemSteps.getStep3(),womaPasoResolucionProblema));
		}else if(womaPasoResolucionProblema.getNumPaso()==4){
			problemSteps.setStep4(Mapper.setWomaPasoResolucionProblemaToStepDto(problemSteps.getStep4(),womaPasoResolucionProblema));
		}
		return problemSteps;
		
	}
	
	public static ProblemSteps setParamsToProblemStepFinalSingular (ProblemSteps problemSteps, int numPaso, WomaPasoResolucionProblema womaPasoResolucionProblema){
		
		problemSteps.setFinalStep(Mapper.setWomaPasoResolucionProblemaToStepDto(problemSteps.getFinalStep(),womaPasoResolucionProblema));

		return problemSteps;
		
	}
	
	public static Step setWomaPasoResolucionProblemaToStepDto (Step step, WomaPasoResolucionProblema womaPasoResolucionProblema){
		step.setComment(womaPasoResolucionProblema.getComentario());
		step.setStep(womaPasoResolucionProblema.getPasoSentencia());
		return step;
	}

	/* END ----- MAPS PROBLEM DTO TO WOMAPROBLEMA */
	
	public static Alumno mapWomaAlumnoToAlumnoDto(WomaAlumno womaAlumno, Alumno alumno){
		
		alumno.setId(womaAlumno.getIdAlum());
		alumno.setNombre(womaAlumno.getNombre());
		alumno.setApellido1(womaAlumno.getApellido1());
		alumno.setApellido2(womaAlumno.getApellido2());
		alumno.setUsername(womaAlumno.getUsername());
		alumno.setEmail(womaAlumno.getEmail());
		alumno.setTelefono(womaAlumno.getTelefono());
		return alumno;
	}
	
	public static Profesor mapWomaProfesorToProfesorDto(WomaProfesor womaProfesor, Profesor profesor){
		
		profesor.setId(womaProfesor.getIdProf());
		profesor.setNombre(womaProfesor.getNombre());
		profesor.setApellido1(womaProfesor.getApellido1());
		profesor.setApellido2(womaProfesor.getApellido2());
		profesor.setUsername(womaProfesor.getUsername());
		profesor.setEmail(womaProfesor.getEmail());
		profesor.setTelefono(womaProfesor.getTelefono());
		
		return profesor;
	}
	
	public static List<TareaAlumno> mapListaWomaProblemaToListaTareasAlumno(List<WomaProblema> listaWomaProblema){
		List<TareaAlumno> listaTareaAlumno = new ArrayList<TareaAlumno>();
		for(int i=0;i<listaWomaProblema.size();i++){
			TareaAlumno tareaAlumno = new TareaAlumno();
			tareaAlumno = Mapper.mapWomaProblemaToTareaAlumno(tareaAlumno,listaWomaProblema.get(i));
			listaTareaAlumno.add(tareaAlumno);
		}
		return listaTareaAlumno;
	}
	
	public static TareaAlumno mapWomaProblemaToTareaAlumno(TareaAlumno tareaAlumno, WomaProblema womaProblema){
		tareaAlumno.setProfesor(Mapper.mapWomaProfesorToProfesorDto(womaProblema.getWomaProfesorId(), new Profesor()));
		tareaAlumno.setResumen(womaProblema.getEnunciado());
		tareaAlumno.setId(womaProblema.getIdProb());
		tareaAlumno.setIdString(womaProblema.getIdProb().toString());
		tareaAlumno.setAsignatura(Mapper.mapWomaAsignaturaToAsignaturaDto(womaProblema.getWomaAsignaturaId(),tareaAlumno.getAsignatura()));
		return tareaAlumno;
	}
	
	public static Asignatura mapWomaAsignaturaToAsignaturaDto(WomaAsignatura womaAsignatura, Asignatura asignatura){
		asignatura.setId(womaAsignatura.getIdAsig());
		asignatura.setDepartamento(womaAsignatura.getDepartamento());
		asignatura.setNombre(womaAsignatura.getNombre());
		asignatura.setMaxAlum(womaAsignatura.getNumMaxAlum());
		
		return asignatura;
	}

	public static ProblemaCorreccionAlumno mapWomaSolucionProblemaToProblemaCorreccionAlumno(
			WomaSolucionProblema womaSolucionProblema, ProblemaCorreccionAlumno problemaCorreccionAlumno) {
		
		problemaCorreccionAlumno.setComentario(womaSolucionProblema.getComentario());
		problemaCorreccionAlumno.setId(womaSolucionProblema.getIdSoluProb());
		problemaCorreccionAlumno.setIdString(womaSolucionProblema.getIdSoluProb().toString());
		problemaCorreccionAlumno.setPasosResolucion(womaSolucionProblema.getPasosResolucion());
		problemaCorreccionAlumno.setResultado(womaSolucionProblema.getSolucionTotal());
		
		return problemaCorreccionAlumno;
	}

	public static Correccion mapWomaCorrecionToCorrecionDto(WomaCorreccion womaCorreccion, Correccion correccion) {
		
		correccion.setId(womaCorreccion.getIdCorr());
		correccion.setIdString(womaCorreccion.getIdCorr().toString());
		correccion.setComentario(womaCorreccion.getComentario());
		correccion.setNota(womaCorreccion.getNota());
		return correccion;
	}

}
