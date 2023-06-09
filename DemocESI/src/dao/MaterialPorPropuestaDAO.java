package dao;

import vista.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import modelo.MaterialInstitucional;
import modelo.Material;
import modelo.Propuesta;
import modelo.MaterialPorPropuesta;
import vista.ConsultarPropuestaPanel;

public class MaterialPorPropuestaDAO extends Conexion {

	public int agregarMaterialPorPropuesta(MaterialPorPropuesta mp) {
		// devuelve también el id generado al insertar ese material

		int result = 0;
		Connection c = null;
		try {
			CategoriaDAO catdao = new CategoriaDAO();
			c = conectar();
			String sql = "INSERT INTO `mydb`.`material`(`titulo`,`fuente`,`enlace`,`categorias_idcategorias`,`prioritario`)VALUES(?,?,?,?,?);";
			PreparedStatement pst = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, mp.getTitulo());
			pst.setString(2, mp.getFuente());
			pst.setString(3, mp.getEnlace());
			pst.setString(4, mp.getCategoria());
			pst.setBoolean(5, mp.esDeTratamientoPrioritario());
			;
			ResultSet idGenerado = pst.getGeneratedKeys();
			while (idGenerado.next()) {
				result = idGenerado.getInt(1);
			}
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
		return result;

	}

	public void asignarUnaPropuestaAlMaterial(int numPropuesta, MaterialPorPropuesta mp) {

		Connection c = null;
		try {
			c = conectar();
			String sql = "INSERT INTO `mydb`.`propuesta_has_material`(`Propuesta_idPropuesta`,`Material_idMaterial`)VALUES (?,?);";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setInt(1, numPropuesta);
			pst.setInt(2, agregarMaterialPorPropuesta(mp));
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

	}

	public ArrayList<MaterialPorPropuesta> traerMaterialesporpropuestas() {
		ArrayList<MaterialPorPropuesta> materialesPorPropuesta = new ArrayList<MaterialPorPropuesta>();

		Connection c = null;
		CategoriaDAO ca = new CategoriaDAO();

		try {
			c = conectar();
			String sql = "SELECT `titulo`,`fuente`,`enlace`,`procedencia`,`categorias_idcategorias`,`prioritario`,`descripcion` FROM `material` WHERE procedencia is null;";
			Statement pst = c.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			while (rs.next()) {

				String titulo = rs.getString("titulo");
				String categoria = ca.deIDcategoriasToNombreCategoria(rs.getInt("categorias_idcategorias"));
				String descripcion = rs.getString("descripcion");
				String fuente = rs.getString("fuente");
				String enlace = rs.getString("enlace");

				MaterialPorPropuesta matPorPropuesta = new MaterialPorPropuesta(titulo, categoria, descripcion, fuente,
						enlace);
				materialesPorPropuesta.add(matPorPropuesta);

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

		return materialesPorPropuesta;
	}

	// https://www.java2novice.com/jdbc/auto-generated-keys/

	/*public void cargarLista() {
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT titulo FROM Material";
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String titulo = rs.getString("titulo");
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
	}*/
	
}
