package com.uma.wolfmaths.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NotaProblemRest {

	private String idProblemResolucion;
	private String nota;
	private String comentario;
	
	public NotaProblemRest(){
		this.idProblemResolucion = "";
		this.nota ="";
		this.comentario = "";
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
	
	public String getIdProblemResolucion() {
		return idProblemResolucion;
	}
	public void setIdProblemResolucion(String idProblemResolucion) {
		this.idProblemResolucion = idProblemResolucion;
	}
	
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		String jgen = null;
		try {
			jgen = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jgen;
	}
	
	
	
	
}
