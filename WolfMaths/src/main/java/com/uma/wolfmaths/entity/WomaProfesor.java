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
@Table(name = "woma_profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WomaProfesor.findAll", query = "SELECT w FROM WomaProfesor w"),
    @NamedQuery(name = "WomaProfesor.findByIdProf", query = "SELECT w FROM WomaProfesor w WHERE w.idProf = :idProf"),
    @NamedQuery(name = "WomaProfesor.findByNombre", query = "SELECT w FROM WomaProfesor w WHERE w.nombre = :nombre"),
    @NamedQuery(name = "WomaProfesor.findByApellido1", query = "SELECT w FROM WomaProfesor w WHERE w.apellido1 = :apellido1"),
    @NamedQuery(name = "WomaProfesor.findByApellido2", query = "SELECT w FROM WomaProfesor w WHERE w.apellido2 = :apellido2"),
    @NamedQuery(name = "WomaProfesor.findByUsername", query = "SELECT w FROM WomaProfesor w WHERE w.username = :username"),
    @NamedQuery(name = "WomaProfesor.findByPassword", query = "SELECT w FROM WomaProfesor w WHERE w.password = :password"),
    @NamedQuery(name = "WomaProfesor.findByEmail", query = "SELECT w FROM WomaProfesor w WHERE w.email = :email"),
    @NamedQuery(name = "WomaProfesor.findByTelefono", query = "SELECT w FROM WomaProfesor w WHERE w.telefono = :telefono"),
    @NamedQuery(name = "WomaProfesor.findByUsernameAndPassword", query = "SELECT w FROM WomaProfesor w WHERE w.username = :username and w.password = :password")})
public class WomaProfesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROF")
    private Integer idProf;
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
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 20)
    @Column(name = "PASSWORD")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 11)
    @Column(name = "TELEFONO")
    private String telefono;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "womaProfesorId")
    private List<WomaProblema> womaProblemaList;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "womaProfesorId")
    private List<WomaProfAsig> womaProfAsigList;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "womaProfesorId")
    private List<WomaSolucionProblema> womaSolucionProblemaList;

    public WomaProfesor() {
    }

    public WomaProfesor(Integer idProf) {
        this.idProf = idProf;
    }

    public Integer getIdProf() {
        return idProf;
    }

    public void setIdProf(Integer idProf) {
        this.idProf = idProf;
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
    public List<WomaSolucionProblema> getWomaSolucionProblemaList() {
        return womaSolucionProblemaList;
    }

    public void setWomaSolucionProblemaList(List<WomaSolucionProblema> womaSolucionProblemaList) {
        this.womaSolucionProblemaList = womaSolucionProblemaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProf != null ? idProf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WomaProfesor)) {
            return false;
        }
        WomaProfesor other = (WomaProfesor) object;
        if ((this.idProf == null && other.idProf != null) || (this.idProf != null && !this.idProf.equals(other.idProf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.uma.wolfmaths.entity.WomaProfesor[ idProf=" + idProf + " ]";
    }
    
}
