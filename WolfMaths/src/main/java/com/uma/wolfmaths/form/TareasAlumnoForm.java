package com.uma.wolfmaths.form;

import java.util.ArrayList;
import java.util.List;

import com.uma.wolfmaths.dto.TareaAlumno;

public class TareasAlumnoForm {
	
	private List<TareaAlumno> listaTareasAlumno;
	
	
	public TareasAlumnoForm(){
		this.listaTareasAlumno = new ArrayList<TareaAlumno>();
	}


	public List<TareaAlumno> getListaTareasAlumno() {
		return listaTareasAlumno;
	}


	public void setListaTareasAlumno(List<TareaAlumno> listaTareasAlumno) {
		this.listaTareasAlumno = listaTareasAlumno;
	}


	@Override
	public String toString() {
		return "TareasAlumnoForm [listaTareasAlumno=" + listaTareasAlumno + "]";
	}
	
	

}
