package org.pmorais.java.java_jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBaseDados {

	private static String url = "jdbc:mysql://localhost:3306/java_curso";
	private static String username = "root";
	private static String password = "phy2k576";
	private static Connection conn;
	
	public static Connection getInstance() throws SQLException {
		if(conn == null) {
			conn = DriverManager.getConnection(url, username, password);
		}
		
		return conn;
	}
}
