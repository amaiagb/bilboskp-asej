package com.asej.model;

import java.sql.Date;
import java.time.LocalDate;

public class Suscriptor {

	private int id;
	private String nombre, usuario, contrasena, email;
	private LocalDate fecha_alta;
	
	public Suscriptor() {
		super();
	}

	public Suscriptor(String usuario, String contrasena) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
	}

	public Suscriptor(String nombre, String usuario, String contrasena, String email) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
	}

	public Suscriptor(int id, String nombre, String usuario, String contrasena, String email, 
			LocalDate fecha_alta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.fecha_alta = fecha_alta;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
 
	public LocalDate getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(LocalDate fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	@Override
	public String toString() {
		return "Suscriptor [id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", contrasena=" + contrasena
				+ ", email=" + email + ", num_cupones=" +  ", fecha_alta=" + fecha_alta + "]";
	}
	
	
	
}
