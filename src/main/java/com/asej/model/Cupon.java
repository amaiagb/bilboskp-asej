package com.asej.model;

import java.util.Date;

public class Cupon {

	private int id_cupon;
	private Date fecha_compra, fecha_caducidad, fecha_devolucion;
	private String estado, tipo;
	private float precio;
	
	public Cupon(int id_cupon, Date fecha_compra, Date fecha_caducidad, Date fecha_devolucion, String estado,
			String tipo, float precio) {
		super();
		this.id_cupon = id_cupon;
		this.fecha_compra = fecha_compra;
		this.fecha_caducidad = fecha_caducidad;
		this.fecha_devolucion = fecha_devolucion;
		this.estado = estado;
		this.tipo = tipo;
		this.precio = precio;
	}

	public Cupon() {
		// TODO Auto-generated constructor stub
	}

	public int getId_cupon() {
		return id_cupon;
	}

	public void setId_cupon(int id_cupon) {
		this.id_cupon = id_cupon;
	}

	public Date getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(Date fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public Date getFecha_caducidad() {
		return fecha_caducidad;
	}

	public void setFecha_caducidad(Date fecha_caducidad) {
		this.fecha_caducidad = fecha_caducidad;
	}

	public Date getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(Date fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
}
