/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.dao;

import com.uma.wolfmaths.entity.WomaAlumno;
import com.uma.wolfmaths.entity.WomaProfesor;

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
public class WomaProfesorFacade extends AbstractFacade<WomaProfesor> {

    @PersistenceContext
    private EntityManager em;
    
    private static final Logger logger = LoggerFactory.getLogger(WomaProfesorFacade.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WomaProfesorFacade() {
        super(WomaProfesor.class);
    }
    
    public WomaProfesor findProfesorByUsername(String username){

    	WomaProfesor profesor = null;
    	javax.persistence.Query q = getEntityManager().createNamedQuery("WomaProfesor.findByUsername", WomaProfesor.class).setParameter("username", username);
    	List<WomaProfesor> listaProfesor = q.getResultList();
    	if(listaProfesor.isEmpty()){
    		logger.info("Profesor no encontrado");

    	}else if(!listaProfesor.isEmpty() && listaProfesor.size()!=1){
    		logger.error("Profesor encontrado con duplicidad. ERROR EN BBDD. Recuperamos el primero");
    		profesor = listaProfesor.get(0);
    	}else{
    		profesor = listaProfesor.get(0);
    	}
    	
    	return profesor;
    }
    
    public WomaProfesor findProfesorByUsernameAndPassword(String username, String password){

    	WomaProfesor profesor = null;
    	javax.persistence.Query q = getEntityManager().createNamedQuery("WomaProfesor.findByUsernameAndPassword", WomaProfesor.class).setParameter("username", username).setParameter("password", password);
    	List<WomaProfesor> listaProfesor = q.getResultList();
    	if(listaProfesor.isEmpty()){
    		logger.info("Profesor no encontrado");

    	}else if(!listaProfesor.isEmpty() && listaProfesor.size()!=1){
    		logger.error("Profesor encontrado con duplicidad. ERROR EN BBDD. Recuperamos el primero");
    		profesor = listaProfesor.get(0);
    	}else{
    		profesor = listaProfesor.get(0);
    	}
    	
    	return profesor;
    }
}
