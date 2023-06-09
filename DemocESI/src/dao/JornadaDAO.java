package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Jornada;
import modelo.Material;

public class JornadaDAO extends Conexion {

	public boolean Agregar(Jornada j) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			c = conectar();
			String Sql = "INSERT INTO `mydb`.`jornada`(`Ref_inst`,`objetivo`,`titulo`)VALUES(?, ?, ?)";
			PreparedStatement pst = c.prepareStatement(Sql);

			pst.setString(1, j.getReferenteInstitucional());
			pst.setString(2, j.getObjetivo());
			pst.setString(3, j.getTitulo());
			pst.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return filasAfectadas != 0;
	}

	public boolean Eliminar(String tituloJornada) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			c = conectar();
			String sql = "DELETE FROM `mydb`.`jornada` WHERE Jornadaid = ?";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, tituloJornada);
			pst.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return filasAfectadas != 0;
	}

	public boolean Modificar(int numeroJornada, Jornada j) {
		int filasAfectadas = 0;
		Connection c = null;
		try {
			c = conectar();
			String Sql = "UPDATE `mydb`.`jornada` SET`Ref_inst` = ?,`objetivo` = ? ,`titulo` = ? WHERE `Jornadaid` = ? ";
			PreparedStatement pst = c.prepareStatement(Sql);
			pst.setString(1, j.getReferenteInstitucional());
			pst.setString(2, j.getObjetivo());
			pst.setString(3, j.getTitulo());
			pst.setInt(4, numeroJornada);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return filasAfectadas != 0;
	}

	public ArrayList<Jornada> Consultar(String sentencia) {
		ArrayList<Jornada> jornadas = new ArrayList<Jornada>();
		jornadas = traerTodas();
		Connection c = null;
		try {
			c = conectar();
			String Sql = "";
			PreparedStatement pst = c.prepareStatement(Sql);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return jornadas;
	}

	public ArrayList<Jornada> traerTodas() {
		ArrayList<Jornada> jornadas = new ArrayList<Jornada>();

		Connection c = null;

		try {
			c = conectar();
			String sql = "SELECT `Ref_inst`,`Jornadaid`,`objetivo`,`titulo`FROM `mydb`.`jornada`;";

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String referente = rs.getString("Ref_inst");
				String objetivo = rs.getString("objetivo");
				String titulo = rs.getString("titulo");
				//ArrayList<Material> materiales = ;

				Jornada j = new Jornada(/*j.getMateriales(),*/null, titulo, referente, objetivo); //faltan los materiales (el null)
				jornadas.add(j);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();

		} finally {
			try {
				if (c != null) {
					c.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return jornadas;
	}

}
