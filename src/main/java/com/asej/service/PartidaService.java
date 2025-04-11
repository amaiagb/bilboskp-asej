package com.asej.service;

import java.util.List;

import com.asej.DAO.PartidaDAO;
import com.asej.model.Partida;

public class PartidaService {
	PartidaDAO partidaDao;
	
	public PartidaService(){
		partidaDao = new PartidaDAO();
	}

	public boolean addPartida(Partida nuevaPartida) {
		 
		return PartidaDAO.addPartida(nuevaPartida);
	}

	public List<Partida> getPartidas() {
		 
		return PartidaDAO.getPartidas();
	}

	public List<Partida> getPartidasById(int id_suscriptor) {
		 
		return PartidaDAO.getPartidasById(id_suscriptor);
	}

	public List<Partida> getUltimasPartidasJugadasById(int id) {
		 
		return PartidaDAO.getUltimasPartidasJugadasById(id);
	}

}
