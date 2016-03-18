package com.uma.wolfmaths.form;

import com.uma.wolfmaths.dto.Alumno;
import com.uma.wolfmaths.dto.Profesor;

public class SessionForm {
	
	private Alumno alumno;
	private Profesor profesor;
	private boolean logueado;
	private String userNameLogin;
	private String passwordLogin;
	private String rolSeleccionado;
	
	public SessionForm(){
		this.alumno = null;
		this.profesor = null;
		this.logueado = false;
		this.userNameLogin = null;
		this.rolSeleccionado = null;
		this.passwordLogin = null;
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

	public boolean isLogueado() {
		return logueado;
	}

	public void setLogueado(boolean logueado) {
		this.logueado = logueado;
	}

	public String getUserNameLogin() {
		return userNameLogin;
	}

	public void setUserNameLogin(String userNameLogin) {
		this.userNameLogin = userNameLogin;
	}

	public String getRolSeleccionado() {
		return rolSeleccionado;
	}

	public void setRolSeleccionado(String rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}

	public String getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}

	@Override
	public String toString() {
		return "SessionForm [alumno=" + alumno + ", profesor=" + profesor + ", logueado=" + logueado
				+ ", userNameLogin=" + userNameLogin + ", passwordLogin=" + passwordLogin + ", rolSeleccionado="
				+ rolSeleccionado + "]";
	}
	
	

}
