/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.dao;

import com.uma.wolfmaths.entity.WomaAlumno;
import com.uma.wolfmaths.entity.WomaCorreccion;
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
public class WomaCorreccionFacade extends AbstractFacade<WomaCorreccion> {

    @PersistenceContext
    private EntityManager em;
    
    private static final Logger logger = LoggerFactory.getLogger(WomaCorreccionFacade.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WomaCorreccionFacade() {
        super(WomaCorreccion.class);
    }
    
    public WomaCorreccion findByWomaSolucionProblema (WomaSolucionProblema womaSolucionProblema){
    	
    	WomaCorreccion womaCorreccion = null;
    	javax.persistence.Query q = getEntityManager().createNamedQuery("WomaCorreccion.findByWomaSolucionProblema", WomaCorreccion.class).setParameter("womaSolucionProblemaId", womaSolucionProblema);
    	List<WomaCorreccion> listaWomaCorreccion = q.getResultList();
    	if(listaWomaCorreccion.isEmpty()){
    		logger.info("Correccion no encontrada");

    	}else if(!listaWomaCorreccion.isEmpty() && listaWomaCorreccion.size()!=1){
    		logger.error("Dos Correcciones leidas en BBDD. ERROR EN BBDD. Recuperamos el primero");
    		womaCorreccion = listaWomaCorreccion.get(0);
    	}else{
    		womaCorreccion = listaWomaCorreccion.get(0);
    	}
    	
    	return womaCorreccion;
    }
    
}
