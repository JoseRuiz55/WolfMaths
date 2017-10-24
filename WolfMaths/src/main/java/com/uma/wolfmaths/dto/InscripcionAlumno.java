package com.uma.wolfmaths.dto;

public class InscripcionAlumno {

    private Integer id;
    private Alumno alumno;
    private Asignatura asignatura;
    private boolean inscrito;
    
    public InscripcionAlumno(){
    this.id = null;
    this.alumno = new Alumno();
    this.asignatura = new Asignatura();
    this.inscrito = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
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
