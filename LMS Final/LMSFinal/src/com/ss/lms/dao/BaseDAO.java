// @author TylerRondeau
// Base Data Access Object that all other DAO's must extend. 
package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseDAO<T>{
	//connection object for contacting the server
	protected static Connection conn = null;
	
	//BaseDAO constructor which every class that extends this must have
	public BaseDAO(Connection conni) {
		conn = conni;
	}
	//Method to execute any preparedStatement that doesn't need a return
	protected void exe(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
		PreparedStatement stmt = conn.prepareStatement(sql);
			
		if(vals!=null) {
			int count = 1;
			for(Object o: vals) {
				stmt.setObject(count, o);
				count++;
			}
		}
		stmt.execute();
	}
	//Method to get data from server and return as an ArrayList of unknown type
	protected ArrayList<T> read(String sql, Object[] vals) throws SQLException, ClassNotFoundException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		if(vals!=null) {
			int count = 1;
			for(Object o: vals) {
				stmt.setObject(count, o);
				count++;
			}
		}
		ResultSet rs = stmt.executeQuery();
		return extractData(rs);
	}
	//Method that must be overridden by anything that extends this so they can turn the ResultSet into their data type
	abstract protected ArrayList<T> extractData(ResultSet rs) throws SQLException;
}