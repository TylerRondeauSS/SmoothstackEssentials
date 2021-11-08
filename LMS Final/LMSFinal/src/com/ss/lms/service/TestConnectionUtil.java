package com.ss.lms.service;

import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class TestConnectionUtil extends ConnectionUtil{
	ConnectionUtil cUtil = new ConnectionUtil();
	@Test
	public void testGetConnection() throws ClassNotFoundException, SQLException {
		//Create the connection we expect to get
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		//test that we get it, notEquals because they are two different connection objects and won't be equal
		assertNotEquals(conn,cUtil.getConnection());
	}
}
