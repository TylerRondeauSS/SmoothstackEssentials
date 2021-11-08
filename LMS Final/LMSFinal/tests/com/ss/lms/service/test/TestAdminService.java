package com.ss.lms.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.ss.lms.entity.BookLoans;
import com.ss.lms.service.AdminService;

public class TestAdminService {
	AdminService admTest = new AdminService();
	@Test
	public void testGetLoan() {
		//make a test loan for a loan we know we can get
		BookLoans test = new BookLoans(60,1,501,LocalDate.now(),LocalDate.now().plusWeeks(1));
		//get the loan from getLoan
		BookLoans testCompare = admTest.getLoan(60, 1, 501);
		//test that each value is the same, and not something else
		//BookId
		assertEquals(test.getBookId(),testCompare.getBookId());
		assertNotEquals(5,testCompare.getBookId());
		//BranchId
		assertEquals(test.getBranchId(),testCompare.getBranchId());
		assertNotEquals(5,testCompare.getBranchId());
		//cardNo
		assertEquals(test.getCardNo(),testCompare.getCardNo());
		assertNotEquals(5,testCompare.getCardNo());
		//dateOut
		assertEquals(test.getDateDue(),testCompare.getDateDue());
		assertNotEquals(test.getDateDue().plusDays(1),testCompare.getDateDue());
		//dateDue
		assertEquals(test.getDateOut(),testCompare.getDateOut());
		assertNotEquals(test.getDateOut().plusDays(1),testCompare.getDateOut());
	}
}
