package com.uma.wolfmaths.dto;

public class Alumno {
	
	private Integer id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String username;
	private String password;
	private String email;
	private String telefono;
	
	public Alumno(){
			
			this.id=null;
			this.nombre="";
			this.apellido1="";
			this.apellido2="";
			this.username="";
			this.password="";
			this.email="";
			this.telefono="";
		}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", username=" + username + ", email=" + email + ", telefono=" + telefono + "]";
	}
	
	

}
