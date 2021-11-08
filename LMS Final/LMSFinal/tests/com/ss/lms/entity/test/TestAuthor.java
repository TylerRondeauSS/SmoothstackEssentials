package com.ss.lms.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.ss.lms.entity.Author;


public class TestAuthor {
	
	@Test
	public void testAuthorAccess() {
		//Make an author with values to change
		Author inst = new Author(1,"notTest");
		//Test to make sure constructor works while testing getter
		assertEquals(inst.getId(),1);
		assertEquals(inst.getName(),"notTest");
		assertNotEquals(inst.getId(),2);
		assertNotEquals(inst.getName(),"Test");
		//Test setId
		inst.setId(2);
		assertEquals(inst.getId(),2);
		assertNotEquals(inst.getId(),1);
		//Test setName
		inst.setName("Test");
		assertEquals(inst.getName(),"Test");
		assertNotEquals(inst.getName(),"notTest");
	}
}
