package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	public Connection conectar() {

		String url = "jdbc:mysql://localhost:3306/mydb";
		String usr = "root";
		String pass = "admin";

		Connection c = null;

		try {
			c = DriverManager.getConnection(url, usr, pass);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return c;

	}
	 
	
	
	
	

}
