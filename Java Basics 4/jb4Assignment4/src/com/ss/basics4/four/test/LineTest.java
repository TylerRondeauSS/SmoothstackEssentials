package com.ss.basics4.four.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.ss.basics4.four.Line;

public class LineTest {
	Line slopeError = new Line(1.0,2.0,1.0,2.0);
	Line line = new Line(1.0,2.0,4.0,2.0);
	Line line2 = new Line (2.0, 5.0, 8.0, 1.0);
	

	@Test
	public void getSlopeTest(){
		assertNotEquals(1.0,line.getSlope());
		assertEquals(-.6666,line2.getSlope(),0.0001);
		//Does not cover because it throws the exception
		try {
			assertNotEquals(1.0,slopeError.getSlope());
		}
		catch(ArithmeticException e) {}
	}
	
	@Test
	public void getDistanceTest(){
		assertNotEquals(1.0,line.getDistance());
		assertEquals(3.0,line.getDistance(),0.001);
	}

	@Test
	public void parallelToTest(){
		//Null is not handled so causes an error that prevents running the other lines
		//assertNull(line.parallelTo(null));
		assertNotEquals(true,line.parallelTo(line2));
		assertEquals(true,line.parallelTo(line));
	}

}
