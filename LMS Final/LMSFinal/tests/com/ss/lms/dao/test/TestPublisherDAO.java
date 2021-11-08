package com.ss.lms.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.ss.lms.dao.PublisherDAO;
import com.ss.lms.entity.Publisher;

public class TestPublisherDAO {
	@Test
	public void testGetPublishers() throws SQLException, ClassNotFoundException {
		ArrayList<Publisher> test = new ArrayList<Publisher>();
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		//Object for test and also test of constructor
		PublisherDAO testDAO = new PublisherDAO(conn);
		//Test to make sure the connection works and we get a non-empty ArrayList
		//At the same time we are testing extractData as this method calls a method that returns extractData
		assertNotEquals(test,testDAO.getPublishers());
	}
	@Test
	public void testGetBook() throws SQLException, ClassNotFoundException{
		int testId = 1;
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		PublisherDAO testDAO = new PublisherDAO(conn);
		//Test getting a Publisher from the database
		assertEquals(1,testDAO.getPublisher(testId).getId());
	}
	@Test
	public void testCreateUpdateDelete() throws SQLException, ClassNotFoundException {
		Publisher test = new Publisher(1500,"TestPublisher","TestPublisherAddress","TestPublisherPhone");
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		PublisherDAO testDAO = new PublisherDAO(conn);
		//Test create
		testDAO.create(test);
		//We've tested getAuthor so we'll use it to show that we can now get the Publisher we put in
		assertEquals(test.getId(),testDAO.getPublisher(test.getId()).getId());
		//Now we'll test updating the Publisher we just made
		test.setName("TestPublisherUpdate");
		testDAO.update(test);
		assertEquals("TestPublisherUpdate",testDAO.getPublisher(test.getId()).getName());
		//Now we'll test deleting the Publisher we just made
		testDAO.delete(test.getId());
		//We have to catch the exception we get because our database return an empty array and we try to access the first element, which doesn't exist
		try {
			testDAO.getPublisher(test.getId());
		}
		catch(IndexOutOfBoundsException e){
			assertTrue(true);
		}
	}
}
