/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.dao;

import com.uma.wolfmaths.entity.WomaSolucionProblema;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

/**
 *
 * @author jruiz
 */
@Service
public class WomaSolucionProblemaFacade extends AbstractFacade<WomaSolucionProblema> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WomaSolucionProblemaFacade() {
        super(WomaSolucionProblema.class);
    }
    
}
