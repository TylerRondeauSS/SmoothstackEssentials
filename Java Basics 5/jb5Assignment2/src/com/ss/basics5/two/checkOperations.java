package com.ss.basics5.two;

interface checkOperations {
	boolean check(int a);
}
class Operations{
	public static boolean checker(checkOperations c, int num) {
		return c.check(num);
	}
	
	public checkOperations isOdd() {
		//if it can be modulus by 2 it's not odd!
		return (num) -> (num % 2 != 0);
	}
	
	public checkOperations isPrime() {
		return (num) -> {
			for(int i = 2; i <= num / 2; i++) {
				if(num % i == 0) {
					return false;
				}
			}
			return true;
		};
	}
	
	public checkOperations isPalindrome() {
		//To check for a palindrome we turn the number into a string and compare it to another string that is itself reversed
		return (num) -> {
			String str = num + "";
			String newStr = new StringBuffer(str).reverse().toString();
			return str.equals(newStr);
		};
	}
}
