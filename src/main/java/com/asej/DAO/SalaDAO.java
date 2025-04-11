package com.asej.DAO;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asej.model.Sala;

public class SalaDAO {

	public Sala getRolById(int id) {
		Sala sala = new Sala();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_sala, nombre, tipo FROM sala WHERE id = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				sala = new Sala(rs.getInt("id_sala"), rs.getString("nombre"), rs.getString("tipo"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return sala;
	}	
	
	public List<Sala> getSalas() {
		List<Sala> salas = new ArrayList<Sala>();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_sala, nombre, tipo FROM sala";
		
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Sala sala = new Sala(rs.getInt("id_sala"), rs.getString("nombre"), rs.getString("tipo"));
				
				salas.add(sala);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return salas;
	}

	public boolean createOrUpdateSala(Sala sala) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "";
		
		if(sala.getId_sala() != 0) {
			sql = "UPDATE sala SET nombre = ?, tipo = ? WHERE id = ?";
		}else {
			sql = "INSERT INTO sala (nombre, tipo) VALUES (?,?)";
		}
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, sala.getNombre());
			
			if(sala.getId_sala() != 0) {
				ps.setInt(2, sala.getId_sala());
			}
			
			if(ps.executeUpdate() > 0) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return false;
	}
	
	public boolean addSala(Sala sala) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO sala (nombre, tipo) VALUES (?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, sala.getNombre());
			ps.setString(2, sala.getTipo());
			
			if(ps.executeUpdate() > 0) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return false;
	}
	
	public boolean deleteSala(Sala sala) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM sala WHERE id = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, sala.getId_sala());
			
			if(ps.executeUpdate() > 0) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return false;
	}
}
