/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.dao;

import com.uma.wolfmaths.entity.WomaAsignatura;
import com.uma.wolfmaths.entity.WomaProfAsig;
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
public class WomaProfAsigFacade extends AbstractFacade<WomaProfAsig> {

	
	private static final Logger logger = LoggerFactory.getLogger(WomaProfAsigFacade.class);
	
    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WomaProfAsigFacade() {
        super(WomaProfAsig.class);
    }
    
    public WomaProfAsig findByAsigAndProf(WomaAsignatura asignatura, WomaProfesor profesor){

        WomaProfAsig womaProfAsig = null;
        javax.persistence.Query q = getEntityManager().createNamedQuery("WomaProfAsig.findByAsigAndProf", WomaProfAsig.class).setParameter("womaAsignaturaId", asignatura).setParameter("idProfAsig",profesor);
        List<WomaProfAsig> listaWomaProfAsig = q.getResultList();
        if(listaWomaProfAsig.isEmpty()){
            logger.info("Inscripcion del profesor no encontrada");

        }else if(!listaWomaProfAsig.isEmpty() && listaWomaProfAsig.size()!=1){
            logger.error("Inscripcion del profesor encontrada con duplicidad. ERROR EN BBDD. Recuperamos la primera");
            womaProfAsig = listaWomaProfAsig.get(0);
        }else{
            womaProfAsig = listaWomaProfAsig.get(0);
        }
        
        return womaProfAsig;
    }
    
    public List<WomaProfAsig> findByAsigAndProf(WomaProfesor profesor){

        javax.persistence.Query q = getEntityManager().createNamedQuery("WomaProfAsig.findByIdProfAsig", WomaProfAsig.class).setParameter("idProfAsig",profesor);
        List<WomaProfAsig> listaWomaProfAsig = q.getResultList();
        logger.info("Se han recuperado "+listaWomaProfAsig.size()+" asignaturas para el profesor"+profesor.getNombre()+" "+profesor.getApellido1());
        
        return listaWomaProfAsig;
    }
    
}
