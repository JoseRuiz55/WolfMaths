/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.dao;

import com.uma.wolfmaths.entity.WomaAlumAsig;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

/**
 *
 * @author jruiz
 */
@Service
public class WomaAlumAsigFacade extends AbstractFacade<WomaAlumAsig> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WomaAlumAsigFacade() {
        super(WomaAlumAsig.class);
    }
    
}
