package com.ss.lms.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.ss.lms.dao.GenreDAO;
import com.ss.lms.entity.Genre;

public class TestGenreDAO {
	@Test
	public void testGetGenres() throws SQLException, ClassNotFoundException {
		ArrayList<Genre> test = new ArrayList<Genre>();
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		//Object for test and also test of constructor
		GenreDAO testDAO = new GenreDAO(conn);
		//Test to make sure the connection works and we get a non-empty ArrayList
		//At the same time we are testing extractData as this method calls a method that returns extractData
		assertNotEquals(test,testDAO.getGenres());
	}
	@Test
	public void testGetBook() throws SQLException, ClassNotFoundException{
		int testId = 1;
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		GenreDAO testDAO = new GenreDAO(conn);
		//Test getting a book from the database
		assertEquals(1,testDAO.getGenre(testId).getGenreId());
	}
	@Test
	public void testCreateUpdateDelete() throws SQLException, ClassNotFoundException {
		Genre test = new Genre(1500,"TestGenre");
		//Connection and DAO for test
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "library", "library");
		GenreDAO testDAO = new GenreDAO(conn);
		//Test create
		testDAO.create(test);
		//We've tested getAuthor so we'll use it to show that we can now get the Genre we put in
		assertEquals(test.getGenreId(),testDAO.getGenre(test.getGenreId()).getGenreId());
		//Now we'll test updating the Genre we just made
		test.setGenreName("TestGenreUpdate");
		testDAO.update(test);
		assertEquals("TestGenreUpdate",testDAO.getGenre(test.getGenreId()).getGenreName());
		//Now we'll test deleting the Genre we just made
		testDAO.delete(test.getGenreId());
		//We have to catch the exception we get because our database return an empty array and we try to access the first element, which doesn't exist
		try {
			testDAO.getGenre(test.getGenreId());
		}
		catch(IndexOutOfBoundsException e){
			assertTrue(true);
		}
	}
}
