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
import javax.persistence.Id;
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
@Table(name = "woma_asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WomaAsignatura.findAll", query = "SELECT w FROM WomaAsignatura w"),
    @NamedQuery(name = "WomaAsignatura.findByIdAsig", query = "SELECT w FROM WomaAsignatura w WHERE w.idAsig = :idAsig"),
    @NamedQuery(name = "WomaAsignatura.findByNombre", query = "SELECT w FROM WomaAsignatura w WHERE w.nombre = :nombre"),
    @NamedQuery(name = "WomaAsignatura.findByDepartamento", query = "SELECT w FROM WomaAsignatura w WHERE w.departamento = :departamento"),
    @NamedQuery(name = "WomaAsignatura.findByNumMaxAlum", query = "SELECT w FROM WomaAsignatura w WHERE w.numMaxAlum = :numMaxAlum")})
public class WomaAsignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ASIG")
    private Integer idAsig;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    @Column(name = "NUM_MAX_ALUM")
    private Integer numMaxAlum;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "womaAsignaturaId")
    private List<WomaProblema> womaProblemaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "womaAsignaturaId")
    private List<WomaProfAsig> womaProfAsigList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "womaAsignaturaId")
    private List<WomaAlumAsig> womaAlumAsigList;

    public WomaAsignatura() {
    }

    public WomaAsignatura(Integer idAsig) {
        this.idAsig = idAsig;
    }

    public Integer getIdAsig() {
        return idAsig;
    }

    public void setIdAsig(Integer idAsig) {
        this.idAsig = idAsig;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Integer getNumMaxAlum() {
        return numMaxAlum;
    }

    public void setNumMaxAlum(Integer numMaxAlum) {
        this.numMaxAlum = numMaxAlum;
    }

    @XmlTransient
    public List<WomaProblema> getWomaProblemaList() {
        return womaProblemaList;
    }

    public void setWomaProblemaList(List<WomaProblema> womaProblemaList) {
        this.womaProblemaList = womaProblemaList;
    }

    @XmlTransient
    public List<WomaProfAsig> getWomaProfAsigList() {
        return womaProfAsigList;
    }

    public void setWomaProfAsigList(List<WomaProfAsig> womaProfAsigList) {
        this.womaProfAsigList = womaProfAsigList;
    }

    @XmlTransient
    public List<WomaAlumAsig> getWomaAlumAsigList() {
        return womaAlumAsigList;
    }

    public void setWomaAlumAsigList(List<WomaAlumAsig> womaAlumAsigList) {
        this.womaAlumAsigList = womaAlumAsigList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsig != null ? idAsig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WomaAsignatura)) {
            return false;
        }
        WomaAsignatura other = (WomaAsignatura) object;
        if ((this.idAsig == null && other.idAsig != null) || (this.idAsig != null && !this.idAsig.equals(other.idAsig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uma.wolfmaths.entity.WomaAsignatura[ idAsig=" + idAsig + " ]";
    }
    
}
