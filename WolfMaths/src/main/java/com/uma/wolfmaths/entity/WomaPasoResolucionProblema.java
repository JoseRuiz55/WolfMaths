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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jruiz
 */
@Entity
@Table(name = "woma_paso_resolucion_problema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WomaPasoResolucionProblema.findAll", query = "SELECT w FROM WomaPasoResolucionProblema w"),
    @NamedQuery(name = "WomaPasoResolucionProblema.findByIdPasoResoProb", query = "SELECT w FROM WomaPasoResolucionProblema w WHERE w.idPasoResoProb = :idPasoResoProb"),
    @NamedQuery(name = "WomaPasoResolucionProblema.findByNumPaso", query = "SELECT w FROM WomaPasoResolucionProblema w WHERE w.numPaso = :numPaso"),
    @NamedQuery(name = "WomaPasoResolucionProblema.findByPasoSentencia", query = "SELECT w FROM WomaPasoResolucionProblema w WHERE w.pasoSentencia = :pasoSentencia"),
    @NamedQuery(name = "WomaPasoResolucionProblema.findByComentario", query = "SELECT w FROM WomaPasoResolucionProblema w WHERE w.comentario = :comentario"),
    @NamedQuery(name = "WomaPasoResolucionProblema.findByResolucion", query = "SELECT w FROM WomaPasoResolucionProblema w WHERE w.resolucion = :resolucion")})
public class WomaPasoResolucionProblema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PASO_RESO_PROB")
    private Integer idPasoResoProb;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_PASO")
    private int numPaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2500)
    @Column(name = "PASO_SENTENCIA")
    private String pasoSentencia;
    @Basic(optional = false)
    @Size(max = 2000)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Size(max = 1)
    @Column(name = "RESOLUCION")
    private String resolucion;
    @JoinColumn(name = "WOMA_INTENTO_PROBLEMA_ID", referencedColumnName = "ID_INTE_PROB")
    @ManyToOne(optional = false)
    private WomaIntentoProblema womaIntentoProblemaId;

    public WomaPasoResolucionProblema() {
    }

    public WomaPasoResolucionProblema(Integer idPasoResoProb) {
        this.idPasoResoProb = idPasoResoProb;
    }

    public WomaPasoResolucionProblema(Integer idPasoResoProb, int numPaso, String pasoSentencia, String comentario) {
        this.idPasoResoProb = idPasoResoProb;
        this.numPaso = numPaso;
        this.pasoSentencia = pasoSentencia;
        this.comentario = comentario;
    }

    public Integer getIdPasoResoProb() {
        return idPasoResoProb;
    }

    public void setIdPasoResoProb(Integer idPasoResoProb) {
        this.idPasoResoProb = idPasoResoProb;
    }

    public int getNumPaso() {
        return numPaso;
    }

    public void setNumPaso(int numPaso) {
        this.numPaso = numPaso;
    }

    public String getPasoSentencia() {
        return pasoSentencia;
    }

    public void setPasoSentencia(String pasoSentencia) {
        this.pasoSentencia = pasoSentencia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public WomaIntentoProblema getWomaIntentoProblemaId() {
        return womaIntentoProblemaId;
    }

    public void setWomaIntentoProblemaId(WomaIntentoProblema womaIntentoProblemaId) {
        this.womaIntentoProblemaId = womaIntentoProblemaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPasoResoProb != null ? idPasoResoProb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WomaPasoResolucionProblema)) {
            return false;
        }
        WomaPasoResolucionProblema other = (WomaPasoResolucionProblema) object;
        if ((this.idPasoResoProb == null && other.idPasoResoProb != null) || (this.idPasoResoProb != null && !this.idPasoResoProb.equals(other.idPasoResoProb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uma.wolfmaths.entity.WomaPasoResolucionProblema[ idPasoResoProb=" + idPasoResoProb + " ]";
    }
    
}
