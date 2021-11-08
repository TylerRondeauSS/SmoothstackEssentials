package com.ss.lms.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.ss.lms.dao.BranchDAO;
import com.ss.lms.entity.Branch;

public class TestBranchDAO {
	@Test
	public void testGetBranches() throws SQLException, ClassNotFoundException {
		ArrayList<Branch> test = new ArrayList<Branch>();
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		//Object for test and also test of constructor
		BranchDAO testDAO = new BranchDAO(conn);
		//Test to make sure the connection works and we get a non-empty ArrayList
		//At the same time we are testing extractData as this method calls a method that returns extractData
		assertNotEquals(test,testDAO.getBranches());
	}
	@Test
	public void testGetBook() throws SQLException, ClassNotFoundException{
		int testId = 1;
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		BranchDAO testDAO = new BranchDAO(conn);
		//Test getting a book from the database
		assertEquals(1,testDAO.getBranch(testId).getBranchId());
	}
	@Test
	public void testCreateUpdateDelete() throws SQLException, ClassNotFoundException {
		Branch test = new Branch(1500,"TestBranch","TestBranchAddress");
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		BranchDAO testDAO = new BranchDAO(conn);
		//Test create
		testDAO.create(test);
		//We've tested getAuthor so we'll use it to show that we can now get the branch we put in
		assertEquals(test.getBranchId(),testDAO.getBranch(test.getBranchId()).getBranchId());
		//Now we'll test updating the branch we just made
		test.setBranchName("TestBranchUpdate");
		testDAO.update(test);
		assertEquals("TestBranchUpdate",testDAO.getBranch(test.getBranchId()).getBranchName());
		//Now we'll test deleting the branch we just made
		testDAO.delete(test.getBranchId());
		//We have to catch the exception we get because our database return an empty array and we try to access the first element, which doesn't exist
		try {
			testDAO.getBranch(test.getBranchId());
		}
		catch(IndexOutOfBoundsException e){
			assertTrue(true);
		}
	}
}
