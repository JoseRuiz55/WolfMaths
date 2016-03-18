package com.uma.wolfmaths.form;

import java.util.ArrayList;
import java.util.List;

import com.uma.wolfmaths.dto.ProblemaProfesor;
import com.uma.wolfmaths.dto.SolucionProblemaAlumno;

public class ProblemasProfesorForm {
	
	private List<ProblemaProfesor> listaProblemaProfesor;
	private List<SolucionProblemaAlumno> listaSolucionProblemaAlumno;
	
	public ProblemasProfesorForm(){
		this.listaProblemaProfesor = new ArrayList<ProblemaProfesor>();
		this.listaSolucionProblemaAlumno = new ArrayList<SolucionProblemaAlumno>();
	}

	public List<ProblemaProfesor> getListaProblemaProfesor() {
		return listaProblemaProfesor;
	}

	public void setListaProblemaProfesor(List<ProblemaProfesor> listaProblemaProfesor) {
		this.listaProblemaProfesor = listaProblemaProfesor;
	}

	public List<SolucionProblemaAlumno> getListaSolucionProblemaAlumno() {
		return listaSolucionProblemaAlumno;
	}

	public void setListaSolucionProblemaAlumno(List<SolucionProblemaAlumno> listaSolucionProblemaAlumno) {
		this.listaSolucionProblemaAlumno = listaSolucionProblemaAlumno;
	}
	
	

}
