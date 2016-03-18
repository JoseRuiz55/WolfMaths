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
@Table(name = "woma_solucion_problema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WomaSolucionProblema.findAll", query = "SELECT w FROM WomaSolucionProblema w"),
    @NamedQuery(name = "WomaSolucionProblema.findByIdSoluProb", query = "SELECT w FROM WomaSolucionProblema w WHERE w.idSoluProb = :idSoluProb"),
    @NamedQuery(name = "WomaSolucionProblema.findBySolucionTotal", query = "SELECT w FROM WomaSolucionProblema w WHERE w.solucionTotal = :solucionTotal"),
    @NamedQuery(name = "WomaSolucionProblema.findByPasosResolucion", query = "SELECT w FROM WomaSolucionProblema w WHERE w.pasosResolucion = :pasosResolucion"),
    @NamedQuery(name = "WomaSolucionProblema.findByComentario", query = "SELECT w FROM WomaSolucionProblema w WHERE w.comentario = :comentario"),
    @NamedQuery(name = "WomaSolucionProblema.findByTipoSolucion", query = "SELECT w FROM WomaSolucionProblema w WHERE w.tipoSolucion = :tipoSolucion"),
    @NamedQuery(name = "WomaSolucionProblema.getSolucionProblemaAlum", query = "SELECT w FROM WomaSolucionProblema w WHERE w.womaProfesorId = null AND w.womaProblemaId = :womaProblema")})
public class WomaSolucionProblema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SOLU_PROB")
    private Integer idSoluProb;
    @Size(max = 3000)
    @Column(name = "SOLUCION_TOTAL")
    private String solucionTotal;
    @Column(name = "PASOS_RESOLUCION")
    private Integer pasosResolucion;
    @Size(max = 2000)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Size(max = 1)
    @Column(name = "TIPO_SOLUCION")
    private String tipoSolucion;
    @JoinColumn(name = "WOMA_ALUMNO_ID", referencedColumnName = "ID_ALUM")
    @ManyToOne(optional = true)
    private WomaAlumno womaAlumnoId;
    @JoinColumn(name = "WOMA_PROBLEMA_ID", referencedColumnName = "ID_PROB")
    @ManyToOne(optional = false)
    private WomaProblema womaProblemaId;
    @JoinColumn(name = "WOMA_PROFESOR_ID", referencedColumnName = "ID_PROF")
    @ManyToOne(optional = true)
    private WomaProfesor womaProfesorId;
    @JoinColumn(name = "WOMA_INTENTO_PROBLEMA_ID", referencedColumnName = "ID_INTE_PROB")
    @ManyToOne(optional = false)
    private WomaIntentoProblema womaIntentoProblemaId;
    @OneToMany(mappedBy = "womaSolucionProblemaId")
    private List<WomaCorreccion> womaCorreccionList;

    public WomaSolucionProblema() {
    }

    public WomaSolucionProblema(Integer idSoluProb) {
        this.idSoluProb = idSoluProb;
    }

    public Integer getIdSoluProb() {
        return idSoluProb;
    }

    public void setIdSoluProb(Integer idSoluProb) {
        this.idSoluProb = idSoluProb;
    }

    public String getSolucionTotal() {
        return solucionTotal;
    }

    public void setSolucionTotal(String solucionTotal) {
        this.solucionTotal = solucionTotal;
    }

    public Integer getPasosResolucion() {
        return pasosResolucion;
    }

    public void setPasosResolucion(Integer pasosResolucion) {
        this.pasosResolucion = pasosResolucion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTipoSolucion() {
        return tipoSolucion;
    }

    public void setTipoSolucion(String tipoSolucion) {
        this.tipoSolucion = tipoSolucion;
    }

    public WomaAlumno getWomaAlumnoId() {
        return womaAlumnoId;
    }

    public void setWomaAlumnoId(WomaAlumno womaAlumnoId) {
        this.womaAlumnoId = womaAlumnoId;
    }

    public WomaProblema getWomaProblemaId() {
        return womaProblemaId;
    }

    public void setWomaProblemaId(WomaProblema womaProblemaId) {
        this.womaProblemaId = womaProblemaId;
    }

    public WomaProfesor getWomaProfesorId() {
        return womaProfesorId;
    }

    public void setWomaProfesorId(WomaProfesor womaProfesorId) {
        this.womaProfesorId = womaProfesorId;
    }

    public WomaIntentoProblema getWomaIntentoProblemaId() {
        return womaIntentoProblemaId;
    }

    public void setWomaIntentoProblemaId(WomaIntentoProblema womaIntentoProblemaId) {
        this.womaIntentoProblemaId = womaIntentoProblemaId;
    }
    
    @XmlTransient
    public List<WomaCorreccion> getWomaCorreccionList() {
        return womaCorreccionList;
    }

    public void setWomaCorreccionList(List<WomaCorreccion> womaCorreccionList) {
        this.womaCorreccionList = womaCorreccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSoluProb != null ? idSoluProb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WomaSolucionProblema)) {
            return false;
        }
        WomaSolucionProblema other = (WomaSolucionProblema) object;
        if ((this.idSoluProb == null && other.idSoluProb != null) || (this.idSoluProb != null && !this.idSoluProb.equals(other.idSoluProb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uma.wolfmaths.entity.WomaSolucionProblema[ idSoluProb=" + idSoluProb + " ]";
    }
    
}
