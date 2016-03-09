package com.uma.wolfmaths.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uma.wolfmaths.dao.*;
import com.uma.wolfmaths.dto.*;
import com.uma.wolfmaths.entity.*;
import com.uma.wolfmaths.utils.Mapper;


@Service
public class WolfMathsService {
	
	private static final Logger logger = LoggerFactory.getLogger(WolfMathsService.class);
	
	@Autowired
	protected static WomaAlumAsigFacade womaAlumAsigFacade;
	
	@Autowired
	protected static WomaAlumnoFacade womaAlumnoFacade;
	
	@Autowired
	protected static WomaAsignaturaFacade womaAsignaturaFacade;
	
	@Autowired
	protected static WomaIntentoProblemaFacade womaIntentoProblemaFacade;
	
	@Autowired
	protected static WomaPasoResolucionProblemaFacade womaPasoResolucionProblemaFacade;
	
	@Autowired
	protected static WomaProblemaFacade womaProblemaFacade;

	@Autowired
	protected static WomaProfAsigFacade womaProfAsigacade;
	
	@Autowired
	protected static WomaProfesorFacade womaProfesorFacade;
	
	@Autowired
	protected static WomaSolucionProblemaFacade womaSolcionProblemaFacade;
	
	@Autowired
	protected static WomaVariablesProblemaFacade womaVariablesProblemaFacade;
	
	public static void createProblem(Problem problem, Subject asignatura, Professor professor){
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
		
		
		womaProblemaFacade.create(womaProblema);
		}
		catch(Exception e){
			logger.info("error: "+e.getMessage());
		}
		logger.info("Problema Creado");
	}
	
	

}
