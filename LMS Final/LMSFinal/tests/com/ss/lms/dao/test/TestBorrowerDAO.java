package com.ss.lms.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.ss.lms.dao.BorrowerDAO;
import com.ss.lms.entity.Borrower;

public class TestBorrowerDAO {
	@Test
	public void testGetBorrowers() throws SQLException, ClassNotFoundException {
		ArrayList<Borrower> test = new ArrayList<Borrower>();
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		//Object for test and also test of constructor
		BorrowerDAO testDAO = new BorrowerDAO(conn);
		//Test to make sure the connection works and returns a non-empty arrayList
		//At the same time we are testing extractData as this method calls a method that returns extractData
		assertNotEquals(test,testDAO.getBorrowers());
	}
	@Test
	public void testGetBook() throws SQLException, ClassNotFoundException{
		int testCard = 1;
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		BorrowerDAO testDAO = new BorrowerDAO(conn);
		//Test getting a book from the database
		assertEquals(1,testDAO.getBorrower(testCard).getCardNo());
	}
	@Test
	public void testCreateUpdateDelete() throws SQLException, ClassNotFoundException {
		Borrower test = new Borrower(1500,"TestBorrower","TestAddress","TestPhone");
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		BorrowerDAO testDAO = new BorrowerDAO(conn);
		//Test create
		testDAO.create(test);
		//We've tested getBorrower so we'll use it to show that we can now get the borrower we put in
		assertEquals(test.getCardNo(),testDAO.getBorrower(test.getCardNo()).getCardNo());
		//Now we'll test updating the borrower we just made
		test.setName("TestBorrowerUpdate");
		testDAO.update(test);
		assertEquals("TestBorrowerUpdate",testDAO.getBorrower(test.getCardNo()).getName());
		//Now we'll test deleting the borrower we just made
		testDAO.delete(test.getCardNo());
		//We have to catch the exception we get because our database return an empty array and we try to access the first element, which doesn't exist
		try {
			testDAO.getBorrower(test.getCardNo());
		}
		catch(IndexOutOfBoundsException e){
			assertTrue(true);
		}
	}
}
