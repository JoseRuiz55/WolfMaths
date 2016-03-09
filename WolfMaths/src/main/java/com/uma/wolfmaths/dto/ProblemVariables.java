package com.uma.wolfmaths.dto;

import com.google.gson.Gson;

public class ProblemVariables {
	
	private String x;
	private String y;
	private String z;
	private String k;
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getZ() {
		return z;
	}
	public void setZ(String z) {
		this.z = z;
	}
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	
	@Override
	public String toString() {
		//Gson gson = new Gson();
		//return gson.toJson("ProblemVariables [x=" + x + ", y=" + y + ", z=" + z + ", k=" + k + "]");
		return "ProblemVariables [x=" + x + ", y=" + y + ", z=" + z + ", k=" + k + "]";
	}
	
	
	
}
