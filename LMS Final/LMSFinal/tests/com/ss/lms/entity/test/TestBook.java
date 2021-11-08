package com.ss.lms.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.ss.lms.entity.Book;

public class TestBook {
	@Test
	public void testBookAccess() {
		//Make a book with values to change
		Book inst = new Book(1,"notTest",1);
		//test constructor / getter
		assertEquals(inst.getId(),1);
		assertEquals(inst.getTitle(),"notTest");
		assertEquals(inst.getPublishId(),1);
		assertNotEquals(inst.getId(),2);
		assertNotEquals(inst.getTitle(),"Test");
		assertNotEquals(inst.getPublishId(),2);
		//Test setId
		inst.setId(2);
		assertEquals(inst.getId(),2);
		assertNotEquals(inst.getId(),1);
		//Test setTitle
		inst.setTitle("Test");
		assertEquals(inst.getTitle(),"Test");
		assertNotEquals(inst.getTitle(),"notTest");
		//Test setPubId
		inst.setPubId(2);
		assertEquals(inst.getPublishId(),2);
		assertNotEquals(inst.getPublishId(),1);
	}
}
