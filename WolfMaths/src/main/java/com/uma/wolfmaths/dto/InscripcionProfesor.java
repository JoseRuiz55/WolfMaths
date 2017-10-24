package com.uma.wolfmaths.dto;

public class InscripcionProfesor {

    private Integer id;
    private Profesor profesor;
    private Asignatura asignatura;
    private boolean inscrito;
    
    public InscripcionProfesor(){
    this.id = null;
    this.profesor = new Profesor();
    this.asignatura = new Asignatura();
    this.inscrito = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public boolean isInscrito() {
        return inscrito;
    }

    public void setInscrito(boolean inscrito) {
        this.inscrito = inscrito;
    }
    
    
}