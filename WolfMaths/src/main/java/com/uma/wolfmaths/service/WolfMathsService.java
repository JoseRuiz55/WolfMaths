package com.uma.wolfmaths.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.uma.wolfmaths.dao.*;
import com.uma.wolfmaths.dto.*;
import com.uma.wolfmaths.entity.*;
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
	protected WomaSolucionProblemaFacade womaSolcionProblemaFacade;
	
	@Autowired
	protected WomaVariablesProblemaFacade womaVariablesProblemaFacade;
	
	public void createProblem(Problem problem, Subject asignatura, Professor professor){
		try{
		List <WomaAlumno> lista = null;
		try{
			lista = womaAlumnoFacade.findAll();
		}catch (Exception e){
			logger.info(e.getMessage());
		}		
		WomaAsignatura womaAsignatura = womaAsignaturaFacade.findAll().get(0);
		//WomaAlumno womaAlumno = womaAlumnoFacade.find(alumno.getId());
		WomaProfesor womaProfesor = womaProfesorFacade.find(1);
		
		WomaProblema womaProblema = Mapper.mapProblemDtoToWomaProblema(problem);
		womaProblema = Mapper.setFKsToWomaProblema(womaProblema, womaAsignatura, womaProfesor);
		//womaProblema.setIdProb(1);
		
		womaProblemaFacade.create(womaProblema);
		}
		catch(Exception e){
			logger.info("error: "+e.getMessage());
		}
		logger.info("Problema Creado");
	}
	
	

}
