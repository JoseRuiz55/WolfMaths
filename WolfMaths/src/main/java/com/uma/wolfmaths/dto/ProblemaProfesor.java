package com.uma.wolfmaths.dto;

public class ProblemaProfesor {
	
	private Integer id;
	private String idString;
	private Asignatura asignatura;
	private String resumen;
	private int numeroResolucionesAlumnos;
	
	public ProblemaProfesor(){
		this.id = null;
		this.idString = "";
		this.asignatura = new Asignatura();
		this.resumen = "";
		this.numeroResolucionesAlumnos = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public int getNumeroResolucionesAlumnos() {
		return numeroResolucionesAlumnos;
	}

	public void setNumeroResolucionesAlumnos(int numeroResolucionesAlumnos) {
		this.numeroResolucionesAlumnos = numeroResolucionesAlumnos;
	}
	
	

}
