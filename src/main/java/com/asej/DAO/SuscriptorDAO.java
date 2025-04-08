package com.asej.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.asej.model.Suscriptor;

public class SuscriptorDAO {

	public Suscriptor login(Suscriptor suscriptor) {
		
		Suscriptor s = null;
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_suscriptor, nombre, usuario, contrasena, email, fecha_alta FROM suscriptor WHERE usuario=? AND contrasena=?;";		
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, suscriptor.getUsuario());
			ps.setString(2, suscriptor.getContrasena());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				s = new Suscriptor();
				s.setId(rs.getInt("id_suscriptor"));
				s.setNombre(rs.getString("nombre"));
				s.setUsuario(rs.getString("usuario"));
				s.setContrasena(rs.getString("contrasena"));
				s.setEmail(rs.getString("email"));
				s.setFecha_alta(rs.getDate("fecha_alta").toLocalDate());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return s;
	}

	public int addSuscriptor(Suscriptor nuevoSuscriptor) {

		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO suscriptor (nombre, usuario, contrasena, email, fecha_alta) VALUES (?,?,?,?,?);";		
		int idGenerado = 0;
		
		try {
			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, nuevoSuscriptor.getNombre());
			ps.setString(2, nuevoSuscriptor.getUsuario());
			ps.setString(3, nuevoSuscriptor.getContrasena());
			ps.setString(4, nuevoSuscriptor.getEmail());
			Date fecha = Date.valueOf(nuevoSuscriptor.getFecha_alta());
			ps.setDate(5, fecha);
			

			if(ps.executeUpdate() > 0) {
				
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Recupera el ID generado (usualmente es un entero)
                        idGenerado = generatedKeys.getInt(1);  // O .getInt(1) si es un entero
                        System.out.println("El ID generado es: " + idGenerado);
                    }
                }
				return idGenerado;
			
			} else  {
				return idGenerado;
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return idGenerado;
	}

}
