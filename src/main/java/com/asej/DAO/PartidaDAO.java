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
import com.asej.model.Sala;

public class PartidaDAO {

	public static List<Partida> getPartidas() {
		List<Partida> productos = new ArrayList<Partida>();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_partida, fecha, jugadores, descripcion, estado, puntuacion FROM partida;";
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
	
	public static boolean addPartida(Partida nuevaPartida, int id_suscriptor) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO partida (fecha, jugadores, descripcion, estado, puntuacion, id_suscriptor, id_sala, codigo) VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			
		    ps.setTimestamp(1, Timestamp.valueOf(nuevaPartida.getFecha()));			
			ps.setInt(2, nuevaPartida.getJugadores());
		    ps.setString(3, nuevaPartida.getDescripcion());
		    ps.setString(4, "programada");
		    ps.setInt(5, 0);
		    ps.setInt(6, id_suscriptor);
		    ps.setInt(7, nuevaPartida.getSala().getId_sala());
		    ps.setString(8, "aaa");
			
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
	
	public static boolean updatePartida(Partida partida) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "UPDATE partida SET fecha = ?, jugadores = ?, descripcion = ?, id_sala = ?, puntuacion =  ? WHERE id_partida = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
		    ps.setTimestamp(1, Timestamp.valueOf(partida.getFecha()));			
			ps.setInt(2, partida.getJugadores());
			ps.setString(3, partida.getDescripcion());
		    ps.setInt(4, partida.getSala().getId_sala());
		    ps.setInt(5, 0);
		    ps.setInt(6, partida.getId_partida());

						
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

	public static boolean deletePartida(Partida partida) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM partida WHERE id_partida = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, partida.getId_partida());
			
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
	
	public static Partida getPartidabyId(int id) {
		 Connection con = AccesoBD.getConnection();
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    Partida partida = null;

		    String sql = "SELECT * FROM partida WHERE id_partida = ?";
		    try {
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, id);

		        rs = ps.executeQuery();
		        if (rs.next()) {
		            partida = new Partida();
		            partida.setId_partida(rs.getInt("id_partida"));
		            partida.setFecha(rs.getObject("fecha", LocalDateTime.class));
		            partida.setJugadores(rs.getInt("jugadores"));
		            partida.setDescripcion(rs.getString("descripcion"));

		            Sala sala = new Sala();
		            sala.setId_sala(rs.getInt("id_sala"));
		            partida.setSala(sala);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        AccesoBD.closeConnection(rs, ps, con);
		    }

		    return partida;
	}
	
	
	public static List<Partida> getPartidasById(int id) {
		List<Partida> partidas = new ArrayList<Partida>();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_partida, fecha, jugadores, descripcion, p.estado, puntuacion, p.id_sala AS id_sala, s.nombre AS nombre_sala, s.tipo FROM partida p inner join sala s ON s.id_sala = p.id_sala WHERE id_suscriptor = ? ORDER BY fecha DESC;";
		//String sql = "SELECT * FROM partida p WHERE p.id_suscriptor = ?;";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Partida p = new Partida();
				p.setId_partida(rs.getInt("id_partida"));
				LocalDateTime fecha = rs.getObject("fecha", LocalDateTime.class);
	            if (fecha != null) {
	                p.setFecha(fecha); // Establecer la fecha en el objeto Partida
	            }
	            p.setJugadores(rs.getInt("jugadores"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setEstado(rs.getString("estado"));
				p.setPuntuacion(rs.getInt("puntuacion"));
				
				Sala sala = new Sala();
				sala.setId_sala(rs.getInt("id_sala"));
				sala.setNombre(rs.getString("nombre_sala"));
				sala.setTipo(rs.getString("tipo"));
				p.setSala(sala);
				
				System.out.println("partida: "+p);
				partidas.add(p);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		System.out.println("DAO partidas: "+partidas);
		return partidas;
	}

	public static List<Partida> getUltimasPartidasJugadasById(int id) {
		List<Partida> partidas = new ArrayList<Partida>();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_partida, fecha, jugadores, descripcion, p.estado, puntuacion, p.id_sala AS id_sala, s.nombre AS nombre_sala, s.tipo FROM partida p inner join sala s ON s.id_sala = p.id_sala WHERE id_suscriptor = ? AND p.estado = 'terminada' ORDER BY fecha ASC;";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Partida p = new Partida();
				p.setId_partida(rs.getInt("id_partida"));
				LocalDateTime fecha = rs.getObject("fecha", LocalDateTime.class);
	            if (fecha != null) {
	                p.setFecha(fecha); // Establecer la fecha en el objeto Partida
	            }
	            p.setJugadores(rs.getInt("jugadores"));
				p.setDescripcion(rs.getString("descripcion"));
				p.setEstado(rs.getString("estado"));
				p.setPuntuacion(rs.getInt("puntuacion"));
				
				Sala sala = new Sala();
				sala.setId_sala(rs.getInt("id_sala"));
				sala.setNombre(rs.getString("nombre_sala"));
				sala.setTipo(rs.getString("tipo"));
				
				p.setSala(sala);
				
				partidas.add(p);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return partidas;
	}

	public int getNumPartidasProgramadas(int id) {
		int count = 0;
		
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(id_partida) AS partidas FROM partida WHERE id_suscriptor = ? AND estado='programada';";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("partidas");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(rs, ps, con);
		}
		
		return count;
	}


}
