package com.ss.lms.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.ss.lms.entity.BookLoans;

public class TestBookLoans {
	@Test
	public void testBookLoansAccess() {
		//Create localDate's for testing
		LocalDate out = LocalDate.now();
		LocalDate due = LocalDate.now().plusWeeks(1);
		LocalDate newOut = LocalDate.now().plusDays(3);
		LocalDate newDue = LocalDate.now().plusDays(10);
		//make a bookLoans to test constructor
		BookLoans inst = new BookLoans(1,1,1,out,due);
		//test constructor and getters
		assertEquals(inst.getBookId(),1);
		assertEquals(inst.getBranchId(),1);
		assertEquals(inst.getCardNo(),1);
		assertEquals(inst.getDateOut(),out);
		assertEquals(inst.getDateDue(),due);
		assertNotEquals(inst.getBookId(),2);
		assertNotEquals(inst.getBranchId(),2);
		assertNotEquals(inst.getCardNo(),2);
		assertNotEquals(inst.getDateOut(),newOut);
		assertNotEquals(inst.getDateDue(),newDue);
		//test setBookId
		inst.setBookId(2);
		assertEquals(inst.getBookId(),2);
		assertNotEquals(inst.getBookId(),1);
		//Test setBranchId
		inst.setBranchId(2);
		assertEquals(inst.getBranchId(),2);
		assertNotEquals(inst.getBranchId(),1);
		//Test setCardNo
		inst.setCardNo(2);
		assertEquals(inst.getCardNo(),2);
		assertNotEquals(inst.getCardNo(),1);
		//Test setDateOut
		inst.setDateOut(newOut);
		assertEquals(inst.getDateOut(),newOut);
		assertNotEquals(inst.getDateOut(),out);
		//Test setDateDue
		inst.setDateDue(newDue);
		assertEquals(inst.getDateDue(),newDue);
		assertNotEquals(inst.getDateDue(),due);
	}
}
