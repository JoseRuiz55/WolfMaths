package com.uma.wolfmaths.form;

import com.uma.wolfmaths.dto.Problem;

public class ProblemForm {
	
	private Problem problem;
	private boolean hayVarsSel;
	private boolean hayStepsSel;
	private boolean readyToSaveProblem;
	private String action;
	private VariablesNumberOptionsForm variablesNumberOptionsForm;
	
	public ProblemForm(){
		this.problem = new Problem();
		this.hayVarsSel = false;
		this.hayStepsSel = false;
		this.readyToSaveProblem = false;
		this.action = "";
		this.variablesNumberOptionsForm=new VariablesNumberOptionsForm();
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public boolean isHayVarsSel() {
		return hayVarsSel;
	}

	public void setHayVarsSel(boolean hayVarsSel) {
		this.hayVarsSel = hayVarsSel;
	}

	public boolean isHayStepsSel() {
		return hayStepsSel;
	}

	public void setHayStepsSel(boolean hayStepsSel) {
		this.hayStepsSel = hayStepsSel;
	}

	public boolean getReadyToSaveProblem() {
		return readyToSaveProblem;
	}

	public void setReadyToSaveProblem(boolean readyToSaveProblem) {
		this.readyToSaveProblem = readyToSaveProblem;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public VariablesNumberOptionsForm getVariablesNumberOptionsForm() {
		return variablesNumberOptionsForm;
	}

	public void setVariablesNumberOptionsForm(VariablesNumberOptionsForm variablesNumberOptionsForm) {
		this.variablesNumberOptionsForm = variablesNumberOptionsForm;
	}

	@Override
	public String toString() {
		return "ProblemForm [problem=" + problem + ", hayVarsSel=" + hayVarsSel + ", hayStepsSel=" + hayStepsSel
				+ ", action=" + action + ", variablesNumberOptionsForm=" + variablesNumberOptionsForm + "]";
	}
	
	
	
	

}
