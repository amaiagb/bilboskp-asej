package com.asej.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.asej.model.Partida;

public class PartidaDAO {

	public static boolean addPartida(Partida nuevaPartida) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO partida (fecha, jugadores, descripcion, estado, puntuacion, id_suscriptor, id_sala) VALUES (?,?,?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			
		    ps.setTimestamp(1, Timestamp.valueOf(nuevaPartida.getFecha()));			
			ps.setInt(2, nuevaPartida.getJugadores());
		    ps.setString(3, nuevaPartida.getDescripcion());
		    ps.setString(4, "programado");
		    ps.setInt(5, 0);
		    ps.setInt(6, 1);
		    ps.setInt(7, nuevaPartida.getSala().getId_sala());
			
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

	public static List<Partida> getPartidas() {
		List<Partida> productos = new ArrayList<Partida>();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_partida, fecha, jugadores, descripcion, estado, puntuacion FROM partida";
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Partida p = new Partida();
				p.setId_partida(rs.getInt("id_partida"));
				LocalDateTime fecha = rs.getObject("fecha", LocalDateTime.class);
	            if (fecha != null) {
	                p.setFecha(fecha); // Establecer la fecha en el objeto Partida
	            }				p.setJugadores(rs.getInt("jugadores"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setEstado(rs.getString("estado"));
				p.setPuntuacion(rs.getInt("puntuacion"));
			

				

				
				productos.add(p);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return productos;
	}

}
