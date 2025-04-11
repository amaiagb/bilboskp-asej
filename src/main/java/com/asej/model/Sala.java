package com.asej.model;

public class Sala {
	private int id_sala;
	private String nombre, tipo, estado;
	
	public Sala() {
		super();
	}

	public Sala(int id_sala, String nombre, String tipo, String estado) {
		super();
		this.id_sala = id_sala;
		this.nombre = nombre;
		this.tipo = tipo;
		this.estado = estado;
	}

	public Sala(String nombre, String tipo, String estado) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.estado = estado;
	}	
	

	public Sala(String nombre, String tipo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Sala [id_sala=" + id_sala + ", nombre=" + nombre + ", tipo=" + tipo + ", estado=" + estado + "]";
	}
	
	

	
	

}
