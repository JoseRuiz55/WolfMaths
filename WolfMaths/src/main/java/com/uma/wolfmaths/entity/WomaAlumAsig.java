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
@Table(name = "woma_alum_asig")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WomaAlumAsig.findAll", query = "SELECT w FROM WomaAlumAsig w"),
    @NamedQuery(name = "WomaAlumAsig.findByIdAsumAsig", query = "SELECT w FROM WomaAlumAsig w WHERE w.idAsumAsig = :idAsumAsig")})
public class WomaAlumAsig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ASUM_ASIG")
    private Integer idAsumAsig;
    @JoinColumn(name = "WOMA_ALUMNO_ID", referencedColumnName = "ID_ALUM")
    @ManyToOne(optional = false)
    private WomaAlumno womaAlumnoId;
    @JoinColumn(name = "WOMA_ASIGNATURA_ID", referencedColumnName = "ID_ASIG")
    @ManyToOne(optional = false)
    private WomaAsignatura womaAsignaturaId;

    public WomaAlumAsig() {
    }

    public WomaAlumAsig(Integer idAsumAsig) {
        this.idAsumAsig = idAsumAsig;
    }

    public Integer getIdAsumAsig() {
        return idAsumAsig;
    }

    public void setIdAsumAsig(Integer idAsumAsig) {
        this.idAsumAsig = idAsumAsig;
    }

    public WomaAlumno getWomaAlumnoId() {
        return womaAlumnoId;
    }

    public void setWomaAlumnoId(WomaAlumno womaAlumnoId) {
        this.womaAlumnoId = womaAlumnoId;
    }

    public WomaAsignatura getWomaAsignaturaId() {
        return womaAsignaturaId;
    }

    public void setWomaAsignaturaId(WomaAsignatura womaAsignaturaId) {
        this.womaAsignaturaId = womaAsignaturaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsumAsig != null ? idAsumAsig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WomaAlumAsig)) {
            return false;
        }
        WomaAlumAsig other = (WomaAlumAsig) object;
        if ((this.idAsumAsig == null && other.idAsumAsig != null) || (this.idAsumAsig != null && !this.idAsumAsig.equals(other.idAsumAsig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uma.wolfmaths.entity.WomaAlumAsig[ idAsumAsig=" + idAsumAsig + " ]";
    }
    
}
