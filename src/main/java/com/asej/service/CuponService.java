package com.asej.service;

import java.util.List;

import com.asej.DAO.CuponDAO;
import com.asej.model.Cupon;
import com.asej.model.Rol;

public class CuponService {

private CuponDAO cuponDAO;
	
	public CuponService() {
		cuponDAO = new CuponDAO();
	}

	public List<Cupon> getCupones(int id_suscriptor) {
		return cuponDAO.getCupones(id_suscriptor);
	}

	public List<Integer> createCupones(int numeroCupones, String nombreRol) {
		return cuponDAO.createCupones(numeroCupones, nombreRol);
	}
	
	public boolean returnCupon(int id_cupon) {
		return cuponDAO.returnCupon(id_cupon);
	}
	
	public boolean actualizarHistorialCupones(int id_suscriptor, int id_cupon) {
		return cuponDAO.actualizarHistorialCupones(id_suscriptor, id_cupon);
	}

	public List<Cupon> getCuponesDisponibles(int id) {
		return cuponDAO.getCuponesDisponibles(id);
	}

	public List<Cupon> getCuponesDevolviblesCentro(int id_suscriptor) {
		 
		return cuponDAO.getCuponesDevolviblesCentro(id_suscriptor);
	}
	
	public boolean usarCupon(int id_cupon) {
		return cuponDAO.usarCupon(id_cupon);
	}
	
	public boolean programarCupon(int numCupones) {
		return cuponDAO.programarCupon(numCupones);
	}
	
	
}
