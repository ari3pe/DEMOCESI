package dao;

import modelo.Propuesta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class CategoriaDAO extends Conexion {
	
	public int deCategoriaToIdCategoria(String categoria) {
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT idcategorias FROM categorias WHERE categoriascol = ?;";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, categoria);;
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				return rs.getInt("idcategorias");
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
		
		
		return 0; // ��??
	}
	
	public String deIDcategoriasToNombreCategoria(int NumCategoria) {
		String categorian = "";
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT categoriascol FROM categorias WHERE idcategorias  = ?;";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, NumCategoria);;
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				return rs.getString("categoriascol") ;
				
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
		
		return categorian; // = ?????????????????????
	}
	
	public ArrayList<String> TraerLasCategorias(){
		ArrayList<String> categorias = new ArrayList<String>();
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT categoriascol FROM categorias ;";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				categorias.add(rs.getString("categoriascol"));
				
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
		
		return categorias;
		
	}
	
	
	}

