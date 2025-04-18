package com.asej.service;

import java.util.List;

import com.asej.DAO.RolDAO;
import com.asej.model.Rol;



public class RolService {

	private RolDAO rolDAO;
	
	public RolService() {
		rolDAO = new RolDAO();
	}

	public List<Rol> getRoles() {
		return rolDAO.getRoles();
	}

	public boolean addRol(Rol rol) {
		return rolDAO.createOrUpdateRol(rol);
	}
	
	public boolean updateRol(Rol rol) {
		if(rol.getId()!=0){
			return rolDAO.createOrUpdateRol(rol);
		}else {
			return false;
		}
	}

	public Rol getRolByNombre(String nombre) {
		
		return rolDAO.getRolByNombre(nombre);
	}
	
}
