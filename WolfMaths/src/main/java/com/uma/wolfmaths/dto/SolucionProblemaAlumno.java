package com.uma.wolfmaths.dto;

public class SolucionProblemaAlumno {
	private Alumno alumno;
	private Correccion correccion;
	private ProblemaCorreccionAlumno problemaCorreccion;
	
	public SolucionProblemaAlumno(){
		this.alumno = new Alumno();
		this.correccion = new Correccion();
		this.problemaCorreccion = new ProblemaCorreccionAlumno();
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Correccion getCorreccion() {
		return correccion;
	}

	public void setCorreccion(Correccion correccion) {
		this.correccion = correccion;
	}

	public ProblemaCorreccionAlumno getProblemaCorreccionAlumno() {
		return problemaCorreccion;
	}

	public void setProblemaCorreccionAlumno(ProblemaCorreccionAlumno problemaCorreccion) {
		this.problemaCorreccion = problemaCorreccion;
	}
	
	

	
}
