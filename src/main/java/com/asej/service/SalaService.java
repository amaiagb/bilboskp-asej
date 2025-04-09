package com.asej.service;

import java.util.List;

import com.asej.DAO.SalaDAO;
import com.asej.model.Sala;

public class SalaService {
	
	private SalaDAO salaDAO;
	
	public SalaService() {
		salaDAO = new SalaDAO();
	}

	public List<Sala> getSalas() {
		return salaDAO.getSalas();
	}

	public boolean addSala(Sala sala) {
		return salaDAO.createOrUpdateSala(sala);
	}
	
	public boolean updateSala(Sala sala) {
		if(sala.getId_sala()!=0){
			return salaDAO.createOrUpdateSala(sala);
		}else {
			return false;
		}
	}

}