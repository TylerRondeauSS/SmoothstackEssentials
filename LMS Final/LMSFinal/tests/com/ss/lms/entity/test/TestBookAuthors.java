package com.ss.lms.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.ss.lms.entity.BookAuthors;

public class TestBookAuthors {
	@Test
	public void testBookAuthorsAccess() {
		//Make a bookAuthors with values to change
		BookAuthors inst = new BookAuthors(1,1);
		//Test to make sure constructor works and getters work
		assertEquals(inst.getBookId(),1);
		assertEquals(inst.getAuthId(),1);
		assertNotEquals(inst.getBookId(),2);
		assertNotEquals(inst.getAuthId(),2);
		//Test setBookId
		inst.setBookId(2);
		assertEquals(inst.getBookId(),2);
		assertNotEquals(inst.getBookId(),1);
		//Test setAuthId
		inst.setAuthId(2);
		assertEquals(inst.getAuthId(),2);
		assertNotEquals(inst.getAuthId(),1);
	}
}
