package com.uma.wolfmaths.dto;

import com.google.gson.Gson;

public class Step {
	
	private String step;
	private String comment;
	
	
	public Step(){
		this.step="";
		this.comment="";
	}


	public String getStep() {
		return step;
	}


	public void setStep(String step) {
		this.step = step;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	@Override
	public String toString() {
		//Gson gson = new Gson();
		//return gson.toJson("Step [step=" + step + ", comment=" + comment + "]");
		return "Step [step=" + step + ", comment=" + comment + "]";
	}
	
	

}
