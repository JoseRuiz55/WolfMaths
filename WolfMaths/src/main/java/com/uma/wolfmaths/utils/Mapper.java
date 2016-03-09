package com.uma.wolfmaths.utils;

import java.util.ArrayList;

import org.hibernate.mapping.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uma.wolfmaths.dto.Problem;
import com.uma.wolfmaths.dto.ProblemSteps;
import com.uma.wolfmaths.dto.ProblemVariables;
import com.uma.wolfmaths.dto.Step;
import com.uma.wolfmaths.entity.WomaAlumno;
import com.uma.wolfmaths.entity.WomaAsignatura;
import com.uma.wolfmaths.entity.WomaIntentoProblema;
import com.uma.wolfmaths.entity.WomaPasoResolucionProblema;
import com.uma.wolfmaths.entity.WomaProblema;
import com.uma.wolfmaths.entity.WomaProfesor;
import com.uma.wolfmaths.entity.WomaSolucionProblema;
import com.uma.wolfmaths.entity.WomaVariablesProblema;

public class Mapper {
	
	private static final Logger logger = LoggerFactory.getLogger(Mapper.class);
	
	/*MAPS PROBLEM DTO TO WOMAPROBLEMA*/
	
	public static final WomaProblema mapProblemDtoToWomaProblema (Problem problemDto){
		WomaProblema womaProblema = new WomaProblema();
		womaProblema.setEnunciado(problemDto.getStatement());
		womaProblema.setNumVariables(problemDto.getNumVars());
		
		return womaProblema;
		
	}
	
	public static final WomaSolucionProblema paramsToWomaSolucionProblema (String result, int numSteps, String comentarioSolucion, String comentarioIntento, String tipoSolucion, 
			int numVars, ProblemVariables problemVars, WomaProfesor womaProfesor, WomaAlumno womaAlumno, WomaProblema womaProblema){
		WomaSolucionProblema womaSolucionProblema = new WomaSolucionProblema();
		
		womaSolucionProblema.setComentario(comentarioSolucion);
		womaSolucionProblema.setPasosResolucion(numSteps);
		womaSolucionProblema.setSolucionTotal(result);
		//womaSolucionProblema.setComentario("");
		womaSolucionProblema.setTipoSolucion(tipoSolucion);
		
		
		WomaVariablesProblema womaVariablesProblema = new WomaVariablesProblema();
		womaVariablesProblema = mapProblemVariablesToWomaVariablesProblema(problemVars, numVars);
		
		
		WomaIntentoProblema womaIntentoProblema = new WomaIntentoProblema();
		womaIntentoProblema = mapParamsToWomaIntentoProblema(numVars, comentarioIntento, womaVariablesProblema );
		
		womaSolucionProblema.setWomaIntentoProblemaId(womaIntentoProblema);
		
		if(womaAlumno!=null && womaProfesor!= null)
		{
			logger.error("Error en la lógica no pueden venir informados ambos actores");
			//throw ExceptionCustom
		}else if(womaAlumno!=null){
			womaSolucionProblema.setWomaAlumnoId(womaAlumno);
		}else if (womaProfesor!=null){
			womaSolucionProblema.setWomaProfesorId(womaProfesor);
		}
		
		womaSolucionProblema.setWomaProblemaId(womaProblema);
		
		return womaSolucionProblema;
	}
	
	public static final WomaIntentoProblema mapParamsToWomaIntentoProblema (int numVars, String comment, WomaVariablesProblema womaVariablesProblema){
		WomaIntentoProblema womaIntentoProblema = new WomaIntentoProblema();
		
		womaIntentoProblema.setComentario(comment);
		womaIntentoProblema.setNumVariables(numVars);
		womaIntentoProblema.setWomaVariablesProblemaId(womaVariablesProblema);
		
		return womaIntentoProblema;
	}
	
	public static final WomaVariablesProblema mapProblemVariablesToWomaVariablesProblema (ProblemVariables problemVars, int numVars){
			
			WomaVariablesProblema womaVariablesProblema = new WomaVariablesProblema();
			
			womaVariablesProblema.setXInitial(problemVars.getX());
			womaVariablesProblema.setYInitial(problemVars.getY());
			womaVariablesProblema.setZInitial(problemVars.getZ());
			womaVariablesProblema.setKInitial(problemVars.getK());
			
			return womaVariablesProblema;
			
		}
	
	public static final ArrayList<WomaPasoResolucionProblema> mapProblemParamsToListWomaPasoResolucionProblema (ProblemSteps problemSteps, int numSteps){
		
		ArrayList<WomaPasoResolucionProblema> listaWomaPasoResolucionProblema = new ArrayList<WomaPasoResolucionProblema>();
		
		for(int i = 1;i<numSteps;i++){
			WomaPasoResolucionProblema womaPasoResolucionProblema = new WomaPasoResolucionProblema();
			womaPasoResolucionProblema = mapProblemStepsDtoToWomaPasoResolucionProblema(problemSteps,i);
			listaWomaPasoResolucionProblema.add(womaPasoResolucionProblema);
		}
		return listaWomaPasoResolucionProblema;
	}
	
	
	public static final WomaPasoResolucionProblema mapProblemStepsDtoToWomaPasoResolucionProblema (ProblemSteps problemSteps, int numStep){
		
		WomaPasoResolucionProblema womaPasoResolucionProblema = new WomaPasoResolucionProblema();
			
		if(numStep==1){
				womaPasoResolucionProblema = mapStepDtoToWomaPasoResolucionProblema(problemSteps.getStep1(), numStep);
		}else if(numStep==2){
				womaPasoResolucionProblema = mapStepDtoToWomaPasoResolucionProblema(problemSteps.getStep2(), numStep);
		}else if(numStep==3){
				womaPasoResolucionProblema = mapStepDtoToWomaPasoResolucionProblema(problemSteps.getStep3(), numStep);
		}else if(numStep==4){
				womaPasoResolucionProblema = mapStepDtoToWomaPasoResolucionProblema(problemSteps.getStep4(), numStep);
		}
			
			return womaPasoResolucionProblema;
	}
	
	public static final WomaPasoResolucionProblema mapStepDtoToWomaPasoResolucionProblema(Step stepDto, int numStep){
		WomaPasoResolucionProblema womaPasoResolucionProblema = new WomaPasoResolucionProblema();
		
		womaPasoResolucionProblema.setComentario(stepDto.getComment());
		womaPasoResolucionProblema.setNumPaso(numStep);
		womaPasoResolucionProblema.setPasoSentencia(stepDto.getStep());
		
		return womaPasoResolucionProblema;
	}
	
	public static WomaProblema setFKsToWomaProblema(WomaProblema womaProblema, WomaAsignatura womaAsignatura, WomaProfesor womaProfesor){
			
			womaProblema.setWomaAsignaturaId(womaAsignatura);
			womaProblema.setWomaProfesorId(womaProfesor);
			
			return womaProblema;
		}
	
	/*END ----- MAPS PROBLEM DTO TO WOMAPROBLEMA*/
	
	/*MAPS WOMAPROBLEMA TO PROBLEMDTO*/
	
	/*END ----- MAPS PROBLEM DTO TO WOMAPROBLEMA*/
	
	
	

}
