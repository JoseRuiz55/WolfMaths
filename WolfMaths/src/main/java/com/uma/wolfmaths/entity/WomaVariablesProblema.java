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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jruiz
 */
@Entity
@Table(name = "woma_variables_problema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WomaVariablesProblema.findAll", query = "SELECT w FROM WomaVariablesProblema w"),
    @NamedQuery(name = "WomaVariablesProblema.findByIdVariablesProblema", query = "SELECT w FROM WomaVariablesProblema w WHERE w.idVariablesProblema = :idVariablesProblema"),
    @NamedQuery(name = "WomaVariablesProblema.findByXInitial", query = "SELECT w FROM WomaVariablesProblema w WHERE w.xInitial = :xInitial"),
    @NamedQuery(name = "WomaVariablesProblema.findByYInitial", query = "SELECT w FROM WomaVariablesProblema w WHERE w.yInitial = :yInitial"),
    @NamedQuery(name = "WomaVariablesProblema.findByZInitial", query = "SELECT w FROM WomaVariablesProblema w WHERE w.zInitial = :zInitial"),
    @NamedQuery(name = "WomaVariablesProblema.findByKInitial", query = "SELECT w FROM WomaVariablesProblema w WHERE w.kInitial = :kInitial"),
    @NamedQuery(name = "WomaVariablesProblema.findByXFinal", query = "SELECT w FROM WomaVariablesProblema w WHERE w.xFinal = :xFinal"),
    @NamedQuery(name = "WomaVariablesProblema.findByYFinal", query = "SELECT w FROM WomaVariablesProblema w WHERE w.yFinal = :yFinal"),
    @NamedQuery(name = "WomaVariablesProblema.findByZFinal", query = "SELECT w FROM WomaVariablesProblema w WHERE w.zFinal = :zFinal"),
    @NamedQuery(name = "WomaVariablesProblema.findByKFinal", query = "SELECT w FROM WomaVariablesProblema w WHERE w.kFinal = :kFinal")})
public class WomaVariablesProblema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_VARIABLES_PROBLEMA")
    private Integer idVariablesProblema;
    @Size(max = 45)
    @Column(name = "X_INITIAL")
    private String xInitial;
    @Size(max = 45)
    @Column(name = "Y_INITIAL")
    private String yInitial;
    @Size(max = 45)
    @Column(name = "Z_INITIAL")
    private String zInitial;
    @Size(max = 45)
    @Column(name = "K_INITIAL")
    private String kInitial;
    @Size(max = 45)
    @Column(name = "X_FINAL")
    private String xFinal;
    @Size(max = 45)
    @Column(name = "Y_FINAL")
    private String yFinal;
    @Size(max = 45)
    @Column(name = "Z_FINAL")
    private String zFinal;
    @Size(max = 45)
    @Column(name = "K_FINAL")
    private String kFinal;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "womaVariablesProblemaId")
    private List<WomaIntentoProblema> womaIntentoProblemaList;

    public WomaVariablesProblema() {
    }

    public WomaVariablesProblema(Integer idVariablesProblema) {
        this.idVariablesProblema = idVariablesProblema;
    }

    public Integer getIdVariablesProblema() {
        return idVariablesProblema;
    }

    public void setIdVariablesProblema(Integer idVariablesProblema) {
        this.idVariablesProblema = idVariablesProblema;
    }

    public String getXInitial() {
        return xInitial;
    }

    public void setXInitial(String xInitial) {
        this.xInitial = xInitial;
    }

    public String getYInitial() {
        return yInitial;
    }

    public void setYInitial(String yInitial) {
        this.yInitial = yInitial;
    }

    public String getZInitial() {
        return zInitial;
    }

    public void setZInitial(String zInitial) {
        this.zInitial = zInitial;
    }

    public String getKInitial() {
        return kInitial;
    }

    public void setKInitial(String kInitial) {
        this.kInitial = kInitial;
    }

    public String getXFinal() {
        return xFinal;
    }

    public void setXFinal(String xFinal) {
        this.xFinal = xFinal;
    }

    public String getYFinal() {
        return yFinal;
    }

    public void setYFinal(String yFinal) {
        this.yFinal = yFinal;
    }

    public String getZFinal() {
        return zFinal;
    }

    public void setZFinal(String zFinal) {
        this.zFinal = zFinal;
    }

    public String getKFinal() {
        return kFinal;
    }

    public void setKFinal(String kFinal) {
        this.kFinal = kFinal;
    }

    @XmlTransient
    public List<WomaIntentoProblema> getWomaIntentoProblemaList() {
        return womaIntentoProblemaList;
    }

    public void setWomaIntentoProblemaList(List<WomaIntentoProblema> womaIntentoProblemaList) {
        this.womaIntentoProblemaList = womaIntentoProblemaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVariablesProblema != null ? idVariablesProblema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WomaVariablesProblema)) {
            return false;
        }
        WomaVariablesProblema other = (WomaVariablesProblema) object;
        if ((this.idVariablesProblema == null && other.idVariablesProblema != null) || (this.idVariablesProblema != null && !this.idVariablesProblema.equals(other.idVariablesProblema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uma.wolfmaths.entity.WomaVariablesProblema[ idVariablesProblema=" + idVariablesProblema + " ]";
    }
    
}
