package com.nikhil.testengine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public interface CommonDAO {
	
	public static Connection setupConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		ResourceBundle rb= ResourceBundle.getBundle("config");
		String databaseUrl=rb.getString("url");
		System.out.println(databaseUrl);
		String userid= rb.getString("userid");
		String password=rb.getString("password");
		Connection connection = DriverManager.getConnection(databaseUrl, userid, password);
		return connection;
		
	}

}

