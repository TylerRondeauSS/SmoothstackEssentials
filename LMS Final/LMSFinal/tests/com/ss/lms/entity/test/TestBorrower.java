package com.ss.lms.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.ss.lms.entity.Borrower;

public class TestBorrower {
	@Test
	public void testBorrowerAccess() {
		//Test constructor
		Borrower inst = new Borrower(1,"nameTest","addressTest","phoneTest");
		//Test to make sure constructor works while testing getter
				assertEquals(inst.getCardNo(),1);
				assertEquals(inst.getName(),"nameTest");
				assertEquals(inst.getAddress(),"addressTest");
				assertEquals(inst.getPhone(),"phoneTest");
				assertNotEquals(inst.getCardNo(),2);
				assertNotEquals(inst.getName(),"nameTest2");
				assertNotEquals(inst.getAddress(),"addressTest2");
				assertNotEquals(inst.getPhone(),"phoneTest2");
				//Test setCardNo
				inst.setCardNo(2);
				assertEquals(inst.getCardNo(),2);
				assertNotEquals(inst.getCardNo(),1);
				//Test setName
				inst.setName("nameTest2");
				assertEquals(inst.getName(),"nameTest2");
				assertNotEquals(inst.getName(),"nameTest");
				//Test setAddress
				inst.setAddress("addressTest2");
				assertEquals(inst.getAddress(),"addressTest2");
				assertNotEquals(inst.getAddress(),"addressTest");
				//Test setPhone
				inst.setPhone("phoneTest2");
				assertEquals(inst.getPhone(),"phoneTest2");
				assertNotEquals(inst.getPhone(),"phoneTest");
	}
}
