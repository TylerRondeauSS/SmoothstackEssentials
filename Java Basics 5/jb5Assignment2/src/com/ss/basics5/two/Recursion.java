package com.ss.basics5.two;

public class Recursion {

	public static void main(String[] args) {
		/*Given an array of ints, is it possible to choose a group of some of the ints, such that the group sums to the given target, with this 
		additional constraint: if there are numbers in the array that are adjacent and the identical value, they must either all be chosen, or none 
		of them chosen. For example, with the array {1, 2, 2, 2, 5, 2}, either all three 2's in the middle must be chosen or not, all as a group. 
		(one loop can be used to find the extent of the identical values).*/
		Recursion run = new Recursion();
		int[] test = {2,4,8};
		int[] test2 = {1,2,4,8,1};
		int[] test3 = {2,4,4,8};
		System.out.println("The result of test 1 is : " + run.groupSumClump(0, test, 10));
		System.out.println("The result of test 2 is : " + run.groupSumClump(0, test2, 14));
		System.out.println("The result of test 3 is : " + run.groupSumClump(0, test3, 14));
	}
	public boolean groupSumClump(int start, int[] nums, int target) {
		//if start is higher than length, return true if the target is 0 otherwise false
	    if(start >= nums.length)
	        return target == 0;
	    
	    int i = start;
	    int sum = 0;
	    //add together identical values
	    while(i < nums.length && nums[start] == nums[i]) {
	        sum += nums[i];
	        i++;
	    }
	    //if we can hit the target from the new position of i - what we already summed, return true                         
	    if(groupSumClump(i, nums, target - sum))
	        return true;
	    //if we can hit the target from the new position of i, return true
	    if(groupSumClump(i, nums, target))
	        return true;
	    //if not, it can't be done                                              
	    return false;
	}
}
