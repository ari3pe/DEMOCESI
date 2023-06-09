package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JList;

import java.time.*;

import modelo.Propuesta;

public class PropuestaDAO extends Conexion {

	public boolean agregar(Propuesta p, String textConversionCategoria) {

		int filasAfectadas = 0;
		Connection c = null;

		try {
			c = conectar();
			CategoriaDAO ca = new CategoriaDAO();
			String sql = "INSERT INTO propuesta(`Titulo`, `categorias_idcategorias`,`descripcion`, `origen`,`autor`,`motivacion`,`fecha`,`estado`,`motivo_rechazo`) VALUES (?,?,?,?,?,?,?,?,?);";
			PreparedStatement pst = c.prepareStatement(sql);
			Date fecha = Date.valueOf(p.getFecha());
			pst.setString(1, p.getTitulo());
			pst.setInt(2, ca.deCategoriaToIdCategoria(textConversionCategoria));
			pst.setString(3, p.getDescripcion());
			pst.setString(4, p.getOrigen());
			pst.setString(5, p.getAutor());
			pst.setString(6, p.getMotivacion());
			pst.setDate(7, fecha);
			pst.setString(8, p.getEstado());
			pst.setString(9, p.getMotivo_rechazo());
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

	public boolean modificar(String TituloPropuesta, Propuesta p, String Conversioncategoria) {

		int filasAfectadas = 0;
		Connection c = null;
		try {
			c = conectar();
			CategoriaDAO cat = new CategoriaDAO();
			String sql = "UPDATE `mydb`.`propuesta` SET `titulo` = ?, `estado` = ?, `autor` = ?, `descripcion` = ?, `motivo_rechazo` = ?, `origen` = ?, `motivacion` = ? , `categorias_idcategorias` = ? WHERE `titulo` = ? ;";
			PreparedStatement pst = c.prepareStatement(sql);
			
			pst.setString(1, p.getTitulo());
			pst.setString(2, p.getEstado());
			pst.setString(3, p.getAutor());
			pst.setString(4, p.getDescripcion());
			pst.setString(5, p.getMotivo_rechazo());
			pst.setString(6, p.getOrigen());
			pst.setString(7, p.getMotivacion());
			pst.setInt(8, cat.deCategoriaToIdCategoria(Conversioncategoria));
			pst.setString(9, TituloPropuesta);

			filasAfectadas = pst.executeUpdate();
		}

		catch (SQLException ex) {
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

	public boolean eliminar(String tituloPropuesta) {

		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = conectar();
			String sql = "DELETE FROM `mydb`.`propuesta` WHERE titulo = ?  ;";
			PreparedStatement pst = c.prepareStatement(sql);

			pst.setString(1, tituloPropuesta);
			pst.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close(); // se Cierra luego de eliminar la fila
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return filasAfectadas != 0;

	}

	public ArrayList<Propuesta> traerTodas() {
		// consultaSinParametros = SELECT *;o
		Connection c = null;
		ArrayList<Propuesta> propuestas = new ArrayList<Propuesta>();
		try {
			c = conectar();
			String sql = "SELECT `titulo`,`estado`,`autor`,`descripcion`,`motivo_rechazo`,`origen`,`motivacion`,`fecha`,`categorias_idcategorias`FROM `mydb`.propuesta ;";
			Statement st = c.createStatement();

			ResultSet rs = st.executeQuery(sql);
			CategoriaDAO ca = new CategoriaDAO();
			while (rs.next()) {

				String titulo = rs.getString("titulo");
				String estado = rs.getString("estado");
				String autor = rs.getString("autor");
				String descripcion = rs.getString("descripcion");
				String motivo_rechazo = rs.getString("motivo_rechazo");
				String origen = rs.getString("origen");
				String motivacion = rs.getString("motivacion");
				LocalDate fecha = rs.getDate("fecha").toLocalDate();
				String categoria = ca.deIDcategoriasToNombreCategoria(rs.getInt("categorias_idcategorias"));

				Propuesta p = new Propuesta(titulo, categoria, autor, descripcion, motivacion, fecha, origen, estado,
						motivo_rechazo);
				propuestas.add(p);
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
		return propuestas;
	}

	public ArrayList<String> traerfechas() {

		Connection c = null;
		ArrayList<String> fechas = new ArrayList<String>();
		try {
			c = conectar();
			String sql = "SELECT fecha FROM mydb.propuesta group by fecha ;";
			Statement st = c.createStatement();

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) { // Conversion
				String fecha = rs.getDate("fecha").toString();
				fechas.add(fecha);
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
		return fechas;

	}

	public ArrayList<Propuesta> traerfiltradas(String fecha1, String fecha2, String estadof, String categoriaf) {
		// consultaSinParametros: SELECT *;
		Connection c = null;
		ArrayList<Propuesta> propuestas = new ArrayList<Propuesta>();
		try {
			c = conectar();
			String sql = "SELECT `titulo`,`estado`,`autor`,`descripcion`,`motivo_rechazo`,`origen`,`motivacion`,`fecha`,`categorias_idcategorias`FROM `mydb`.propuesta  "
					+ Armadofiltroconsulta(fecha1, fecha2, estadof, categoriaf) + ";";
			Statement st = c.createStatement();

			ResultSet rs = st.executeQuery(sql);
			CategoriaDAO ca = new CategoriaDAO();
			while (rs.next()) {

				String titulo = rs.getString("titulo");
				String estado = rs.getString("estado");
				String autor = rs.getString("autor");
				String descripcion = rs.getString("descripcion");
				String motivo_rechazo = rs.getString("motivo_rechazo");
				String origen = rs.getString("origen");
				String motivacion = rs.getString("motivacion");
				LocalDate fecha = rs.getDate("fecha").toLocalDate();
				String categoria = ca.deIDcategoriasToNombreCategoria(rs.getInt("categorias_idcategorias"));

				Propuesta p = new Propuesta(titulo, categoria, autor, descripcion, motivacion, fecha, origen, estado,
						motivo_rechazo);
				propuestas.add(p);
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
		return propuestas;
	}

	private boolean hayFecha(String fecha1, String fecha2) {
		return fecha1 != "todo" && fecha2 != "todo";
		// si las opciones de fechas son "todo" significa que el usuario no filtr�.
	}

	private boolean hayEstado(String estadoFiltro) {
		return estadoFiltro != "todo";
	}

	private boolean hayCategoria(String categoriaFiltro) {
		return categoriaFiltro != "todo";
	}

	private String Armadofiltroconsulta(String fecha1, String fecha2, String estadof, String categoriaf) {
		String con = "";
		CategoriaDAO ca = new CategoriaDAO();
// armado de la consulta con las distintas posibilidades
		if (hayCategoria(categoriaf) && hayEstado(estadof) && hayFecha(fecha1, fecha2)) {
			con = "where fecha between '" + fecha1 + "' and ' " + fecha2 + " ' and estado = '" + estadof
					+ "' and categorias_idcategorias =" + ca.deCategoriaToIdCategoria(categoriaf);

		} else if (hayCategoria(categoriaf) && hayEstado(estadof) && !hayFecha(fecha1, fecha2)) {
			con = "where estado = '" + estadof + "' and categorias_idcategorias =  "
					+ ca.deCategoriaToIdCategoria(categoriaf);

		} else if (!hayCategoria(categoriaf) && hayEstado(estadof) && hayFecha(fecha1, fecha2)) {
			con = "where fecha between ' " + fecha1 + " ' and ' " + fecha2 + " ' and estado = '" + estadof + "' ";

		} else if (hayCategoria(categoriaf) && !hayEstado(estadof) && hayFecha(fecha1, fecha2)) {
			con = "where fecha between '" + fecha1 + "' and '" + fecha2 + "' and categorias_idcategorias = "
					+ ca.deCategoriaToIdCategoria(categoriaf);

		} else if (!hayCategoria(categoriaf) && !hayEstado(estadof) && hayFecha(fecha1, fecha2)) {
			con = "where fecha between '" + fecha1 + "' and '" + fecha2 + "'";

		} else if (!hayCategoria(categoriaf) && hayEstado(estadof) && !hayFecha(fecha1, fecha2)) {
			con = "where estado = '" + estadof + "' ";

		} else if (hayCategoria(categoriaf) && !hayEstado(estadof) && !hayFecha(fecha1, fecha2)) {
			con = "where categorias_idcategorias =" + ca.deCategoriaToIdCategoria(categoriaf) + " ";

		}

		return con;
	}


	public Propuesta traerdetalle(String titulos) {
		
		Propuesta detallada = new Propuesta();
		Connection c = null;
		try {
			c = conectar();
			String sql = "SELECT `titulo`,`estado`,`autor`,`descripcion`,`motivo_rechazo`,`origen`,`motivacion`,`fecha`,`categorias_idcategorias`FROM `mydb`.propuesta where titulo = ?  ;";
			PreparedStatement st = c.prepareStatement(sql);
			st.setString(1, titulos);

			ResultSet rs = st.executeQuery(sql);
			CategoriaDAO ca = new CategoriaDAO();

				String titulo = rs.getString("titulo");
				String estado = rs.getString("estado");
				String autor = rs.getString("autor");
				String descripcion = rs.getString("descripcion");
				String motivo_rechazo = rs.getString("motivo_rechazo");
				String origen = rs.getString("origen");
				String motivacion = rs.getString("motivacion");
				LocalDate fecha = rs.getDate("fecha").toLocalDate();
				String categoria = ca.deIDcategoriasToNombreCategoria(rs.getInt("categorias_idcategorias"));

				 detallada = new Propuesta(titulo, categoria, autor, descripcion, motivacion, fecha, origen, estado,
						motivo_rechazo);
			
			
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
		
		return detallada;
	}
	
	public ArrayList<String> verDetalleDeLaPropuestaDelMaterial(ArrayList<String> losids){
		
		Connection c = null;
		ArrayList<String> titulosDePropuestas = new ArrayList<String>();
		try {
			c = conectar();
			for (String idp : losids ) {
				
				String sql = "SELECT `titulo` FROM `mydb`.propuesta  where idpropuesta  = ?;";
				PreparedStatement st = c.prepareStatement(sql);
				st.setString(1, idp);
				ResultSet rs = st.executeQuery(sql);
					titulosDePropuestas.add(rs.getString("titulo"));
				
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
		return titulosDePropuestas;
		
	}
	
}