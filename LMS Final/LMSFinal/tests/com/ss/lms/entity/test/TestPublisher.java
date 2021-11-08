package com.ss.lms.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.ss.lms.entity.Publisher;

public class TestPublisher {
	@Test
	public void testPublisherAccess() {
		//Make a publisher with values to change
		Publisher inst = new Publisher(1,"nameTest","addressTest","phoneTest");
		//Test to make sure constructor works while testing getter
		assertEquals(inst.getId(),1);
		assertEquals(inst.getName(),"nameTest");
		assertEquals(inst.getAddress(),"addressTest");
		assertEquals(inst.getPhoneNumber(),"phoneTest");
		assertNotEquals(inst.getId(),2);
		assertNotEquals(inst.getName(),"nameTest2");
		assertNotEquals(inst.getAddress(),"addressTest2");
		assertNotEquals(inst.getPhoneNumber(),"phoneTest2");
		//Test setId
		inst.setId(2);
		assertEquals(inst.getId(),2);
		assertNotEquals(inst.getId(),1);
		//Test setName
		inst.setName("nameTest2");
		assertEquals(inst.getName(),"nameTest2");
		assertNotEquals(inst.getName(),"nameTest");
		//Test setAddress
		inst.setAddress("addressTest2");
		assertEquals(inst.getAddress(),"addressTest2");
		assertNotEquals(inst.getAddress(),"addressTest");
		//Test setPhoneNumber
		inst.setPhoneNumber("phoneTest2");
		assertEquals(inst.getPhoneNumber(),"phoneTest2");
		assertNotEquals(inst.getPhoneNumber(),"phoneTest");
	}
}
