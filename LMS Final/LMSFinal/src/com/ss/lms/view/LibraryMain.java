//@author: TylerRondeau
//This is the beginning of the program that brings the user to where it specifically wants to go
package com.ss.lms.view;

public class LibraryMain {
	public static void main(String[] args) {
		System.out.println("Welcome to the SS Library Management System. You may type exit at any time to exit, or main at any time to return to main menu.");
		mainMenu();
	}
	//Brings the user to the main menu. This is a method so we can call it should the user come back to the main menu
	public static void mainMenu() {
		//Instantiate all of our views
		AdminView adm = new AdminView();
		LibrarianView lib = new LibrarianView();
		BorrowerView borr = new BorrowerView();
		UtilityFunctions util = new UtilityFunctions();
		//Ask the user what type of user they are
		util.println("Which category of a user are you?");
		util.println("1) Librarian\n2) Administrator\n3) Borrower\n4) Exit");
		int choice = util.getInput(4,1); 
		//Send the user to the proper view
		switch(choice) {
		case 1:
			lib.lib1();break;
		case 2:
			adm.admMain();break;
		case 3:
			borr.borr1();break;
		case 4:
			System.out.println("\nNow Exiting the SS Library Management System. Goodbye!");
			System.exit(0);
		}
	}
}