package com.asej.service;

import java.util.List;

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

	public List<Centro> getCentrosPendientes() {
		
		return CentroDAO.getCentrosPendientes();
	}

	public boolean updateEstadoCentro(int id_centro) {
		 
		return  centroDAO.updateEstadoCentro( id_centro) ;
	}

	public Centro getCentroById(int id_centro) {
		
		return centroDAO.getCentroById(id_centro);
	}

	public int countCentros() {
		// TODO Auto-generated method stub
		return centroDAO.countCentros();
	}

	public int countSolicitudes() {
		 
		return centroDAO.countSolicitudes();
	}

	public boolean deleteCentro(int id_centro) {
		 
		return centroDAO.deleteCentro(id_centro);
	}

	public List<Centro> getCentros() {
		 
		return centroDAO.getCentros();
	}

}
