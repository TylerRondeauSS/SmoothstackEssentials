//@author: TylerRondeau
//This class is full of utility functions. These are functions that we have to call multiple times
package com.ss.lms.view;

import java.util.Scanner;

public class UtilityFunctions {
	Scanner scan = new Scanner(System.in);
	//Take number input from the user with a max and min value possible.
	public int getInput(int max, int min) {
		int input = 0;
		boolean valid = false;
		while(valid==false) {
			System.out.print("Enter input: ");
			while(scan.hasNextInt() == false) {
				//Tell the user the input is invalid and remove their input until it is a number unless it is exit in which case we exit. 
				exitOrMain(scan.nextLine());
				System.out.println("Invalid input, please try again\n");
			}
			input = scan.nextInt();
			//Check to make sure the number is valid, if not go to next iteration of loop
			if(input > max || input < min) {
				System.out.println("Input is invalid, please try again\n");
				continue;
			}
			valid = true;
		}
		return input;
	}
	//quick println without having to import System in every file
	public void println(String str) {
		System.out.println(str);
	}
	//quick print without having to import System in every file
	public void print(String str) {
		System.out.println(str);
	}
	//Check if the string is exit without case sensitivity, if so exit program.
	public void exitOrMain(String str) {
		if("exit".equalsIgnoreCase(str) == Boolean.TRUE){
			System.out.println("\nNow Exiting the SS Library Management System. Goodbye!");
			scan.close();
			System.exit(0);
		}
		if("main".equalsIgnoreCase(str) == Boolean.TRUE){
			System.out.println("\nReturning to main menu\n");
			LibraryMain.mainMenu();
		}
	}
	//Check if the string is quit and return true or false appropriately
	public boolean quit(String str) {
		if("quit".equalsIgnoreCase(str) == Boolean.TRUE){
			return true;
		}
		return false;
	}
	//Method to take input to confirm a choice, either y for yes or n for no
	public boolean confirmChoice(String s) {
		if("y".equalsIgnoreCase(s)) {return true;}
		return false;
	}
}