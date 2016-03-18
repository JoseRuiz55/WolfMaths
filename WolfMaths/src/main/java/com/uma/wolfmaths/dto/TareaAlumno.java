package com.uma.wolfmaths.dto;

public class TareaAlumno {
	
	public Integer id;
	public String idString;
	public Asignatura asignatura;
	public Profesor profesor;
	public String resumen;
	
	public TareaAlumno(){
		this.id = null;
		this.asignatura = new Asignatura();
		this.profesor = new Profesor();
		this.resumen = "";
		this.idString = "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

	@Override
	public String toString() {
		return "TareaAlumno [id=" + id + ", idString=" + idString + ", asignatura=" + asignatura + ", profesor="
				+ profesor + ", resumen=" + resumen + "]";
	}
	
	

}
