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
		
		if( nuevoCentro.getId() > 0 ) {
			
			String sql = "INSERT INTO centro_educativo (nombre_centro, localidad, etapas_educativas, num_alumnado, id_suscriptor, estado) VALUES (?,?,?,?,?, ?);";		
			
			try {
				ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, nuevoCentro.getNombre_centro());
				ps.setString(2, nuevoCentro.getLocalidad());
				ps.setInt(3, nuevoCentro.getEtapas_educativas());
				ps.setInt(4, nuevoCentro.getNum_alumnado());
				ps.setInt(5, nuevoCentro.getId());
				ps.setString(6, "pendiente");
				
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

	public Centro getCentroByName(String nombre_centro) {
		 
		Centro c = null;
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT nombre_centro, localidad, etapas_educativas, num_alumnado, id_suscriptor, estado FROM centro_educativo WHERE nombre_centro=?;";		
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, nombre_centro);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				c = new Centro(rs.getInt("id_centro"), rs.getString("nombre_centro"), rs.getString("localidad"), rs.getInt("etapas_educativas"), rs.getInt("num_alumnado"));
				c.setId(rs.getInt("id_suscriptor"));
				c.setEstado(rs.getString("estado"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}

	public Centro getCentroBySuscriptor(Suscriptor suscriptorLogin) {
		Centro c = null;
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_centro, nombre_centro, localidad, etapas_educativas, num_alumnado, id_suscriptor, estado FROM centro_educativo WHERE id_suscriptor = ?;";		
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, suscriptorLogin.getId());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				c = new Centro(suscriptorLogin, rs.getInt("id_centro"), rs.getString("nombre_centro"), rs.getString("localidad"), rs.getInt("etapas_educativas"), rs.getInt("num_alumnado"));
				c.setId(rs.getInt("id_suscriptor"));
				c.setEstado(rs.getString("estado"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}

}
