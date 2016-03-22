package com.uma.wolfmaths.form;

import java.util.ArrayList;
import java.util.List;

import com.uma.wolfmaths.dto.Correccion;
import com.uma.wolfmaths.dto.CorreccionProblemaAlumno;
import com.uma.wolfmaths.dto.TareaAlumno;

public class TareasAlumnoForm {
	
	private List<TareaAlumno> listaTareasAlumno;
	private List<CorreccionProblemaAlumno> listaCorreccionProblemaAlumno;
	private List<Correccion> listaHistorico;
	
	
	public TareasAlumnoForm(){
		this.listaTareasAlumno = new ArrayList<TareaAlumno>();
		this.listaCorreccionProblemaAlumno = new ArrayList<CorreccionProblemaAlumno>();
		this.listaHistorico = new ArrayList<Correccion>();
	}


	public List<TareaAlumno> getListaTareasAlumno() {
		return listaTareasAlumno;
	}


	public void setListaTareasAlumno(List<TareaAlumno> listaTareasAlumno) {
		this.listaTareasAlumno = listaTareasAlumno;
	}


	public List<CorreccionProblemaAlumno> getListaCorreccionProblemaAlumno() {
		return listaCorreccionProblemaAlumno;
	}


	public void setListaCorreccionProblemaAlumno(List<CorreccionProblemaAlumno> listaCorreccionProblemaAlumno) {
		this.listaCorreccionProblemaAlumno = listaCorreccionProblemaAlumno;
	}

	public List<Correccion> getListaHistorico() {
		return listaHistorico;
	}


	public void setListaHistorico(List<Correccion> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}


	@Override
	public String toString() {
		return "TareasAlumnoForm [listaTareasAlumno=" + listaTareasAlumno + ", listaCorreccionProblemaAlumno="
				+ listaCorreccionProblemaAlumno + ", listaHistorico=" + listaHistorico + "]";
	}


	


	
	
	

}
