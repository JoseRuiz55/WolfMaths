package com.uma.wolfmaths.dto;

public class ProblemaCorreccionAlumno {
	
	private Integer id;
	private String idString;
	private String resultado;
	private String comentario;
	private Integer pasosResolucion;
	
	public ProblemaCorreccionAlumno(){
		this.id = null;
		this.idString = "";
		this.resultado = "";
		this.comentario = "";
		this.pasosResolucion = null;
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

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getPasosResolucion() {
		return pasosResolucion;
	}

	public void setPasosResolucion(Integer pasosResolucion) {
		this.pasosResolucion = pasosResolucion;
	}
	
	

}
