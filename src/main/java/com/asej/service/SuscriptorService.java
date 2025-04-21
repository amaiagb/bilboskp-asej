package com.asej.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.asej.DAO.SuscriptorDAO;
import com.asej.model.Centro;
import com.asej.model.Suscriptor;

public class SuscriptorService {
	
	SuscriptorDAO suscriptorDAO = new SuscriptorDAO();

	public Suscriptor login(Suscriptor suscriptor) {
		String hashedPass = hashPassword(suscriptor.getContrasena());
		suscriptor.setContrasena(hashedPass);
		return suscriptorDAO.login(suscriptor);
	}

	public int addSuscriptor(Suscriptor nuevoSuscriptor) {
		String hashedPass = hashPassword(nuevoSuscriptor.getContrasena());
		nuevoSuscriptor.setContrasena(hashedPass);
		return suscriptorDAO.addSuscriptor(nuevoSuscriptor);
	}

	public Suscriptor getSuscriptorByUsuario(String usuario) {
		 
		return suscriptorDAO.getSuscriptorByUsuario(usuario);
	}

	private String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes());
			StringBuilder hexString = new StringBuilder();
			for(byte b: hash) {
				hexString.append(String.format("%02x", b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}

	public int countSuscriptores() {
		// TODO Auto-generated method stub
		return suscriptorDAO.countSuscriptores();
	}

	public boolean deleteSuscriptor(int id_suscriptor) {
		 
		return suscriptorDAO.deleteSuscriptor(id_suscriptor);
	}

	public List<Suscriptor> getSuscriptores() {
		
		return suscriptorDAO.getSuscriptores();
	}

}
