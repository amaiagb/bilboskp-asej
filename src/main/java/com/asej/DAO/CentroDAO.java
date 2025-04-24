package com.asej.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.asej.model.Centro;
import com.asej.model.Rol;
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

	public static List<Centro> getCentrosPendientes() {
		List<Centro> centros = null;
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_centro, nombre_centro, localidad, etapas_educativas, num_alumnado, c.id_suscriptor, c.estado, s.nombre, s.usuario,s.email,s.id_rol, s.fecha_alta "
				+ "FROM centro_educativo c INNER JOIN suscriptor s ON s.id_suscriptor=c.id_suscriptor "
				+ "WHERE estado = 'pendiente';";		
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			centros = new ArrayList<>();
			while(rs.next()) {
				Centro c = new Centro(rs.getInt("id_centro"), rs.getString("nombre_centro"), rs.getString("localidad"), rs.getInt("etapas_educativas"), rs.getInt("num_alumnado"));
				c.setId(rs.getInt("id_suscriptor"));
				c.setEstado(rs.getString("estado"));
				c.setFecha_alta(rs.getDate("fecha_alta").toLocalDate());
				centros.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return centros;
	}

	public boolean updateEstadoCentro(int id_centro) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "UPDATE centro_educativo SET estado = 'aceptado' WHERE id_centro = ? ;";		
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id_centro);
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		return false;
	}

	public Centro getCentroById(int id_centro) {
		Centro c = null;
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_centro, nombre_centro, localidad, etapas_educativas, num_alumnado, c.id_suscriptor, c.estado, s.nombre, s.usuario,s.email,s.id_rol, s.fecha_alta "
				+ "FROM centro_educativo c INNER JOIN suscriptor s ON s.id_suscriptor=c.id_suscriptor "
				+ "WHERE id_centro = ?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id_centro);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				c = new Centro(id_centro, rs.getString("nombre_centro"), rs.getString("localidad"), rs.getInt("etapas_educativas"), rs.getInt("num_alumnado"));
				c.setId(rs.getInt("id_suscriptor"));
				c.setEstado(rs.getString("estado"));
				c.setFecha_alta(rs.getDate("fecha_alta").toLocalDate());
				c.setEmail(rs.getString("email"));
				c.setNombre(rs.getString("nombre"));
				c.setUsuario(rs.getString("usuario"));
				Rol rol = new Rol(rs.getInt("id_rol"), "centro");
				c.setRol(rol);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return c;
	}

	public int countCentros() {
		int numCentros = 0;
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(id_centro) AS numCentros FROM centro_educativo WHERE estado='aceptado';";		
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				numCentros = rs.getInt("numCentros");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			AccesoBD.closeConnection(rs, ps, con);
		}
		
		return numCentros;
	}

	public int countSolicitudes() {
		int numSolicitudes = 0;
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(id_centro) AS numSolicitudes FROM centro_educativo WHERE estado='pendiente';";		
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				numSolicitudes = rs.getInt("numSolicitudes");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			AccesoBD.closeConnection(rs, ps, con);
		}
		
		return numSolicitudes;
	}

	public boolean deleteCentro(int id_centro) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM centro_educativo  WHERE id_centro = ? ;";		
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id_centro);
			
			if(ps.executeUpdate() > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		return false;
	}

	public List<Centro> getCentros() {
		List<Centro> centros = null;
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_centro, nombre_centro, localidad, etapas_educativas, num_alumnado, c.id_suscriptor, c.estado, s.nombre, s.usuario,s.email,s.id_rol, s.fecha_alta "
				+ "FROM centro_educativo c INNER JOIN suscriptor s ON s.id_suscriptor=c.id_suscriptor "
				+ " ORDER BY s.fecha_alta DESC;";		
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			centros = new ArrayList<>();
			while(rs.next()) {
				Centro c = new Centro(rs.getInt("id_centro"), rs.getString("nombre_centro"), rs.getString("localidad"), rs.getInt("etapas_educativas"), rs.getInt("num_alumnado"));
				c.setId(rs.getInt("id_suscriptor"));
				c.setEstado(rs.getString("estado"));
				c.setFecha_alta(rs.getDate("fecha_alta").toLocalDate());
				c.setNombre(rs.getString("nombre"));
				c.setEmail(rs.getString("email"));
				centros.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return centros;
	}

	public boolean deleteCentro(Connection con, int id_centro) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "DELETE FROM centro_educativo  WHERE id_centro = ? ;";		
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id_centro);
			System.out.println("Ejecutando: " + sql + " con id_centro=" + id_centro);
			if(ps.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}

}
