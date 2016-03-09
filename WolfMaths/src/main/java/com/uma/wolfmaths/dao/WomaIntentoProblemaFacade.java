/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.dao;

import com.uma.wolfmaths.entity.WomaIntentoProblema;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jruiz
 */
@Service
public class WomaIntentoProblemaFacade extends AbstractFacade<WomaIntentoProblema> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WomaIntentoProblemaFacade() {
        super(WomaIntentoProblema.class);
    }
    
}
