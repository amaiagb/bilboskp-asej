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
		// TODO Auto-generated method stub
		return PartidaDAO.addPartida(nuevaPartida);
	}

	public List<Partida> getPartidas() {
		// TODO Auto-generated method stub
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

	public Partida getPartidaById(int id) {
		// TODO Auto-generated method stub
		return PartidaDAO.getPartidabyId(id);
	}

}
