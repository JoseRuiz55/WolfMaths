/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.dao;

import com.uma.wolfmaths.entity.WomaAlumno;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jruiz
 */
@Service
public class WomaAlumnoFacade extends AbstractFacade<WomaAlumno> {

    @PersistenceContext
    private EntityManager em;
    
    private static final Logger logger = LoggerFactory.getLogger(WomaAlumnoFacade.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WomaAlumnoFacade() {
        super(WomaAlumno.class);
    }
    
    
    public WomaAlumno findAlumnoByUsername(String username){

    	WomaAlumno alumno = null;
    	javax.persistence.Query q = getEntityManager().createNamedQuery("WomaAlumno.findByUsername", WomaAlumno.class).setParameter("username", username);
    	List<WomaAlumno> listaAlumno = q.getResultList();
    	if(listaAlumno.isEmpty()){
    		logger.info("Alumno no encontrado");

    	}else if(!listaAlumno.isEmpty() && listaAlumno.size()!=1){
    		logger.error("Alumno encontrado con duplicidad. ERROR EN BBDD. Recuperamos el primero");
    		alumno = listaAlumno.get(0);
    	}else{
    		alumno = listaAlumno.get(0);
    	}
    	
    	return alumno;
    }
    
    public WomaAlumno findAlumnoByUsernameAndPassword(String username, String password){

    	WomaAlumno alumno = null;
    	javax.persistence.Query q = getEntityManager().createNamedQuery("WomaAlumno.findByUsernameAndPassword", WomaAlumno.class).setParameter("username", username).setParameter("password", password);
    	List<WomaAlumno> listaAlumno = q.getResultList();
    	if(listaAlumno.isEmpty()){
    		logger.info("Alumno no encontrado");

    	}else if(!listaAlumno.isEmpty() && listaAlumno.size()!=1){
    		logger.error("Alumno encontrado con duplicidad. ERROR EN BBDD. Recuperamos el primero");
    		alumno = listaAlumno.get(0);
    	}else{
    		alumno = listaAlumno.get(0);
    	}
    	
    	return alumno;
    }
    
    
    
}
