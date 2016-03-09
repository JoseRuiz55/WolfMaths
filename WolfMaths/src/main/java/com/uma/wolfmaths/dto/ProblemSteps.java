package com.uma.wolfmaths.dto;

import com.google.gson.Gson;

public class ProblemSteps {
	
	private Step step1;
	private Step step2;
	private Step step3;
	private Step step4;
	private Step finalStep;
	
	public ProblemSteps(){
		this.step1=new Step();
		this.step2=new Step();
		this.step3=new Step();
		this.step4=new Step();
		this.finalStep = new Step();
	}

	public Step getStep1() {
		return step1;
	}

	public void setStep1(Step step1) {
		this.step1 = step1;
	}

	public Step getStep2() {
		return step2;
	}

	public void setStep2(Step step2) {
		this.step2 = step2;
	}

	public Step getStep3() {
		return step3;
	}

	public void setStep3(Step step3) {
		this.step3 = step3;
	}

	public Step getStep4() {
		return step4;
	}

	public void setStep4(Step step4) {
		this.step4 = step4;
	}

	public Step getFinalStep() {
		return finalStep;
	}

	public void setFinalStep(Step finalStep) {
		this.finalStep = finalStep;
	}

	@Override
	public String toString() {
		//Gson gson = new Gson();
		//return gson.toJson("ProblemSteps [step1=" + step1 + ", step2=" + step2 + ", step3=" + step3 + ", step4=" + step4
		//		+ ", finalStep=" + finalStep + "]");
		return "ProblemSteps [step1=" + step1 + ", step2=" + step2 + ", step3=" + step3 + ", step4=" + step4
				+ ", finalStep=" + finalStep + "]";
	}
	
	

}
