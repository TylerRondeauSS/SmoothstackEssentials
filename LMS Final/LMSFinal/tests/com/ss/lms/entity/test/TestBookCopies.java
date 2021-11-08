package com.ss.lms.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.ss.lms.entity.BookCopies;

public class TestBookCopies {
	@Test
	public void testBookCopiesAccess() {
		//Make a bookCopies with values to change
		BookCopies inst = new BookCopies(1,1,1);
		//Test to make sure constructor works and getters work
		assertEquals(inst.getBookId(),1);
		assertEquals(inst.getBranchId(),1);
		assertEquals(inst.getCopies(),1);
		assertNotEquals(inst.getBookId(),2);
		assertNotEquals(inst.getBranchId(),2);
		assertNotEquals(inst.getCopies(),2);
		//Test setBookId
		inst.setBookId(2);
		assertEquals(inst.getBookId(),2);
		assertNotEquals(inst.getBookId(),1);
		//Test setBranchId
		inst.setBranchId(2);
		assertEquals(inst.getBranchId(),2);
		assertNotEquals(inst.getBranchId(),1);
		//Test setCopies
		inst.setCopies(2);
		assertEquals(inst.getCopies(),2);
		assertNotEquals(inst.getCopies(),1);
	}
}
