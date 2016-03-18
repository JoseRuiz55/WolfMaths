/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jruiz
 */
@Entity
@Table(name = "woma_intento_problema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WomaIntentoProblema.findAll", query = "SELECT w FROM WomaIntentoProblema w"),
    @NamedQuery(name = "WomaIntentoProblema.findByIdInteProb", query = "SELECT w FROM WomaIntentoProblema w WHERE w.idInteProb = :idInteProb"),
    @NamedQuery(name = "WomaIntentoProblema.findByNumVariables", query = "SELECT w FROM WomaIntentoProblema w WHERE w.numVariables = :numVariables"),
    @NamedQuery(name = "WomaIntentoProblema.findByComentario", query = "SELECT w FROM WomaIntentoProblema w WHERE w.comentario = :comentario")})
public class WomaIntentoProblema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_INTE_PROB")
    private Integer idInteProb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_VARIABLES")
    private int numVariables;
    @Basic(optional = false)
    @Size(max = 2000)
    @Column(name = "COMENTARIO")
    private String comentario;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "womaIntentoProblemaId")
    private List<WomaPasoResolucionProblema> womaPasoResolucionProblemaList;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "womaIntentoProblemaId")
    private List<WomaSolucionProblema> womaSolucionProblemaList;
    @JoinColumn(name = "WOMA_VARIABLES_PROBLEMA_ID", referencedColumnName = "ID_VARIABLES_PROBLEMA")
    @ManyToOne(optional = false)
    private WomaVariablesProblema womaVariablesProblemaId;

    public WomaIntentoProblema() {
    }

    public WomaIntentoProblema(Integer idInteProb) {
        this.idInteProb = idInteProb;
    }

    public WomaIntentoProblema(Integer idInteProb, int numVariables, String comentario) {
        this.idInteProb = idInteProb;
        this.numVariables = numVariables;
        this.comentario = comentario;
    }

    public Integer getIdInteProb() {
        return idInteProb;
    }

    public void setIdInteProb(Integer idInteProb) {
        this.idInteProb = idInteProb;
    }

    public int getNumVariables() {
        return numVariables;
    }

    public void setNumVariables(int numVariables) {
        this.numVariables = numVariables;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @XmlTransient
    public List<WomaPasoResolucionProblema> getWomaPasoResolucionProblemaList() {
        return womaPasoResolucionProblemaList;
    }

    public void setWomaPasoResolucionProblemaList(List<WomaPasoResolucionProblema> womaPasoResolucionProblemaList) {
        this.womaPasoResolucionProblemaList = womaPasoResolucionProblemaList;
    }

    @XmlTransient
    public List<WomaSolucionProblema> getWomaSolucionProblemaList() {
        return womaSolucionProblemaList;
    }

    public void setWomaSolucionProblemaList(List<WomaSolucionProblema> womaSolucionProblemaList) {
        this.womaSolucionProblemaList = womaSolucionProblemaList;
    }

    public WomaVariablesProblema getWomaVariablesProblemaId() {
        return womaVariablesProblemaId;
    }

    public void setWomaVariablesProblemaId(WomaVariablesProblema womaVariablesProblemaId) {
        this.womaVariablesProblemaId = womaVariablesProblemaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInteProb != null ? idInteProb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WomaIntentoProblema)) {
            return false;
        }
        WomaIntentoProblema other = (WomaIntentoProblema) object;
        if ((this.idInteProb == null && other.idInteProb != null) || (this.idInteProb != null && !this.idInteProb.equals(other.idInteProb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uma.wolfmaths.entity.WomaIntentoProblema[ idInteProb=" + idInteProb + " ]";
    }
    
}
