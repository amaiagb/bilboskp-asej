package com.asej.DAO;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.asej.model.Partida;
import com.asej.model.Sala;

public class SalaDAO {

	public Sala getRolById(int id) {
		Sala sala = new Sala();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id_sala, nombre, tipo, estado FROM sala WHERE id = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				sala = new Sala(rs.getInt("id_sala"), rs.getString("nombre"), rs.getString("tipo"), rs.getString("estado"));
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
		
		String sql = "SELECT id_sala, nombre, tipo, estado FROM sala";
		
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Sala sala = new Sala(rs.getInt("id_sala"), rs.getString("nombre"), rs.getString("tipo"), rs.getString("estado"));
				
				salas.add(sala);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return salas;
	}

	// HAY QUE ARREGLAR ESTOS METODOS PARA GESTIONAR BIEN EL ESTADO
	public boolean createOrUpdateSala(Sala sala) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "";
		
		if(sala.getId_sala() != 0) {
			sql = "UPDATE sala SET nombre = ?, tipo = ?, estado = ? WHERE id_sala = ?";
		}else {
			sql = "INSERT INTO sala (nombre, tipo, estado) VALUES (?,?,?)";
		}
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, sala.getNombre());
			ps.setString(2, sala.getTipo());
			 if(sala.getId_sala() != 0) {
		            // Si es un UPDATE, no cambiamos el estado, solo seteamos el id_sala al final
					ps.setString(3, "Activado");
		            ps.setInt(4, sala.getId_sala());   // id_sala
		        } else {
		            // Si es un INSERT, setear estado al índice 3
		            ps.setString(3, sala.getEstado()); // estado para INSERT
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
	
//	lo dfejo por si acaso lo ecesit pa algo, pero como principol el de ambos, y a su vez, implementarlo en partida
//	public boolean addSala(Sala sala) {
//		Connection con = AccesoBD.getConnection();
//		PreparedStatement ps = null;
//		
//		String sql = "INSERT INTO sala (nombre, tipo, estado) VALUES (?,?,?)";
//		
//		try {
//			ps = con.prepareStatement(sql);
//			
//			ps.setString(1, sala.getNombre());
//			ps.setString(2, sala.getTipo());
//			ps.setString(3, "Activado");
//			
//			if(ps.executeUpdate() > 0) {
//				return true;
//			}else {
//				return false;
//			}
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			AccesoBD.closeConnection(null, ps, con);
//		}
//		
//		return false;
//	}
//	
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

	public boolean desactivarSala(Sala s) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		System.out.println(s);
		String sql = "UPDATE sala SET estado = ? WHERE id_sala = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, "deshabilitada");
			ps.setInt(2, s.getId_sala());

			
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

	public Sala getSalaById(int id) {
		 Connection con = AccesoBD.getConnection();
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    Sala sala = null;

		    String sql = "SELECT * FROM sala WHERE id_sala = ?";
		    try {
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, id);

		        rs = ps.executeQuery();
		        if (rs.next()) {
		            sala = new Sala();
		            sala.setId_sala(rs.getInt("id_sala"));
		            sala.setNombre(rs.getString("nombre"));
		            sala.setTipo(rs.getString("tipo"));
		            sala.setEstado(rs.getString("estado"));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        AccesoBD.closeConnection(rs, ps, con);
		    }

		    return sala;
	}

	public int countSalas() {
		Connection con = AccesoBD.getConnection();
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int numSalas = 0;

	    String sql = "SELECT COUNT(id_sala) AS numSalas FROM sala WHERE LOWER(estado) = 'habilitada'";
	    try {
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	        	numSalas = rs.getInt("numSalas");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        AccesoBD.closeConnection(rs, ps, con);
	    }

	    return numSalas;
	}

	
}
