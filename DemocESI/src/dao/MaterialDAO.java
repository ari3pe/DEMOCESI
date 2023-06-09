package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import modelo.MaterialInstitucional;
import modelo.Material;
import modelo.MaterialPorPropuesta;

public class MaterialDAO extends Conexion {

	public void agregarMaterialInstitucional(String Categoria, MaterialInstitucional m) {
		Connection c = null;
		try {
			c = conectar();
			CategoriaDAO catdao = new CategoriaDAO();
			String sql = "INSERT INTO `mydb`.`material`(`titulo`,`fuente`,`enlace`,`procedencia`,`categorias_idcategorias`,`prioritario`)VALUES(?,?,?,?,?,?);";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, m.getTitulo());
			pst.setString(2, m.getFuente());
			pst.setString(3, m.getEnlace());
			pst.setString(4, m.getProcedencia());
			pst.setInt(5, catdao.deCategoriaToIdCategoria(Categoria));
			pst.setBoolean(6, m.esDeTratamientoPrioritario());

			pst.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public ArrayList<Material> traerTodos() {
		ArrayList<Material> materiales = new ArrayList<Material>();
		MaterialPorPropuestaDAO mp = new MaterialPorPropuestaDAO();
		materiales.addAll(traerinstitucionales());
		materiales.addAll(mp.traerMaterialesporpropuestas());

		return materiales;
	}

	public ArrayList<MaterialInstitucional> traerinstitucionales() {

		ArrayList<MaterialInstitucional> materialesInstitucionales = new ArrayList<MaterialInstitucional>();
		Connection c = null;
		CategoriaDAO ca = new CategoriaDAO();

		try {
			c = conectar();
			String sql = "SELECT `titulo`,`fuente`,`enlace`,`procedencia`,`categorias_idcategorias`,`prioritario`,`descripcion`  FROM `mydb`.`material` where procedencia is not null;";
			Statement pst = c.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			while (rs.next()) {

				String titulo = rs.getString("titulo");
				String categoria = ca.deIDcategoriasToNombreCategoria(rs.getInt("categorias_idcategorias"));
				String fuente = rs.getString("fuente");
				String enlace = rs.getString("enlace");
				boolean prioridad = rs.getBoolean("prioritario");
				String descripcion = rs.getString("descripcion");
				String procedencia = rs.getString("procedencia");
				MaterialInstitucional n = new MaterialInstitucional(titulo, categoria, descripcion, fuente, enlace,
						procedencia, prioridad);
				materialesInstitucionales.add(n);

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
		return materialesInstitucionales;
	}
	
	public void eliminar(String tutilo) {
		Connection c = null;
		CategoriaDAO ca = new CategoriaDAO();

		try {
			c = conectar();
			String sql = "DELETE FROM `mydb`.`material` WHERE titulo = ?;";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, tutilo);
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
}
