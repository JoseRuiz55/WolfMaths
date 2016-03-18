/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.dao;

import com.uma.wolfmaths.entity.WomaAlumno;
import com.uma.wolfmaths.entity.WomaProblema;
import com.uma.wolfmaths.entity.WomaSolucionProblema;

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
public class WomaSolucionProblemaFacade extends AbstractFacade<WomaSolucionProblema> {

    @PersistenceContext
    private EntityManager em;
    
    private static final Logger logger = LoggerFactory.getLogger(WomaSolucionProblema.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WomaSolucionProblemaFacade() {
        super(WomaSolucionProblema.class);
    }
    
    public List<WomaSolucionProblema> getListaWomaSolucionProblemaAlumnos(WomaProblema womaProblema){

    	javax.persistence.Query q = getEntityManager().createNamedQuery("WomaSolucionProblema.getSolucionProblemaAlum", WomaSolucionProblema.class).setParameter("womaProblema", womaProblema);
    	List<WomaSolucionProblema> listaWomaSolucionProblema = q.getResultList();
    	if(listaWomaSolucionProblema.isEmpty()){
    		logger.info("Alumno no encontrado");

    	}
    	
    	return listaWomaSolucionProblema;
    }
    
}
