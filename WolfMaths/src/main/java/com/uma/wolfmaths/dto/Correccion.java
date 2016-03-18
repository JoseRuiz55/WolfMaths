package com.uma.wolfmaths.dto;

public class Correccion {
	
	private Integer id;
	private String idString;
	private String nota;
	private String comentario;
	
	public Correccion(){
		this.id = null;
		this.idString = "";
		this.nota = "";
		this.comentario = "";
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

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	

}
