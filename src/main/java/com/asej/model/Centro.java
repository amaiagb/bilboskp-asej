package com.asej.model;

import java.time.LocalDate;

public class Centro extends Suscriptor {

	private int id_centro;
	private String nombre_centro, localidad;
	private int etapas_educativas, num_alumnado;
	private String estado; //pendiente, activo
	
	public Centro(int id_centro, String nombre, String localidad, int etapas_educativas, int num_alumnado) {
		super();
		this.id_centro = id_centro;
		this.nombre_centro = nombre;
		this.localidad = localidad;
		this.etapas_educativas = etapas_educativas;
		this.num_alumnado = num_alumnado;
	}


	public Centro(String nombre, String usuario, String contrasena, String email,
			int id_centro, String nombre_centro, String localidad, int etapas_educativas,
			int num_alumnado) {
		super(nombre, usuario, contrasena, email);
		this.id_centro = id_centro;
		this.nombre_centro = nombre_centro;
		this.localidad = localidad;
		this.etapas_educativas = etapas_educativas;
		this.num_alumnado = num_alumnado;
	}

	public Centro(String nombre, String usuario, String contrasena, String email,
			 String nombre_centro, String localidad, int etapas_educativas,
			int num_alumnado) {
		super(nombre, usuario, contrasena, email); 
		this.nombre_centro = nombre_centro;
		this.localidad = localidad;
		this.etapas_educativas = etapas_educativas;
		this.num_alumnado = num_alumnado;
	}
	
	public Centro(Suscriptor suscriptor,
			int id_centro, String nombre_centro, String localidad, int etapas_educativas,
			int num_alumnado) {
		super(suscriptor.getNombre(), suscriptor.getUsuario(), suscriptor.getContrasena(), suscriptor.getEmail(), suscriptor.getRol());
		this.id_centro = id_centro;
		this.nombre_centro = nombre_centro;
		this.localidad = localidad;
		this.etapas_educativas = etapas_educativas;
		this.num_alumnado = num_alumnado;
	}

	public int getId_centro() {
		return id_centro;
	}

	public void setId_centro(int id_centro) {
		this.id_centro = id_centro;
	}

	public String getNombre_centro() {
		return nombre_centro;
	}

	public void setNombre_centro(String nombre) {
		this.nombre_centro = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public int getEtapas_educativas() {
		return etapas_educativas;
	}

	public void setEtapas_educativas(int etapas_educativas) {
		this.etapas_educativas = etapas_educativas;
	}

	public int getNum_alumnado() {
		return num_alumnado;
	}

	public void setNum_alumnado(int num_alumnado) {
		this.num_alumnado = num_alumnado;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "Centro [id_centro=" + id_centro + ", nombre_centro=" + nombre_centro + ", localidad=" + localidad
				+ ", etapas_educativas=" + etapas_educativas + ", num_alumnado=" + num_alumnado + ", estado=" + estado
				+ "]";
	}
	
	
}
