package com.uma.wolfmaths.dto;

import com.uma.wolfmaths.constants.WolfmathsConstants;

public class Asignatura {
	
	private Integer id;
	private String nombre;
	private String departamento;
	private int maxAlum;
	
	public Asignatura(){
		this.nombre="";
		this.departamento="";
		this.maxAlum=WolfmathsConstants.MAX_STUDENT_SUBJECT;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public int getMaxAlum() {
		return maxAlum;
	}

	public void setMaxAlum(int maxAlum) {
		this.maxAlum = maxAlum;
	}

	@Override
	public String toString() {
		return "Asignatura [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + ", maxAlum=" + maxAlum
				+ "]";
	}

	
	
}
