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
	
	public static boolean addPartida(Partida nuevaPartida, int idSuscriptor, String codigo) {
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
	        ps.setInt(6, idSuscriptor); 
	        ps.setInt(7, nuevaPartida.getSala().getId_sala());
	        ps.setString(8, codigo); 

	        return ps.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
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
		
		String sql = "SELECT id_partida, fecha, jugadores, descripcion, p.estado, puntuacion, p.id_sala AS id_sala, s.nombre AS nombre_sala, s.tipo FROM partida p inner join sala s ON s.id_sala = p.id_sala WHERE id_suscriptor = ? AND p.estado = 'finalizada' ORDER BY fecha ASC;";
		
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

	public void deletePartidasBySuscriptor(int id_suscriptor) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM partida WHERE id_suscriptor = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id_suscriptor);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deletePartidasBySuscriptor(Connection con, int id_suscriptor) {
		PreparedStatement ps = null;
		String sql = "DELETE FROM partida WHERE id_suscriptor = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id_suscriptor);
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
		
	public static List<Partida> getPartidasByIdYSala(int id_suscriptor, int id_sala) {
		  List<Partida> partidas = new ArrayList<>();
		    Connection con = AccesoBD.getConnection();
		    PreparedStatement ps = null;
		    ResultSet rs = null;

		    String sql = "SELECT * FROM partida WHERE id_suscriptor = ? AND id_sala = ? AND estado='finalizada' ORDER BY puntuacion DESC";

		    try {
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, id_suscriptor);
		        ps.setInt(2, id_sala);

		        rs = ps.executeQuery();

		        SalaDAO salaDAO = new SalaDAO();

		        while (rs.next()) {
		            Partida partida = new Partida();
		            partida.setId_partida(rs.getInt("id_partida"));
		            partida.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
					partida.setDescripcion(rs.getString("descripcion"));
		            partida.setPuntuacion(rs.getInt("puntuacion"));
		            partida.setSala(salaDAO.getSalaById(rs.getInt("id_sala")));
		            partidas.add(partida);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        AccesoBD.closeConnection(rs, ps, con);
		    }

		    return partidas;
		}

	public static Partida mirarCodigo(String codigo) {
		 Connection con = AccesoBD.getConnection();
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    Partida p = null;

		    String sql = "SELECT p.*, s.id_sala, s.nombre AS nombre_sala, s.tipo FROM partida p " +
		                 "INNER JOIN sala s ON p.id_sala = s.id_sala " +
		                 "WHERE p.codigo = ?";

		    try {
		        ps = con.prepareStatement(sql);
		        ps.setString(1, codigo);
		        rs = ps.executeQuery();

		        if (rs.next()) {
		            p = new Partida();
		            p.setId_partida(rs.getInt("id_partida"));
		            p.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
		            p.setJugadores(rs.getInt("jugadores"));
		            p.setDescripcion(rs.getString("descripcion"));
		            p.setEstado(rs.getString("estado"));
		            p.setPuntuacion(rs.getInt("puntuacion"));

		            Sala sala = new Sala();
		            sala.setId_sala(rs.getInt("id_sala"));
		            sala.setNombre(rs.getString("nombre_sala"));
		            sala.setTipo(rs.getString("tipo"));
		            p.setSala(sala);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        AccesoBD.closeConnection(rs, ps, con);
		    }

		    return p;
		}

	public static boolean actualizarPartida(Partida partida) {
		Connection con = AccesoBD.getConnection();
	    PreparedStatement ps = null;

	    String sql = "UPDATE partida SET fecha = ?, jugadores = ?, descripcion = ?, id_sala = ?, puntuacion = ?, estado = ? WHERE id_partida = ?";

	    try {
	        ps = con.prepareStatement(sql);

	        ps.setTimestamp(1, Timestamp.valueOf(partida.getFecha()));
	        ps.setInt(2, partida.getJugadores());
	        ps.setString(3, partida.getDescripcion());
	        ps.setInt(4, partida.getSala().getId_sala());
	        ps.setInt(5, partida.getPuntuacion());
	        ps.setString(6, partida.getEstado());
	        ps.setInt(7, partida.getId_partida());

	        return ps.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        AccesoBD.closeConnection(null, ps, con);
	    }
	    return false;
	}

	public static List<Partida> getPartidasByIdBajo(int id_suscriptor) {
		 List<Partida> partidas = new ArrayList<>();
		    Connection con = AccesoBD.getConnection();
		    PreparedStatement ps = null;
		    ResultSet rs = null;

		    String sql = "SELECT * FROM partida WHERE id_suscriptor = ? AND estado='finalizada' ORDER BY puntuacion DESC";

		    try {
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, id_suscriptor);

		        rs = ps.executeQuery();

		        SalaDAO salaDAO = new SalaDAO();

		        while (rs.next()) {
		            Partida partida = new Partida();
		            partida.setId_partida(rs.getInt("id_partida"));
		            partida.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
					partida.setDescripcion(rs.getString("descripcion"));
		            partida.setPuntuacion(rs.getInt("puntuacion"));
		            partida.setSala(salaDAO.getSalaById(rs.getInt("id_sala")));
		            partidas.add(partida);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        AccesoBD.closeConnection(rs, ps, con);
		    }

		    return partidas;
		}

	public boolean existePartidaEnFecha(LocalDateTime fecha) {
		Connection con = AccesoBD.getConnection();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean existe = false;

	    String sql = "SELECT COUNT(*) FROM partida WHERE fecha = ?";

	    try {
	        ps = con.prepareStatement(sql);
	        ps.setTimestamp(1, Timestamp.valueOf(fecha));
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            existe = rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        AccesoBD.closeConnection(rs, ps, con);
	    }

	    return existe;
	}

	
	public static List<Partida> getPartidasByIdYEstado(int id_suscriptor, String estado) {
	    List<Partida> partidas = new ArrayList<>();
	    Connection con = AccesoBD.getConnection();
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    String sql = "SELECT * FROM partida WHERE id_suscriptor = ? AND estado = ? ORDER BY id_partida DESC";

	    try {
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, id_suscriptor);
	        ps.setString(2, estado);

	        rs = ps.executeQuery();

	        SalaDAO salaDAO = new SalaDAO();

	        while (rs.next()) {
	            Partida partida = new Partida();
	            partida.setId_partida(rs.getInt("id_partida"));
	            partida.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
	            partida.setPuntuacion(rs.getInt("puntuacion"));
	            partida.setDescripcion(rs.getString("descripcion")); 
	            partida.setJugadores(rs.getInt("jugadores"));         
	            partida.setEstado(rs.getString("estado"));
	            partida.setSala(salaDAO.getSalaById(rs.getInt("id_sala")));
	            partidas.add(partida);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        AccesoBD.closeConnection(rs, ps, con);
	    }

	    return partidas;
	}

	public static boolean crearPartidaSuscriptor(Partida nuevaPartida, int idSuscriptor) {
	    Connection con = AccesoBD.getConnection();
	    PreparedStatement ps = null;

	    String sql = "INSERT INTO partida (estado, puntuacion, id_suscriptor, id_sala) VALUES (?,?,?,?)";

	    try {
	        ps = con.prepareStatement(sql);

	        ps.setString(1, "finalizada"); 
	        ps.setInt(2, nuevaPartida.getPuntuacion()); 
	        ps.setInt(3, idSuscriptor);  
	        ps.setInt(4, nuevaPartida.getSala().getId_sala()); 

	        return ps.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        AccesoBD.closeConnection(null, ps, con);
	    }
	    return false;
	}


}
