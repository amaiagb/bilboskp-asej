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
		System.out.println("AAASAAA"+ sala.getId_sala());
		if(sala.getId_sala()!=0){
			System.out.println("BBBBBBBB");
			return salaDAO.createOrUpdateSala(sala);
		}else {
			System.out.println("CCCCCCCCCC");
			return false;
		}
	}
	
	public boolean desactivarSala(Sala s) {
		// TODO Auto-generated method stub
		return salaDAO.desactivarSala(s);
	}

	public Sala getSalaById(int id) {
		// TODO Auto-generated method stub
		return salaDAO.getSalaById(id);
	}

}