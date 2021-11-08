package com.ss.lms.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.ss.lms.entity.Author;
import com.ss.lms.dao.AuthorDAO;

public class TestAuthorDAO {
	
	@Test
	public void testGetAuthors() throws SQLException, ClassNotFoundException {
		ArrayList<Author> test = new ArrayList<Author>();
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		//Object for test and also test of constructor
		AuthorDAO testDAO = new AuthorDAO(conn);
		//Test to make sure the connection works
		//At the same time we are testing extractData as this method calls a method that returns extractData
		assertNotEquals(test,testDAO.getAuthors());
	}
	@Test
	public void testGetAuthor() throws SQLException, ClassNotFoundException{
		int testId = 1;
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		AuthorDAO testDAO = new AuthorDAO(conn);
		//Test getting an author from the database
		assertEquals(1,testDAO.getAuthor(testId).getId());
	}
	@Test
	public void testCreateUpdateDelete() throws SQLException, ClassNotFoundException {
		Author test = new Author(1500,"TestAuthor");
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		AuthorDAO testDAO = new AuthorDAO(conn);
		//Test create
		testDAO.create(test);
		//We've tested getAuthor so we'll use it to show that we can now get the author we put in
		assertEquals(test.getId(),testDAO.getAuthor(test.getId()).getId());
		//Now we'll test updating the author we just made
		test.setName("TestAuthorUpdate");
		testDAO.update(test);
		assertEquals("TestAuthorUpdate",testDAO.getAuthor(test.getId()).getName());
		//Now we'll test deleting the author we just made
		testDAO.delete(test.getId());
		//We have to catch the exception we get because our database return an empty array and we try to access the first element, which doesn't exist
		try {
			testDAO.getAuthor(test.getId());
		}
		catch(IndexOutOfBoundsException e){
			assertTrue(true);
		}
	}
}
