package com.asej.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Partida implements Serializable{
	private static final long serialVersionUID = 1L;
	
    private int id_partida, jugadores, puntuacion;
    private LocalDateTime fecha; 
    private String descripcion, estado;
    private Sala sala;

    public Partida() {
        super();
    }



  

	public Partida(int jugadores, LocalDateTime fecha, String descripcion, Sala sala) {
		super();
		this.jugadores = jugadores;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.sala = sala;
	}





	public Partida(int id_partida, int jugadores, int puntuacion, LocalDateTime fecha, String descripcion,
			String estado, Sala sala) {
		super();
		this.id_partida = id_partida;
		this.jugadores = jugadores;
		this.puntuacion = puntuacion;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.estado = estado;
		this.sala = sala;
	}





	public int getId_partida() {
		return id_partida;
	}





	public void setId_partida(int id_partida) {
		this.id_partida = id_partida;
	}





	public int getJugadores() {
		return jugadores;
	}





	public void setJugadores(int jugadores) {
		this.jugadores = jugadores;
	}





	public int getPuntuacion() {
		return puntuacion;
	}





	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}





	public LocalDateTime getFecha() {
		return fecha;
	}





	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}





	public String getDescripcion() {
		return descripcion;
	}





	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}





	public String getEstado() {
		return estado;
	}





	public void setEstado(String estado) {
		this.estado = estado;
	}





	public Sala getSala() {
		return sala;
	}





	public void setSala(Sala sala) {
		this.sala = sala;
	}





	@Override
	public String toString() {
		return "Partida [id_partida=" + id_partida + ", jugadores=" + jugadores + ", puntuacion=" + puntuacion
				+ ", fecha=" + fecha + ", descripcion=" + descripcion + ", estado=" + estado + ", sala=" + sala + "]";
	}



}
