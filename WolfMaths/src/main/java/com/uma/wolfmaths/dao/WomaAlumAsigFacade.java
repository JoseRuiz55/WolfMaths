/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.dao;

import com.uma.wolfmaths.entity.WomaAlumAsig;
import com.uma.wolfmaths.entity.WomaAlumno;
import com.uma.wolfmaths.entity.WomaAsignatura;

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
public class WomaAlumAsigFacade extends AbstractFacade<WomaAlumAsig> {
	
	private static final Logger logger = LoggerFactory.getLogger(WomaAlumAsigFacade.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WomaAlumAsigFacade() {
        super(WomaAlumAsig.class);
    }
    
    public WomaAlumAsig findByAsigAndAlum(WomaAsignatura asignatura, WomaAlumno alumno){

        WomaAlumAsig womaAlumAsig = null;
        javax.persistence.Query q = getEntityManager().createNamedQuery("WomaProfAsig.findByAsigAndAlum", WomaAlumAsig.class).setParameter("womaAsignaturaId", asignatura).setParameter("womaAlumnoId",alumno);
        List<WomaAlumAsig> listaWomaAlumAsig = q.getResultList();
        if(listaWomaAlumAsig.isEmpty()){
            logger.info("Inscripcion del Alumno no encontrada");

        }else if(!listaWomaAlumAsig.isEmpty() && listaWomaAlumAsig.size()!=1){
            logger.error("Inscripcion del Alumno encontrada con duplicidad. ERROR EN BBDD. Recuperamos la primera");
            womaAlumAsig = listaWomaAlumAsig.get(0);
        }else{
            womaAlumAsig = listaWomaAlumAsig.get(0);
        }
        
        return womaAlumAsig;
    }
    
    public List<WomaAlumAsig> findByIdAlum(WomaAlumno alumno){

        javax.persistence.Query q = getEntityManager().createNamedQuery("WomaAlumAsig.findByIdProfAsig", WomaAlumAsig.class).setParameter("womaAlumnoId",alumno);
        List<WomaAlumAsig> listaAlumProfAsig = q.getResultList();
        logger.info("Se han recuperado "+listaAlumProfAsig.size()+" asignaturas para el alumno"+alumno.getNombre()+alumno.getApellido1());
        
        return listaAlumProfAsig;
    }
    
}
