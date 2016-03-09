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
@Table(name = "woma_alumno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WomaAlumno.findAll", query = "SELECT w FROM WomaAlumno w"),
    @NamedQuery(name = "WomaAlumno.findByIdAlum", query = "SELECT w FROM WomaAlumno w WHERE w.idAlum = :idAlum"),
    @NamedQuery(name = "WomaAlumno.findByNombre", query = "SELECT w FROM WomaAlumno w WHERE w.nombre = :nombre"),
    @NamedQuery(name = "WomaAlumno.findByApellido1", query = "SELECT w FROM WomaAlumno w WHERE w.apellido1 = :apellido1"),
    @NamedQuery(name = "WomaAlumno.findByApellido2", query = "SELECT w FROM WomaAlumno w WHERE w.apellido2 = :apellido2"),
    @NamedQuery(name = "WomaAlumno.findByUsername", query = "SELECT w FROM WomaAlumno w WHERE w.username = :username"),
    @NamedQuery(name = "WomaAlumno.findByPassword", query = "SELECT w FROM WomaAlumno w WHERE w.password = :password"),
    @NamedQuery(name = "WomaAlumno.findByEmail", query = "SELECT w FROM WomaAlumno w WHERE w.email = :email"),
    @NamedQuery(name = "WomaAlumno.findByTelefono", query = "SELECT w FROM WomaAlumno w WHERE w.telefono = :telefono")})
public class WomaAlumno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ALUM")
    private Integer idAlum;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "APELLIDO1")
    private String apellido1;
    @Size(max = 100)
    @Column(name = "APELLIDO2")
    private String apellido2;
    @Size(max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 12)
    @Column(name = "TELEFONO")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "womaAlumnoId")
    private List<WomaSolucionProblema> womaSolucionProblemaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "womaAlumnoId")
    private List<WomaAlumAsig> womaAlumAsigList;

    public WomaAlumno() {
    }

    public WomaAlumno(Integer idAlum) {
        this.idAlum = idAlum;
    }

    public Integer getIdAlum() {
        return idAlum;
    }

    public void setIdAlum(Integer idAlum) {
        this.idAlum = idAlum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<WomaSolucionProblema> getWomaSolucionProblemaList() {
        return womaSolucionProblemaList;
    }

    public void setWomaSolucionProblemaList(List<WomaSolucionProblema> womaSolucionProblemaList) {
        this.womaSolucionProblemaList = womaSolucionProblemaList;
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
        hash += (idAlum != null ? idAlum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WomaAlumno)) {
            return false;
        }
        WomaAlumno other = (WomaAlumno) object;
        if ((this.idAlum == null && other.idAlum != null) || (this.idAlum != null && !this.idAlum.equals(other.idAlum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uma.wolfmaths.entity.WomaAlumno[ idAlum=" + idAlum + " ]";
    }
    
}
