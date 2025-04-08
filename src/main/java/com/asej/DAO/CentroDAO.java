package com.asej.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.asej.model.Centro;
import com.asej.model.Suscriptor;

public class CentroDAO {

	SuscriptorDAO suscriptorDAO = new SuscriptorDAO();

	public boolean addCentro(Centro nuevoCentro) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int id_suscriptor = suscriptorDAO.addSuscriptor(nuevoCentro);
		System.out.println(id_suscriptor);
		
		if(id_suscriptor > 0 ) {
			
			String sql = "INSERT INTO centro_educativo (nombre, localidad, etapas_educativas, num_alumnado, id_suscriptor) VALUES (?,?,?,?,?);";		
			
			try {
				ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, nuevoCentro.getNombre_centro());
				ps.setString(2, nuevoCentro.getLocalidad());
				ps.setInt(3, nuevoCentro.getEtapas_educativas());
				ps.setInt(4, nuevoCentro.getNum_alumnado());
				ps.setInt(5, id_suscriptor);
				
				if(ps.executeUpdate() > 0) {
					return true;
				
				} else  {
					System.out.println("Error en el insert de centro");
					
					return false;
				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		return false;
	}

}
