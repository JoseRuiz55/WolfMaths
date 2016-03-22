package com.uma.wolfmaths.form;

import com.uma.wolfmaths.dto.Administrador;
import com.uma.wolfmaths.dto.Alumno;
import com.uma.wolfmaths.dto.Profesor;

public class RegistrationForm {
	
	private Alumno alumno;
	private Profesor profesor;
	private Administrador administrador;
	private String rol;
	
	public RegistrationForm(){
		this.alumno = new Alumno();
		this.profesor = new Profesor();
		this.administrador = new Administrador();
		this.rol = "";
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "RegistrationForm [alumno=" + alumno + ", profesor=" + profesor + ", administrador=" + administrador
				+ ", rol=" + rol + "]";
	}

	

}
