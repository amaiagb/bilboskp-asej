package com.asej.model;

public class Centro extends Suscriptor {

	private int id_centro;
	private String nombre, localidad;
	private int etapas_educativas, num_alumnado;
	
	public Centro(int id_centro, String nombre, String localidad, int etapas_educativas, int num_alumnado) {
		super();
		this.id_centro = id_centro;
		this.nombre = nombre;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	
}
