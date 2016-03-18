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
@Table(name = "woma_problema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WomaProblema.findAll", query = "SELECT w FROM WomaProblema w"),
    @NamedQuery(name = "WomaProblema.findByIdProb", query = "SELECT w FROM WomaProblema w WHERE w.idProb = :idProb"),
    @NamedQuery(name = "WomaProblema.findByEnunciado", query = "SELECT w FROM WomaProblema w WHERE w.enunciado = :enunciado"),
    @NamedQuery(name = "WomaProblema.findByNumVariables", query = "SELECT w FROM WomaProblema w WHERE w.numVariables = :numVariables"),
    @NamedQuery(name = "WomaProblema.findByWomaAsignaturaId", query = "SELECT w FROM WomaProblema w WHERE w.womaAsignaturaId.idAsig = :idAsignatura"),
    @NamedQuery(name = "WomaProblema.findByWomaProfesorId", query = "SELECT w FROM WomaProblema w WHERE w.womaProfesorId.idProf = :idProfesor")})
public class WomaProblema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROB")
    private Integer idProb;
    @Basic(optional = false)
    @Size(max = 2500)
    @Column(name = "ENUNCIADO")
    private String enunciado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_VARIABLES")
    private int numVariables;
    @JoinColumn(name = "WOMA_ASIGNATURA_ID", referencedColumnName = "ID_ASIG")
    @ManyToOne(optional = false)
    private WomaAsignatura womaAsignaturaId;
    @JoinColumn(name = "WOMA_PROFESOR_ID", referencedColumnName = "ID_PROF")
    @ManyToOne(optional = false)
    private WomaProfesor womaProfesorId;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "womaProblemaId")
    private List<WomaSolucionProblema> womaSolucionProblemaList;

    public WomaProblema() {
    }

    public WomaProblema(Integer idProb) {
        this.idProb = idProb;
    }

    public WomaProblema(Integer idProb, String enunciado, int numVariables) {
        this.idProb = idProb;
        this.enunciado = enunciado;
        this.numVariables = numVariables;
    }

    public Integer getIdProb() {
        return idProb;
    }

    public void setIdProb(Integer idProb) {
        this.idProb = idProb;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getNumVariables() {
        return numVariables;
    }

    public void setNumVariables(int numVariables) {
        this.numVariables = numVariables;
    }

    public WomaAsignatura getWomaAsignaturaId() {
        return womaAsignaturaId;
    }

    public void setWomaAsignaturaId(WomaAsignatura womaAsignaturaId) {
        this.womaAsignaturaId = womaAsignaturaId;
    }

    public WomaProfesor getWomaProfesorId() {
        return womaProfesorId;
    }

    public void setWomaProfesorId(WomaProfesor womaProfesorId) {
        this.womaProfesorId = womaProfesorId;
    }

    @XmlTransient
    public List<WomaSolucionProblema> getWomaSolucionProblemaList() {
        return womaSolucionProblemaList;
    }

    public void setWomaSolucionProblemaList(List<WomaSolucionProblema> womaSolucionProblemaList) {
        this.womaSolucionProblemaList = womaSolucionProblemaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProb != null ? idProb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WomaProblema)) {
            return false;
        }
        WomaProblema other = (WomaProblema) object;
        if ((this.idProb == null && other.idProb != null) || (this.idProb != null && !this.idProb.equals(other.idProb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uma.wolfmaths.entity.WomaProblema[ idProb=" + idProb + " ]";
    }
    
}
