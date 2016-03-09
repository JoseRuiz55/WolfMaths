package com.uma.wolfmaths.dto;

import com.uma.wolfmaths.constants.WolfmathsConstants;

public class Subject {
	
	private Integer id;
	private String name;
	private String department;
	private int maxStudent;
	
	public Subject(){
		this.name="";
		this.department="";
		this.maxStudent=WolfmathsConstants.MAX_STUDENT_SUBJECT;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getMaxStudent() {
		return maxStudent;
	}

	public void setMaxStudent(int maxStudent) {
		this.maxStudent = maxStudent;
	}
	
	

}
