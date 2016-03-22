package com.uma.wolfmaths.dto;

public class CorreccionProblemaAlumno {
	
	private Asignatura asignatura;
	private Profesor profesor;
	private Correccion correccion;
	private ProblemaCorreccionAlumno problemaCorreccionAlumno;
	private String enunciado;
	
	
	public CorreccionProblemaAlumno(){
		this.asignatura = new Asignatura();
		this.profesor = new Profesor();
		this.correccion = new Correccion();
		this.problemaCorreccionAlumno = new ProblemaCorreccionAlumno();
		this.enunciado = "";
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
	public Correccion getCorreccion() {
		return correccion;
	}
	public void setCorreccion(Correccion correccion) {
		this.correccion = correccion;
	}
	public ProblemaCorreccionAlumno getProblemaCorreccionAlumno() {
		return problemaCorreccionAlumno;
	}
	public void setProblemaCorreccionAlumno(ProblemaCorreccionAlumno problemaCorreccionAlumno) {
		this.problemaCorreccionAlumno = problemaCorreccionAlumno;
	}
	public String getEnunciado() {
		return enunciado;
	}


	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}


	@Override
	public String toString() {
		return "CorreccionProblemaAlumno [asignatura=" + asignatura + ", profesor=" + profesor + ", correccion="
				+ correccion + ", problemaCorreccionAlumno=" + problemaCorreccionAlumno + ", enunciado=" + enunciado
				+ "]";
	}


	
	
	

}
