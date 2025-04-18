package com.asej.service;

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
		// TODO Auto-generated method stub
		return PartidaDAO.deletePartida(partida);
	}

	public boolean updatePartida(Partida partida) {
		// TODO Auto-generated method stub
		return PartidaDAO.updatePartida(partida);
	}

	public List<Partida> getPartidasById(int id_suscriptor) {
		 
		return PartidaDAO.getPartidasById(id_suscriptor);
	}

	public List<Partida> getUltimasPartidasJugadasById(int id) {
		 
		return PartidaDAO.getUltimasPartidasJugadasById(id);
	}
	public Partida getPartidaById(int id) {
		// TODO Auto-generated method stub
		return PartidaDAO.getPartidabyId(id);
	}

}
