package com.ss.lms.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import com.ss.lms.dao.BookLoansDAO;
import com.ss.lms.entity.BookLoans;
import com.ss.lms.entity.Branch;

public class TestBookLoansDAO {
	@Test
	public void testGetBookLoans() throws SQLException, ClassNotFoundException {
		ArrayList<BookLoans> test = new ArrayList<BookLoans>();
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		//Object for test and also test of constructor
		BookLoansDAO testDAO = new BookLoansDAO(conn);
		//Test to make sure the connection works and we get a non-empty ArrayList
		//At the same time we are testing extractData as this method calls a method that returns extractData
		assertNotEquals(test,testDAO.getBookLoans());
	}
	@Test
	public void testGetBookLoan() throws SQLException, ClassNotFoundException{
		int testBkId = 60;
		int testBrId = 1;
		int testCardNo = 501;
		BookLoans test = new BookLoans(testBkId,testBrId,testCardNo,LocalDate.now(),LocalDate.now().plusWeeks(1));
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		BookLoansDAO testDAO = new BookLoansDAO(conn);
		//Test getting a book from the database
		assertEquals(test.getBookId(),testDAO.getBookLoan(testBkId,testBrId,testCardNo).getBookId());
	}
	@Test
	public void testCreateUpdateDelete() throws SQLException, ClassNotFoundException {
		BookLoans test = new BookLoans(1500,1500,1500,LocalDate.now(),LocalDate.now().plusWeeks(1));
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		BookLoansDAO testDAO = new BookLoansDAO(conn);
		//Test create
	}
}
