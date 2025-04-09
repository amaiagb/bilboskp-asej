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

	public List<Cupon> getCupones(String usuario) {
		return cuponDAO.getCupones(usuario);
	}

	public List<Integer> createCupones(int numeroCupones, String nombreRol) {
		return cuponDAO.createCupones(numeroCupones, nombreRol);
	}
	
	public boolean returnCupon(Cupon cupon) {
		return cuponDAO.returnCupon(cupon);
	}
	
	public boolean actualizarHistorialCupones(int id_suscriptor, int id_cupon) {
		return cuponDAO.actualizarHistorialCupones(id_suscriptor, id_cupon);
	}
	
	
}
