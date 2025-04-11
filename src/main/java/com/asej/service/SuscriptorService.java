package com.asej.service;

import com.asej.DAO.SuscriptorDAO;
import com.asej.model.Centro;
import com.asej.model.Suscriptor;

public class SuscriptorService {
	
	SuscriptorDAO suscriptorDAO = new SuscriptorDAO();

	public Suscriptor login(Suscriptor suscriptor) {
		
		return suscriptorDAO.login(suscriptor);
	}

	public int addSuscriptor(Suscriptor nuevoSuscriptor) {
		
		return suscriptorDAO.addSuscriptor(nuevoSuscriptor);
	}

	public Suscriptor getSuscriptorByUsuario(String usuario) {
		 
		return suscriptorDAO.getSuscriptorByUsuario(usuario);
	}

	

}
