package com.ss.basics5.two;

import java.io.BufferedReader;
import java.io.FileReader;

//@Author TylerRondeau
public class Lambdas {
	public static void main(String[] args) throws Exception{
		Operations run = new Operations();
		final String testPath = "D:\\Downloads\\Personal\\Smoothstack\\SmoothstackEssentials\\Java Basics 5\\jb5Assignment2\\testFile.txt";
		BufferedReader read = new BufferedReader(new FileReader(testPath));
		int tests = Integer.parseInt(read.readLine());
		checkOperations co;
		boolean result = false;
		String answer = null;
		while(tests-- > 0) {
			String s = read.readLine();
			int check = Integer.parseInt(s.substring(0,1));
			int num = Integer.parseInt(s.substring(2));
			if(check == 1) {
				co = run.isOdd(); 
				result = run.checker(co, num); 
				answer = (result) ? "Odd" : "Even";
			}
			else if (check == 2) {
				co = run.isPrime(); 
				result = run.checker(co, num); 
				answer = (result) ? "Prime" : "Composite";
			}
			else if(check == 3) {
				co = run.isPalindrome(); 
				result = run.checker(co, num); 
				answer = (result) ? "Palindrome" : "Not a Palindrome";
			}
			System.out.println(answer);
		}
	}
}
