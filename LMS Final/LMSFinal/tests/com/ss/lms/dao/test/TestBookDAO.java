package com.ss.lms.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.ss.lms.dao.BookDAO;
import com.ss.lms.entity.Book;

public class TestBookDAO {
	@Test
	public void testGetAuthors() throws SQLException, ClassNotFoundException {
		ArrayList<Book> test = new ArrayList<Book>();
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		//Object for test and also test of constructor
		BookDAO testDAO = new BookDAO(conn);
		//Test to make sure the connection works
		//At the same time we are testing extractData as this method calls a method that returns extractData
		assertNotEquals(test,testDAO.getBooks());
	}
	@Test
	public void testGetBook() throws SQLException, ClassNotFoundException{
		int testId = 1;
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		BookDAO testDAO = new BookDAO(conn);
		//Test getting a book from the database
		assertEquals(1,testDAO.getBook(testId).getId());
	}
	@Test
	public void testCreateUpdateDelete() throws SQLException, ClassNotFoundException {
		Book test = new Book(1500,"TestBook",1);
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		BookDAO testDAO = new BookDAO(conn);
		//Test create
		testDAO.create(test);
		//We've tested getAuthor so we'll use it to show that we can now get the book we put in
		assertEquals(test.getId(),testDAO.getBook(test.getId()).getId());
		//Now we'll test updating the book we just made
		test.setTitle("TestBookUpdate");
		testDAO.update(test);
		assertEquals("TestBookUpdate",testDAO.getBook(test.getId()).getTitle());
		//Now we'll test deleting the book we just made
		testDAO.delete(test.getId());
		//We have to catch the exception we get because our database return an empty array and we try to access the first element, which doesn't exist
		try {
			testDAO.getBook(test.getId());
		}
		catch(IndexOutOfBoundsException e){
			assertTrue(true);
		}
	}
}
