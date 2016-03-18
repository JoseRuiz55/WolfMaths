package com.uma.wolfmaths.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class Problem {
	private String statement;
	private ProblemSteps steps;
	private ProblemVariables variables;
	private double result;
	private int numVars;
	private int numSteps;
	private String comment;
	private Profesor profesor;
	private Alumno alumno;
	private Asignatura asignatura;
	private String idProblemResolucion = "";
	
	public Problem(){
		this.statement = "";
		this.steps = new ProblemSteps();
		this.variables = new ProblemVariables();
		this.profesor = new Profesor();
		this.alumno = new Alumno();
		this.asignatura = new Asignatura();
		this.idProblemResolucion = "";
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public ProblemSteps getSteps() {
		return steps;
	}

	public void setSteps(ProblemSteps steps) {
		this.steps = steps;
	}

	public ProblemVariables getVariables() {
		return variables;
	}

	public void setVariables(ProblemVariables variables) {
		this.variables = variables;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public int getNumVars() {
		return numVars;
	}

	public void setNumVars(int numVars) {
		this.numVars = numVars;
	}

	public int getNumSteps() {
		return numSteps;
	}

	public void setNumSteps(int numSteps) {
		this.numSteps = numSteps;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	

	public String getIdProblemResolucion() {
		return idProblemResolucion;
	}

	public void setIdProblemResolucion(String idProblemResolucion) {
		this.idProblemResolucion = idProblemResolucion;
	}

	@Override
	public String toString() {
		//Gson gson = new Gson();
		//return gson.toJson("Problem [enunciado=" + enunciado + ", steps=" + steps + ", variables=" + variables + ", result="
		//		+ result + ", numVars=" + numVars + ", numSteps=" + numSteps + "]");
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
