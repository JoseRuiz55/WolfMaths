package com.uma.wolfmaths.form;

import java.util.ArrayList;
import java.util.List;

import com.uma.wolfmaths.dto.Administrador;
import com.uma.wolfmaths.dto.Alumno;
import com.uma.wolfmaths.dto.Asignatura;
import com.uma.wolfmaths.dto.Profesor;

public class RegistrationForm {

	private Alumno alumno;
	private Profesor profesor;
	private Administrador administrador;
	private String rol;
	private List<Asignatura> listaAsignaturas;
	private List<Profesor> listaProfesores;
	private List<Alumno> listaAlumnos;
	private List<Administrador> listaAdministradores;
	private Asignatura asignatura;
	private boolean creacion;
	private boolean visualizar;
	private boolean editar;

	public RegistrationForm() {
		this.alumno = new Alumno();
		this.profesor = new Profesor();
		this.administrador = new Administrador();
		this.rol = "";
		this.listaAsignaturas = new ArrayList<Asignatura>();
		this.listaProfesores = new ArrayList<Profesor>();
		this.listaAlumnos = new ArrayList<Alumno>();
		this.listaAdministradores = new ArrayList<Administrador>();
		this.asignatura = new Asignatura();
		this.creacion = false;
		this.visualizar = false;
		this.editar = false;
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

	public List<Asignatura> getListaAsignaturas() {
		return listaAsignaturas;
	}

	public void setListaAsignaturas(List<Asignatura> listaAsignaturas) {
		this.listaAsignaturas = listaAsignaturas;
	}

	public List<Profesor> getListaProfesores() {
		return listaProfesores;
	}

	public void setListaProfesores(List<Profesor> listaProfesores) {
		this.listaProfesores = listaProfesores;
	}

	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(List<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

	public List<Administrador> getListaAdministradores() {
		return listaAdministradores;
	}

	public void setListaAdministradores(List<Administrador> listaAdministradores) {
		this.listaAdministradores = listaAdministradores;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public boolean isCreacion() {
		return creacion;
	}

	public void setCreacion(boolean creacion) {
		this.creacion = creacion;
	}

	public boolean isVisualizar() {
		return visualizar;
	}

	public void setVisualizar(boolean visualizar) {
		this.visualizar = visualizar;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	@Override
	public String toString() {
		return "RegistrationForm [alumno=" + alumno + ", profesor=" + profesor + ", administrador=" + administrador
				+ ", rol=" + rol + ", listaAsignaturas=" + listaAsignaturas + ", listaProfesores=" + listaProfesores
				+ ", listaAlumnos=" + listaAlumnos + ", listaAdministradores=" + listaAdministradores + ", asignatura="
				+ asignatura + ", creacion=" + creacion + ", visualizar=" + visualizar + ", editar=" + editar + "]";
	}

	

}
