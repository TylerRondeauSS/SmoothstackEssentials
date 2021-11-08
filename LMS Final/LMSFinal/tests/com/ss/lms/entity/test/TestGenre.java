package com.ss.lms.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.ss.lms.entity.Genre;

public class TestGenre {
	@Test
	public void testGenreAccess() {
		//Make a genre with values to change
		Genre inst = new Genre(1,"notTest");
		//Test to make sure constructor works while testing getter
		assertEquals(inst.getGenreId(),1);
		assertEquals(inst.getGenreName(),"notTest");
		assertNotEquals(inst.getGenreId(),2);
		assertNotEquals(inst.getGenreName(),"Test");
		//Test setGenreId
		inst.setGenreId(2);
		assertEquals(inst.getGenreId(),2);
		assertNotEquals(inst.getGenreId(),1);
		//Test setGenreName
		inst.setGenreName("Test");
		assertEquals(inst.getGenreName(),"Test");
		assertNotEquals(inst.getGenreName(),"notTest");
	}
}
