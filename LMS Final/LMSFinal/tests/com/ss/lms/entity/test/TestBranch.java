package com.ss.lms.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.ss.lms.entity.Branch;

public class TestBranch {
	@Test
	public void testBranchAccess() {
		//Create branch to test constructor
		Branch inst = new Branch(1,"notTest","notTest");
		//Test constructor and getters
		assertEquals(inst.getBranchId(),1);
		assertEquals(inst.getBranchName(),"notTest");
		assertEquals(inst.getBranchAddress(),"notTest");
		assertNotEquals(inst.getBranchId(),2);
		assertNotEquals(inst.getBranchName(),"Test");
		assertNotEquals(inst.getBranchAddress(),"Test");
		//test setId
		inst.setBranchId(2);
		assertEquals(inst.getBranchId(),2);
		assertNotEquals(inst.getBranchId(),1);
		//Test setBranchName
		inst.setBranchName("Test");
		assertEquals(inst.getBranchName(),"Test");
		assertNotEquals(inst.getBranchName(),"notTest");
		//Test setBranchAddress
		inst.setBranchAddress("Test");
		assertEquals(inst.getBranchAddress(),"Test");
		assertNotEquals(inst.getBranchAddress(),"notTest");
	}
}
