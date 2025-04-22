package com.asej.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.asej.DAO.AccesoBD;
import com.asej.DAO.CuponDAO;
import com.asej.DAO.SuscriptorDAO;
import com.asej.model.Cupon;
import com.asej.model.Suscriptor;

public class SuscriptorService {
	
	SuscriptorDAO suscriptorDAO = new SuscriptorDAO();
	CuponDAO cuponDAO = new CuponDAO();

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

	public List<Suscriptor> getSuscriptores() {
		
		return suscriptorDAO.getSuscriptores();
	}

	public Suscriptor getSuscriptorById(int id_suscriptor) {
		 
		return suscriptorDAO.getSuscriptorById(id_suscriptor);
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
	
	
	//Con transaccion
	public boolean deleteSuscriptorTransaccion(int id_suscriptor) {
		
		// Obtener todos los cupones asociados a un suscriptor
		List<Cupon> cupones = cuponDAO.getCupones(id_suscriptor);
		
		Connection con = null; 
		try {
			//Crear connection y pasarle la misma a cada operacion del DAO
			con = AccesoBD.getConnection();
			con.setAutoCommit(false);

			//Borrar en el historial las filas del suscriptor
			cuponDAO.deleteHistorialBySuscriptorId(con, id_suscriptor);
			
			//Borrar los cupones asociados al suscriptor 
			for (Cupon cupon : cupones) {
				cuponDAO.deleteCuponesById(con, cupon.getId_cupon());
			}
			// Borrar suscriptor
			suscriptorDAO.deleteSuscriptor(con ,id_suscriptor);
			
			con.commit();
	        return true;
			
		} catch (Exception e) {
			if (con != null) {
	            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
	        }
	        e.printStackTrace();
	        return false;
			
		} finally {
			if (con != null) {
	            try { con.setAutoCommit(true); con.close(); } catch (SQLException ex) { ex.printStackTrace(); }
	        }
		}
		
	}
	


}
