package com.asej.service;

import java.util.List;

import com.asej.DAO.PartidaDAO;
import com.asej.model.Partida;

public class PartidaService {
	PartidaDAO partidaDao;
	
	public PartidaService(){
		partidaDao = new PartidaDAO();
	}

	public boolean addPartida(Partida nuevaPartida, int id_suscriptor) {
		 
		return PartidaDAO.addPartida(nuevaPartida, id_suscriptor);
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

}
