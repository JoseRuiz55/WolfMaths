/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uma.wolfmaths.entity;

import java.io.Serializable;
import java.util.List;
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
@Table(name = "woma_correccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WomaCorreccion.findAll", query = "SELECT w FROM WomaCorreccion w"),
    @NamedQuery(name = "WomaCorreccion.findByIdCorr", query = "SELECT w FROM WomaCorreccion w WHERE w.idCorr = :idCorr"),
    @NamedQuery(name = "WomaCorreccion.findByNota", query = "SELECT w FROM WomaCorreccion w WHERE w.nota = :nota"),
    @NamedQuery(name = "WomaCorreccion.findByComentario", query = "SELECT w FROM WomaCorreccion w WHERE w.comentario = :comentario"),
    @NamedQuery(name = "WomaCorreccion.findByWomaSolucionProblema", query = "SELECT w FROM WomaCorreccion w WHERE w.womaSolucionProblemaId = :womaSolucionProblemaId")})
public class WomaCorreccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CORR")
    private Integer idCorr;
    @Size(max = 45)
    @Column(name = "NOTA")
    private String nota;
    @Size(max = 45)
    @Column(name = "COMENTARIO")
    private String comentario;
    @OneToMany
    private List<WomaSolucionProblema> womaSolucionProblemaList;
    @JoinColumn(name = "WOMA_SOLUCION_PROBLEMA_ID", referencedColumnName = "ID_SOLU_PROB")
    @ManyToOne
    private WomaSolucionProblema womaSolucionProblemaId;

    public WomaCorreccion() {
    }

    public WomaCorreccion(Integer idCorr) {
        this.idCorr = idCorr;
    }

    public Integer getIdCorr() {
        return idCorr;
    }

    public void setIdCorr(Integer idCorr) {
        this.idCorr = idCorr;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @XmlTransient
    public List<WomaSolucionProblema> getWomaSolucionProblemaList() {
        return womaSolucionProblemaList;
    }

    public void setWomaSolucionProblemaList(List<WomaSolucionProblema> womaSolucionProblemaList) {
        this.womaSolucionProblemaList = womaSolucionProblemaList;
    }

    public WomaSolucionProblema getWomaSolucionProblemaId() {
        return womaSolucionProblemaId;
    }

    public void setWomaSolucionProblemaId(WomaSolucionProblema womaSolucionProblemaId) {
        this.womaSolucionProblemaId = womaSolucionProblemaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCorr != null ? idCorr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WomaCorreccion)) {
            return false;
        }
        WomaCorreccion other = (WomaCorreccion) object;
        if ((this.idCorr == null && other.idCorr != null) || (this.idCorr != null && !this.idCorr.equals(other.idCorr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uma.wolfmaths.entity.WomaCorreccion[ idCorr=" + idCorr + " ]";
    }
    
}
