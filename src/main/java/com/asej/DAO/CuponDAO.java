package com.asej.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.asej.model.Cupon;
import com.asej.model.Rol;

public class CuponDAO {

	// Comprar cupones
	// crear el n�mero de cupones que nos compra el suscriptor
	// insertar en la tabla de hist�rico la asociaci�n de cupones con el
	// suscriptor

	// Devolver cupones
	// Cambiar el estado del n�mero de cupones que nos indica el suscriptor a
	// estado "devuelto"

	// Devuelve todos los datos de los cupones de un suscriptor del cual sabemos el
	// nombre de usuario
	public List<Cupon> getCupones(int id_suscriptor) {
		List<Cupon> cupones = new ArrayList<Cupon>();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT c.id_cupon, c.fecha_compra, c.fecha_caducidad, c.fecha_devolucion, c.estado, c.tipo, c.precio FROM cupon c "
				+ "INNER JOIN historial_cupones hc ON c.id_cupon = hc.id_cupon "
				+ "INNER JOIN suscriptor s ON hc.id_suscriptor = s.id_suscriptor " + "WHERE s.id_suscriptor = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id_suscriptor);

			rs = ps.executeQuery();

			while (rs.next()) {

				Cupon cupon = new Cupon(rs.getInt("id_cupon"), rs.getDate("fecha_compra"),
						rs.getDate("fecha_caducidad"), rs.getDate("fecha_devolucion"), rs.getString("estado"),
						rs.getString("tipo"), rs.getFloat("precio"));

				cupones.add(cupon);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoBD.closeConnection(null, ps, con);
		}

		return cupones;
	}

	// Devuelve el estado de un cup�n a devuelto
	public boolean returnCupon(int id_cupon) {

		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;

		LocalDate fechaDevolucion = LocalDate.now();
		Date sqlFechaDevolucion = Date.valueOf(fechaDevolucion);

		String sql = "UPDATE cupon SET estado = 'devuelto', fecha_devolucion = ? WHERE id_cupon = ? AND estado = ?";

		try {
			ps = con.prepareStatement(sql);

			ps.setDate(1, sqlFechaDevolucion);
			ps.setInt(2, id_cupon);
			ps.setString(3, "disponible");

			if (ps.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoBD.closeConnection(null, ps, con);
		}

		return false;
	}

	public boolean programarCupon(int numCupones) {

		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;

		// hay que a�adir el id_suscriptor porque si no esta cambiando los cupones que
		// encuentre primero en toda la BD sean de quien sean
		String sql = "UPDATE cupon SET estado = 'programado' WHERE estado = ? LIMIT ?";

		try {

			ps = con.prepareStatement(sql);

			ps.setString(1, "disponible");
			ps.setInt(2, numCupones);

			if (ps.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoBD.closeConnection(null, ps, con);
		}

		return false;
	}

	public boolean setCuponEnUso() {

		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;

		String sql = "UPDATE cupon SET estado = 'en uso' WHERE estado = ? AND tipo = ? LIMIT 1";

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, "programado");
			ps.setString(2, "centro");

			if (ps.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoBD.closeConnection(null, ps, con);
		}

		return false;
	}

	public boolean setCuponUsado() {

		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;

		String sql = "UPDATE cupon SET estado = 'usado' WHERE estado = ? AND tipo = ? LIMIT 1";

		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, "en uso");
			ps.setString(2, "centro");

			if (ps.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoBD.closeConnection(null, ps, con);
		}

		return false;
	}

	public List<Integer> createCupones(int numeroCupones, String nombreRol) {

		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;

		List<Integer> id_cupones = new ArrayList<>();

		LocalDate fechaCompra = LocalDate.now();
		LocalDate fechaCaducidad = fechaCompra.plusDays(30);

		// Para convertir las fechas a java.sql.Date
		Date sqlFechaCompra = Date.valueOf(fechaCompra);
		Date sqlFechaCaducidad = Date.valueOf(fechaCaducidad);

		String sql = "INSERT INTO cupon (fecha_compra, fecha_caducidad, estado, precio, fecha_devolucion, tipo) VALUES (?,?,?,?,?,?) ";

		try {

			for (int i = 0; i < numeroCupones; i++) {

				ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				ps.setDate(1, sqlFechaCompra);
				ps.setDate(2, sqlFechaCaducidad);
				ps.setString(3, "disponible");
				if (nombreRol.equalsIgnoreCase("suscriptor")) {
					ps.setFloat(4, (float) 4.95);
					ps.setString(6, nombreRol);
				} else if (nombreRol.equalsIgnoreCase("centro")) {
					ps.setFloat(4, (float) 0);
					ps.setString(6, nombreRol);
				}
				ps.setString(5, null);

				if (ps.executeUpdate() > 0) { // Si el n�mero de filas es mayor que 0
					generatedKeys = ps.getGeneratedKeys();
					if (generatedKeys.next()) {
						int idGenerado = generatedKeys.getInt(1); // el id_cupon generado
						id_cupones.add(idGenerado);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoBD.closeConnection(null, ps, con);
		}

		return id_cupones;
	}

	public boolean actualizarHistorialCupones(int id_suscriptor, int id_cupon) {

		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;

		String sql = "INSERT INTO historial_cupones (id_suscriptor, id_cupon) VALUES (?,?)";

		try {
			ps = con.prepareStatement(sql);

			ps.setInt(1, id_suscriptor);
			ps.setInt(2, id_cupon);

			if (ps.executeUpdate() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoBD.closeConnection(null, ps, con);
		}

		return false;
	}

	public List<Cupon> getCuponesDisponibles(int id) {
		List<Cupon> cupones = new ArrayList<Cupon>();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT c.id_cupon, c.fecha_compra, c.fecha_caducidad, c.fecha_devolucion, c.estado, c.tipo, c.precio FROM cupon c "
				+ "INNER JOIN historial_cupones hc ON c.id_cupon = hc.id_cupon "
				+ "INNER JOIN suscriptor s ON hc.id_suscriptor = s.id_suscriptor "
				+ "WHERE s.id_suscriptor = ? AND c.estado = 'disponible'";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

				Cupon cupon = new Cupon(rs.getInt("id_cupon"), rs.getDate("fecha_compra"),
						rs.getDate("fecha_caducidad"), rs.getDate("fecha_devolucion"), rs.getString("estado"),
						rs.getString("tipo"), rs.getFloat("precio"));
				cupones.add(cupon);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoBD.closeConnection(null, ps, con);
		}

		return cupones;
	}

	public List<Cupon> getCuponesProgramados(int id) {
		List<Cupon> cupones = new ArrayList<Cupon>();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT c.id_cupon, c.fecha_compra, c.fecha_caducidad, c.fecha_devolucion, c.estado, c.tipo, c.precio FROM cupon c "
				+ "INNER JOIN historial_cupones hc ON c.id_cupon = hc.id_cupon "
				+ "INNER JOIN suscriptor s ON hc.id_suscriptor = s.id_suscriptor "
				+ "WHERE s.id_suscriptor = ? AND c.estado = 'programado'";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {

				Cupon cupon = new Cupon(rs.getInt("id_cupon"), rs.getDate("fecha_compra"),
						rs.getDate("fecha_caducidad"), rs.getDate("fecha_devolucion"), rs.getString("estado"),
						rs.getString("tipo"), rs.getFloat("precio"));
				cupones.add(cupon);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoBD.closeConnection(null, ps, con);
		}

		return cupones;
	}

	public void deleteHistorialBySuscriptorId(Connection con, int id_suscriptor) {
		PreparedStatement ps = null;
		String sql = "DELETE FROM historial_cupones WHERE id_suscriptor = ?;";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id_suscriptor);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCuponesById(Connection con, int id_cupon) {
		PreparedStatement ps = null;
		String sql = "DELETE FROM cupon WHERE id_cupon = ?;";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id_cupon);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Cupon> getCuponesDevolviblesCentro(int id_suscriptor) {

		List<Cupon> cupones = null;
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT c.id_cupon, c.fecha_compra, c.fecha_caducidad, c.fecha_devolucion, c.estado, c.tipo, c.precio FROM cupon c "
				+ "INNER JOIN historial_cupones hc ON c.id_cupon = hc.id_cupon "
				+ "INNER JOIN suscriptor s ON hc.id_suscriptor = s.id_suscriptor "
				+ "WHERE s.id_suscriptor = ? AND (c.estado = 'disponible' OR c.estado = 'programado')";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id_suscriptor);
			rs = ps.executeQuery();

			cupones = new ArrayList<Cupon>();

			while (rs.next()) {

				Cupon cupon = new Cupon(rs.getInt("id_cupon"), rs.getDate("fecha_compra"),
						rs.getDate("fecha_caducidad"), rs.getDate("fecha_devolucion"), rs.getString("estado"),
						rs.getString("tipo"), rs.getFloat("precio"));
				cupones.add(cupon);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoBD.closeConnection(rs, ps, con);
		}

		return cupones;

	}

	public int createCuponBienvenida() {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;

		LocalDate fechaCompra = LocalDate.now();
		LocalDate fechaCaducidad = fechaCompra.plusDays(365 * 100);

		// Para convertir las fechas a java.sql.Date
		Date sqlFechaCompra = Date.valueOf(fechaCompra);
		Date sqlFechaCaducidad = Date.valueOf(fechaCaducidad);

		int id_generado = 0;

		String sql = "INSERT INTO cupon (fecha_compra, fecha_caducidad, estado, precio, fecha_devolucion, tipo) VALUES (?,?,?,?,?,?) ";

		try {

			ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setDate(1, sqlFechaCompra);
			ps.setDate(2, sqlFechaCaducidad);
			ps.setString(3, "disponible");
			ps.setFloat(4, (float) 0);
			ps.setString(5, null);
			ps.setString(6, "suscriptor");

			if (ps.executeUpdate() > 0) {

				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						id_generado = generatedKeys.getInt(1); // getInt(1) si es un entero
					}
				}
				return id_generado;

			} else {
				return id_generado;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccesoBD.closeConnection(null, ps, con);
		}

		return id_generado;
	}

}
