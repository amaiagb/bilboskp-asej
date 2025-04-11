package com.asej.service;

import com.asej.DAO.CentroDAO;
import com.asej.model.Centro;
import com.asej.model.Suscriptor;

public class CentroService {
	
	CentroDAO centroDAO = new CentroDAO();

	public boolean addCentro(Centro nuevoCentro) {
		// TODO Auto-generated method stub
		return centroDAO.addCentro(nuevoCentro);
	}

	public Centro getCentroByName(String nombre_centro) {
		
 		return centroDAO.getCentroByName(nombre_centro);
	}

	public Centro getCentroBySuscriptor(Suscriptor suscriptorLogin) {
		
		return centroDAO.getCentroBySuscriptor(suscriptorLogin);
	}

}
