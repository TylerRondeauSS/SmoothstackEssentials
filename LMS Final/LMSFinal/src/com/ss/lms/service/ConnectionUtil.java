//@author: TylerRondeau
//Provides connection to all Service files
package com.ss.lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	protected Connection getConnection() throws ClassNotFoundException, SQLException{
		//Register Driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//Create Connection and return it
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		conn.setAutoCommit(false);
		return conn;
	}
}
