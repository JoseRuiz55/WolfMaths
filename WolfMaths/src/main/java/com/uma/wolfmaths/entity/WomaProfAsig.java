/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jruiz
 */
@Entity
@Table(name = "woma_prof_asig")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WomaProfAsig.findAll", query = "SELECT w FROM WomaProfAsig w"),
    @NamedQuery(name = "WomaProfAsig.findByIdProfAsig", query = "SELECT w FROM WomaProfAsig w WHERE w.idProfAsig = :idProfAsig"),
    @NamedQuery(name = "WomaProfAsig.findByAsigAndProf", query = "SELECT w FROM WomaProfAsig w WHERE w.womaAsignaturaId = :womaAsignaturaId AND w.idProfAsig = :idProfAsig")})
public class WomaProfAsig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROF_ASIG")
    private Integer idProfAsig;
    @JoinColumn(name = "WOMA_ASIGNATURA_ID", referencedColumnName = "ID_ASIG")
    @ManyToOne(optional = false)
    private WomaAsignatura womaAsignaturaId;
    @JoinColumn(name = "WOMA_PROFESOR_ID", referencedColumnName = "ID_PROF")
    @ManyToOne(optional = false)
    private WomaProfesor womaProfesorId;

    public WomaProfAsig() {
    }

    public WomaProfAsig(Integer idProfAsig) {
        this.idProfAsig = idProfAsig;
    }

    public Integer getIdProfAsig() {
        return idProfAsig;
    }

    public void setIdProfAsig(Integer idProfAsig) {
        this.idProfAsig = idProfAsig;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfAsig != null ? idProfAsig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WomaProfAsig)) {
            return false;
        }
        WomaProfAsig other = (WomaProfAsig) object;
        if ((this.idProfAsig == null && other.idProfAsig != null) || (this.idProfAsig != null && !this.idProfAsig.equals(other.idProfAsig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uma.wolfmaths.entity.WomaProfAsig[ idProfAsig=" + idProfAsig + " ]";
    }
    
}
