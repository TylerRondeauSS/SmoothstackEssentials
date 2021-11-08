package com.ss.lms.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.ss.lms.entity.BookGenres;

public class TestBookGenres {
	@Test
	public void testBookGenresAccess() {
		//Make a BookGenres with values to change
		BookGenres inst = new BookGenres(1,1);
		//Test to make sure constructor works and getters work
		assertEquals(inst.getBookId(),1);
		assertEquals(inst.getGenreId(),1);
		assertNotEquals(inst.getBookId(),2);
		assertNotEquals(inst.getGenreId(),2);
		//Test setBookId
		inst.setBookId(2);
		assertEquals(inst.getBookId(),2);
		assertNotEquals(inst.getBookId(),1);
		//Test setGenreId
		inst.setGenreId(2);
		assertEquals(inst.getGenreId(),2);
		assertNotEquals(inst.getGenreId(),1);
	}
}
