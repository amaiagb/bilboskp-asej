package com.asej.service;

import java.time.LocalDateTime;
import java.util.List;

import com.asej.DAO.PartidaDAO;
import com.asej.model.Partida;

public class PartidaService {
	PartidaDAO partidaDao;
	
	public PartidaService(){
		partidaDao = new PartidaDAO();
	}

	public boolean addPartida(Partida nuevaPartida, int idSuscriptor, String codigo) {
		 
		return PartidaDAO.addPartida(nuevaPartida, idSuscriptor, codigo);
	}

	public List<Partida> getPartidas() {
		 
		return PartidaDAO.getPartidas();
	}
	
	public boolean deletePartida(Partida partida) {
		 
		return PartidaDAO.deletePartida(partida);
	}

	public boolean updatePartida(Partida partida) {
		 
		return PartidaDAO.updatePartida(partida);
	}

	public List<Partida> getPartidasById(int id_suscriptor) {
		 
		return PartidaDAO.getPartidasById(id_suscriptor);
	}

	public List<Partida> getUltimasPartidasJugadasById(int id) {
		 
		return PartidaDAO.getUltimasPartidasJugadasById(id);
	}
	public Partida getPartidaById(int id) {
		 
		return PartidaDAO.getPartidabyId(id);
	}

	public int getNumPartidasProgramadas(int id) {
		 
		return partidaDao.getNumPartidasProgramadas(id);
	}

	public List<Partida> getPartidasByIdYSala(int id_suscriptor, int id_sala) {
		// TODO Auto-generated method stub
		return PartidaDAO.getPartidasByIdYSala(id_suscriptor, id_sala);
	}

	public Partida mirarCodigo(String codigo) {
		// TODO Auto-generated method stub
		return PartidaDAO.mirarCodigo(codigo);
	}

	public boolean actualizarPartida(Partida partida) {
		// TODO Auto-generated method stub
		return PartidaDAO.actualizarPartida(partida);
	}

	public List<Partida> getPartidasByIdBajo(int id_suscriptor) {
		// TODO Auto-generated method stub
		return PartidaDAO.getPartidasByIdBajo(id_suscriptor);
	}

	public boolean existePartidaEnFecha(LocalDateTime fecha) {
		// TODO Auto-generated method stub
		 try {
		        return partidaDao.existePartidaEnFecha(fecha);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
	}

	public List<Partida> getPartidasByIdYEstado(int id_suscriptor, String estado) {
		// TODO Auto-generated method stub
		return PartidaDAO.getPartidasByIdYEstado(id_suscriptor, estado);
	}

	public boolean crearPartidaSuscriptor(Partida nuevaPartida, int idSuscriptor) {
		// TODO Auto-generated method stub
		return PartidaDAO.crearPartidaSuscriptor(nuevaPartida, idSuscriptor);
	}



}
