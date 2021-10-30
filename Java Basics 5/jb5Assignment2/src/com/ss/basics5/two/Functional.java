package com.ss.basics5.two;

import java.util.Arrays;
import java.util.List;

//@Author TylerRondeau
public class Functional {
	public static void main(String[] args) {
		Functional run = new Functional();
		//Given a list of non-negative integers, return an integer list of the rightmost digits. (Note: use %)
		List<Integer> test = Arrays.asList(1,22,93);
		List<Integer> test2 = Arrays.asList(16,8,886,8,1);
		List<Integer> test3 = Arrays.asList(10,0);
		test = run.rightDigit(test);
		test2 = run.rightDigit(test2);
		test3 = run.rightDigit(test3);
		System.out.println("The result for test 1 is : "+ test.toString());
		System.out.println("The result for test 2 is : "+ test2.toString());
		System.out.println("The result for test 3 is : "+ test3.toString());
		
		//Given a list of integers, return a list where each integer is multiplied by 2.
		test2.set(4, -1);
		test = run.doubling(test);
		test2 = run.doubling(test2);
		System.out.println("The result for test 1 is : "+ test.toString());
		System.out.println("The result for test 2 is : "+ test2.toString());
		
		//Given a list of strings, return a list where each string has all its "x" removed.
		List<String> testS = Arrays.asList("ax","bb","cx");
		List<String> testS2 = Arrays.asList("xxax","xbxbx","xxcx");
		List<String> testS3 = Arrays.asList("x");
		testS = run.noX(testS);
		testS2 = run.noX(testS2);
		testS3 = run.noX(testS3);
		System.out.println("The result for test 1 is : "+ testS.toString());
		System.out.println("The result for test 2 is : "+ testS2.toString());
		System.out.println("The result for test 3 is : "+ testS3.toString());
	}
	
	public List<Integer> rightDigit(List<Integer> ints){
		ints.replaceAll(n-> n%10);
		return ints;
	}
	
	public List<Integer> doubling(List<Integer> ints){
		ints.replaceAll(n-> n*2);
		return ints;
	}
	
	public List<String> noX(List<String> strs){
		strs.replaceAll(n -> n.replace("x", ""));
		return strs;
	}
}
