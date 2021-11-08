//@author: TylerRondeau
//This is the view for Borrowers that handles all output and leads them through the system
package com.ss.lms.view;

import java.time.LocalDate;
import java.util.ArrayList;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.Branch;
import com.ss.lms.service.BorrowerService;
import com.ss.lms.service.LibrarianService;

public class BorrowerView {
	private int cardNo = -1;
	Branch br = null;
	UtilityFunctions util = new UtilityFunctions();
	BorrowerService borSer = new BorrowerService();
	LibrarianService libSer = new LibrarianService();
	//method that gets a borrowers card number so they can access the system
	public void borr1() {
		//ask for card number and by default assume non-valid
		Boolean valid = false;
		util.println("\nPlease enter your card number: ");
		//while it isn't valid, loop asking for input
		while(valid == false) {
			cardNo = util.getInput(Integer.MAX_VALUE, 1);
			//Check for validity, if it is, don't ask for card number again. If it isn't, ask them to reinput
			valid = borSer.checkCard(cardNo);
			if(valid == false) {
				util.println("Invalid card number. Please try again");
			}
		}
		util.println("\nWhat would you like to do : ");
		util.println("1) Check out a book\n2) Return a book\n3) Return to main menu");
		int choice = util.getInput(3, 1);
		//Based on user choice let them take a book, return a book, or go to main menu
		switch(choice) {
			case 1:
				takeBook();break;
			case 2: 
				returnBook();break;
			case 3:
				LibraryMain.mainMenu();
		}
	}
	//Method to check out a book
	public void takeBook() {
		//Method to choose a branch because we do it multiple times
		br = chooseBranch();
		util.println("\nWhat book would you like to check out : ");
		//Get books available at the chosen branch
		ArrayList<Book> books = borSer.getAvailableBooks(br.getBranchId());
		int max = books.size() +1;
		//Borrow a method from librarianService to print the list of books
		libSer.printBooks(books);
		util.println(max + ") quit to previous menu");
		int choice = util.getInput(max, 1);
		if (choice == max) {
			borr1();
		}
		//Take the chosen book, along with todays date and a week from today
		Book bk = books.get(choice - 1);
		LocalDate today = LocalDate.now();
		LocalDate oneWeek = today.plusWeeks(1);
		//Tell the service to loan us the book
		borSer.loanBook(bk.getId(), br.getBranchId(), cardNo,today,oneWeek);
		//Get a copy of the book we took out and update bookCopies to have 1 less book
		BookCopies copy = borSer.getCopy(bk.getId(),br.getBranchId());
		copy = new BookCopies(bk.getId(),br.getBranchId(),copy.getCopies()-1);
		borSer.updateCopies(copy);
		//Say we succeeded and go to mainMenu
		util.println("Book successfully checked out. This book is due back " + oneWeek + ". Thank you! Logging out.\n");
		LibraryMain.mainMenu();
	}
	//Method to return a book
	public void returnBook() {
		br = chooseBranch();
		util.println("\nWhat book would you like to return : ");
		//Get a list of books loaned to user from the selected branch and choose one to return
		ArrayList<Book> books = borSer.getLoanedBooks(br.getBranchId(),cardNo);
		int max = books.size() +1;
		libSer.printBooks(books);
		util.println(max + ") quit to previous menu");
		int choice = util.getInput(max, 1);
		if (choice == max) {
			borr1();
		}
		Book bk = books.get(choice - 1);
		//Return the book
		borSer.returnBook(bk.getId(),br.getBranchId(),cardNo);
		//Update bookCopies to have 1 more book
		BookCopies copy = borSer.getCopy(bk.getId(),br.getBranchId());
		copy = new BookCopies(bk.getId(),br.getBranchId(),copy.getCopies()+1);
		borSer.updateCopies(copy);
		util.println("Book successfully checked in. Thank you! Logging out.\n");
		LibraryMain.mainMenu();
	}
	//Helper method for choosing a branch so we don't have to write it twice
	public Branch chooseBranch() {
		//Use getBranches from librarianService since it's already written
		ArrayList<Branch> branches = libSer.getBranches();
		int max = branches.size() + 1;
		if(max==1) {
			util.println("\nThere are no branches in the System. Returning to main menu.\n");
			LibraryMain.mainMenu();
		}
		util.println("\nWhich library branch would you like to access :");
		//Use printBranches from librarianService since it's already written
		libSer.printBranches();
		util.println(max + ") quit to previous menu");
		int choice = util.getInput(max, 1);
		//If they choose the max option go to borr1 otherwise return their chosen branch
		if (choice == max) {borr1();}
		return branches.get(choice-1);
	}
}
