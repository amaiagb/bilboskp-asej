package com.asej.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.asej.DAO.AccesoBD;
import com.asej.DAO.CentroDAO;
import com.asej.DAO.CuponDAO;
import com.asej.DAO.PartidaDAO;
import com.asej.DAO.SuscriptorDAO;
import com.asej.model.Centro;
import com.asej.model.Cupon;
import com.asej.model.Suscriptor;

public class CentroService {
	
	CentroDAO centroDAO = new CentroDAO();
	CuponDAO cuponDAO = new CuponDAO();
	SuscriptorDAO suscriptorDAO = new SuscriptorDAO();
	PartidaDAO partidaDAO = new PartidaDAO();

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
		 
		return centroDAO.countCentros();
	}

	public int countSolicitudes() {
		 
		return centroDAO.countSolicitudes();
	}

	public List<Centro> getCentros() {
		 
		return centroDAO.getCentros();
	}
	
	public boolean deleteCentro(int id_centro) {

		return centroDAO.deleteCentro(id_centro);
	}

	public boolean deleteCentroCompleto(Centro centro, double totalReembolso) {
		
		String email = centro.getEmail();
		int id_suscriptor = centro.getId();
		
		 //Hacer la devolucion
		 PasarelaPago pasarela = new PasarelaPago();
		 boolean reembolsoHecho = pasarela.procesarReembolso(email, totalReembolso);
		 
		 if(reembolsoHecho) {
			 //si el reembolso es correcto -> Hacer transaccion
			 
			 Connection con = null;
			 
			 try {
				 
				 con = AccesoBD.getConnection();
				 con.setAutoCommit(false);
				
				 // BORRAR LOS DATOS ASOCIADOS AL CENTRO
				 // delete historial cupones
				 cuponDAO.deleteHistorialBySuscriptorId(con, id_suscriptor);
				
				 //Obtener cupones del centro
				 List<Cupon> cupones = cuponDAO.getCupones(id_suscriptor);
				 // delete cupones
				 for (Cupon cupon : cupones) {
					cuponDAO.deleteCuponesById(con, cupon.getId_cupon());
				 }
				
				 // delete partidas
				 partidaDAO.deletePartidasBySuscriptor(con, id_suscriptor);
				
				 // delete suscriptor (persona contacto del centro)
				 //suscriptorDAO.deleteSuscriptor(con ,id_suscriptor);
				
				 // delete centro
				 System.out.println("Intentando borrar centro con ID: " + centro.getId_centro());
				 //boolean centroBorrado = centroDAO.deleteCentro(con, centro.getId_centro());
				 
				 if(suscriptorDAO.deleteSuscriptor(con ,id_suscriptor)) {
					 con.commit();
					 return true;
				 } else {
					 con.rollback();
					 return false;
				 }
				
			} catch (SQLException e) {
	            if (con != null) {
	                try {
	                	con.rollback(); // en caso de excepcion, rollback
	                } catch (SQLException rollbackEx) {
	                    rollbackEx.printStackTrace();
	                }
	            }
	            e.printStackTrace();
	            return false;
				 
			}finally {
	            if (con != null) {
	                try {
	                	con.setAutoCommit(true); // restaurar auto-commit despues de la transaccion
	                	con.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
			 
		 } else {
			 return false;
		 }
	}

}
