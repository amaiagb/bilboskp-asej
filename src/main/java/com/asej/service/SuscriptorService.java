package com.asej.service;

import com.asej.DAO.SuscriptorDAO;
import com.asej.model.Suscriptor;

public class SuscriptorService {
	
	SuscriptorDAO suscriptorDAO = new SuscriptorDAO();

	public Suscriptor login(Suscriptor suscriptor) {
		
		return suscriptorDAO.login(suscriptor);
	}

	public boolean addSuscriptor(Suscriptor nuevoSuscriptor) {
		
		return suscriptorDAO.addSuscriptor(nuevoSuscriptor);
	}

}
