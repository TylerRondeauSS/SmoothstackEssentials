//@author: TylerRondeau
//This is the view for Librarians that handles all output and leads them through the system
package com.ss.lms.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.Branch;
import com.ss.lms.service.LibrarianService;

public class LibrarianView {
	Branch br = null;
	LibrarianService libSer = new LibrarianService();
	UtilityFunctions util = new UtilityFunctions();
	//Lib1 menu
	public void lib1() {
		util.println("\nWhich of the following would you like to do: ");
		util.println("1) Enter your library branch\n2) Go back to main menu");
		int choice = util.getInput(2, 1);
		//based on choice, go to lib2 or mainMenu
		switch(choice) {
			case 1:
				lib2();break;
			case 2:
				LibraryMain.mainMenu();break;
		}
	}
	//Lib2 menu
	public void lib2() {
		ArrayList<Branch> branches = libSer.getBranches();
		int max = branches.size() + 1;
		//If there are no branches, tell user and return to MainMenu
		if(max==1) {
			util.println("\nThere are no branches in the System. Returning to main menu.\n");
			LibraryMain.mainMenu();
		}
		//Choose library branch
		util.println("\nPlease choose your library branch from the following :");
		libSer.printBranches();
		util.println(max + ") quit to previous menu");
		int choice = util.getInput(max, 1);
		//If they chose max, return them to previous menu
		if(choice == max) {
			lib1();
		}
		//Set branch to their choice, and continue to lib3
		br = branches.get(choice-1);
		lib3();
	}
	//Lib3 menu
	public void lib3() {
		util.println("\nPlease select an action : ");
		util.println("1) Update the details of the library\n2) Add copies of a book to the branch\n3) Return to previous menu");
		int choice = util.getInput(3, 1);
		//Based on choice go to one of: branchUpdate, addBook, or back to lib2
		switch (choice) {
			case 1: 
				branchUpdate();break;
			case 2:
				addBook();break;
			case 3:
				lib2();break;
		}
	}
	//Method to lead the user through updating a branch
	public void branchUpdate() {
		//Create a new branch the same as br
		util.println("\nYou have chosen to update branch : " + br.getBranchId() + " with Branch Name: " + br.getBranchName() + " and Branch Address : " + br.getBranchAddress());
		util.println("Type 'quit' at any time to exit");
		//If we close the scanner here it bugs out UtilityFunctions
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		//Change branch name
		util.println("Please enter new branch name or enter N/A for no change: ");
		String name = scan.nextLine();
		//if they typed N/A don't change name, otherwise do
		if("N/A".equalsIgnoreCase(name)) {}
		else{br.setBranchName(name);}
		//check if they typed exit or main and react accordingly
		util.exitOrMain(name);
		//Change branch address
		util.println("Please enter new branch address or enter N/A for no change: ");
		String add = scan.nextLine();
		//if they typed N/A don't change address, otherwise do
		if("N/A".equalsIgnoreCase(add)) {}
		else{br.setBranchAddress(add);}
		//check if they typed exit or main and react accordingly
		util.exitOrMain(add);
		//Update the branch
		libSer.updateBranch(br);
		lib3();
	}
	//Method to lead the user through adding a book to their branch
	public void addBook() {
		//Get a list of the books in the entire library system
		ArrayList<Book> books = libSer.getBooks();
		int max = books.size() + 1;
		//if there are no books, tell the user and return to mainMenu
		if(max==1) {
			util.println("\nThere are no books in the System. Returning to main menu.\n");
			LibraryMain.mainMenu();
		}
		//print all books in the library system and select one to add copies of
		util.println("\nPick the book you'd like to add copies of: ");
		libSer.printBooks(books);
		util.println(max + ") quit to previous menu");
		int choice = util.getInput(max, 1);
		//if they chose the last option, return to lib3
		if (choice == max) {
			lib3();
		}
		//Using their book choice, find out how many copies exist at the branch
		Book bk = books.get(choice - 1);
		int copies = libSer.getCopies(bk.getId(),br.getBranchId());
		//Tell the user how many copies there are and ask how many to add
		util.println("Existing copies : " + copies);
		util.println("How many new copies are we adding? ");
		int quant = util.getInput(Integer.MAX_VALUE, 0);
		//if there are currently no copies, and they want to add even 1 we must add the book to the branch 
		if(copies == 0 && quant > 0) {
			libSer.addBook(bk.getId(), br.getBranchId(),quant);
		}
		//if there are currently any copies, simply update the total copies
		int total = quant+copies;
		libSer.addCopies(bk.getId(),br.getBranchId(),total);
		//print to the user what they changed, and the new values then return to lib3
		util.println("Copies of " + bk.getTitle() + " updated in branch " + br.getBranchName());
		util.println("New total : " + libSer.getCopies(bk.getId(), br.getBranchId()));
		lib3();
	}
}