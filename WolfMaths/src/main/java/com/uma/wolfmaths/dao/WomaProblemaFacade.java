/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.dao;

import com.uma.wolfmaths.entity.WomaAlumno;
import com.uma.wolfmaths.entity.WomaProblema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jruiz
 */
@Service
public class WomaProblemaFacade extends AbstractFacade<WomaProblema> {

	
	private static final Logger logger = LoggerFactory.getLogger(WomaProblemaFacade.class);
	
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WomaProblemaFacade() {
        super(WomaProblema.class);
    }
    
    public List<WomaProblema> findWomaProblemaByIdAsignatura(Integer idAsignatura){

    	javax.persistence.Query q = getEntityManager().createNamedQuery("WomaProblema.findByWomaAsignaturaId", WomaProblema.class).setParameter("idAsignatura", idAsignatura);
    	List<WomaProblema> listaWomaProblema = q.getResultList();
    	if(listaWomaProblema.isEmpty()){
    		logger.info("No se ha encontrado ningun problema asociado a esa asignatura");
    	}
    	
    	return listaWomaProblema;
    }
    
    public List<WomaProblema> findWomaProblemaByIdProfesor(Integer idProfesor){

    	javax.persistence.Query q = getEntityManager().createNamedQuery("WomaProblema.findByWomaProfesorId", WomaProblema.class).setParameter("idProfesor", idProfesor);
    	List<WomaProblema> listaWomaProblema = q.getResultList();
    	if(listaWomaProblema.isEmpty()){
    		logger.info("No se ha encontrado ningun problema asociado a esa asignatura");
    	}
    	
    	return listaWomaProblema;
    }
    
}
