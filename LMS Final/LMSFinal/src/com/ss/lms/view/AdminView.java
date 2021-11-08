//@author: TylerRondeau
//This is the view for Administrators that handles all output and leads them through the system
package com.ss.lms.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookAuthors;
import com.ss.lms.entity.BookGenres;
import com.ss.lms.entity.BookLoans;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.Publisher;
import com.ss.lms.service.AdminService;
import com.ss.lms.service.BorrowerService;
import com.ss.lms.service.LibrarianService;

public class AdminView {
	//Instantiate UtilityFunctions and our services
	UtilityFunctions util = new UtilityFunctions();
	AdminService admSer = new AdminService();
	BorrowerService borSer = new BorrowerService();
	LibrarianService libSer = new LibrarianService();
	//Scanner we may need
	Scanner scan = new Scanner(System.in);
	//Create ArrayLists we may require
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<Borrower> bors = new ArrayList<Borrower>();
	ArrayList<Author> auths = new ArrayList<Author>();
	ArrayList<Publisher> pubs = new ArrayList<Publisher>();
	ArrayList<Branch> brs = new ArrayList<Branch>();
	ArrayList<Genre> gens = new ArrayList<Genre>();
	
	//Main method for admin which brings to sub-menus or calls ovrDue
	public void admMain() {
		int choice = 0;
		util.println("\nAdmin Menu: ");
		util.println("1) Book Menu\n2) Author Menu\n3) Genre Menu\n4) Publisher Menu\n5) Library Branch Menu\n6) Borrower Menu\n7) Override Book Due Date\n8) Return to Main Menu");
		choice = util.getInput(8, 1);
		switch(choice) {
		case 1:
			bookMenu();break;
		case 2:
			authMenu();break;
		case 3: 
			genMenu();break;
		case 4:
			pubMenu();break;
		case 5:
			branchMenu();break;
		case 6:
			borrMenu();break;
		case 7:
			changeDueDate();break;
		}
		LibraryMain.mainMenu();
	}
	//Method to change the due date of a loan
	private void changeDueDate() {
		//Get borrowers from the method in BorrowerService and LibrarianService
		ArrayList<Borrower> borrs = borSer.getBorrowers();
		ArrayList<Branch> branches = libSer.getBranches();
		//If we close this scanner it will bug UtilityFunctions
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		if(borrs.size()==0) {
			util.println("No Borrowers exist");
			admMain();
		}
		
		if(branches.size()==0) {
			util.println("No Branches exist");
			admMain();
		}
		//Choose Borrowers
		util.println("Which Borrower would you like to change the due date for : ");
		admSer.printBorrowers();
		int choice = util.getInput(borrs.size(), 1);
		Borrower borr = borrs.get(choice-1);
		int cardNo = borr.getCardNo();
		//Choose Branch
		util.println("Which Branch was the book taken out from : ");
		libSer.printBranches();
		choice = util.getInput(branches.size(), 1);
		Branch br = branches.get(choice-1);
		//Choose Loan
		ArrayList<Book> loans = borSer.getLoanedBooks(br.getBranchId(), cardNo);
		if(loans.size() == 0) {
			util.println("User has no loans in this branch. Returning to admin menu.");
			admMain();
		}
		util.println("Which book due date would you like to change : ");
		libSer.printBooks(loans);
		choice = util.getInput(loans.size(), 1);
		Book bk = loans.get(choice-1);
		//Get the BookLoan for the Branch,Borrower and Book selected
		BookLoans loan = admSer.getLoan(bk.getId(),br.getBranchId(),cardNo);
		util.println("Please enter the date you'd like the book to be due in format yyyy-mm-dd : ");
		String date = scan.nextLine();
		//parse the string to a localDate then change the dueDate to it and update it in the server
		LocalDate newDue = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
		loan.setDateDue(newDue);
		admSer.updateLoanDue(loan);
		util.println("Due date changed! Returning to admin menu.");
		admMain();
	}
	//Method to perform CRUD operations on borrower table
	private void borrMenu() {
		//Set bors to all borrowers
		bors = borSer.getBorrowers();
		//Allow user to choose what they would like to do
		util.println("\nWhat would you like to do?\n1) Add Borrower\n2) Update Borrower\n3) Delete Borrower\n4) Print list of Borrowers\n5) Return to previous menu");
		int choice = util.getInput(5, 1);
		switch(choice) {
		case 1:
			addBorr();break;
		case 2:
			updBorr();break;
		case 3:
			delBorr();break;
		case 4:
			prntBorr();break;
		case 5:
			admMain();break;
		}
		
	}
	//Method to add a borrower
	private void addBorr() {
		//Set cardNo to current highest cardNo + 1
		int cardNo = bors.get(bors.size()-1).getCardNo()+1;
		//Get the name
		util.println("Please enter the name of the Borrower : ");
		String name = scan.nextLine();
		//Get the address
		util.println("Please enter the Borrower's address : ");
		String add = scan.nextLine();
		//Get the phone number
		util.println("Please enter the Borrower's phone number (ex. 888-888-8888: ");
		String phone = scan.nextLine();
		//Create the borrower and add it to the table
		Borrower newB = new Borrower(cardNo,name,add,phone);
		//Confirm that the inputs are correct
		util.println("Please confirm you would like to create Borrower " + name + " with address " + add + " and phone number " + phone + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.addBorrower(newB); util.println("Borrower added successfully. Their card number is " + cardNo);}
		borrMenu();
	}
	//Method to update a borrower
	private void updBorr() {
		//Set max to bors size and ask which borrower they would like to update, then get that borrower
		int max = bors.size();
		util.println("What Borrower would you like to update from the following : ");
		admSer.printBorrowers();
		util.println("Which Borrower would you like to update : ");
		int choice = util.getInput(max, 1);
		Borrower bor = bors.get(choice-1);
		//Ask if they would like to update the name
		util.println("Would you like to update the name from " + bor.getName() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			util.println("What would you like to change name to : ");
			bor.setName(scan.nextLine());
		}
		//Ask if they would like to change the address
		util.println("Would you like to update the address from " + bor.getAddress() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			util.println("What would you like to change the address to? ");
			bor.setAddress(scan.nextLine());
		}
		//Ask if they would like to change the phone number
		util.println("Would you like to update the phone number " + bor.getPhone() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			util.println("What would you like to change the phone number to? : ");
			bor.setPhone(scan.nextLine());
		}
		//Ask if the new Borrower is what they want to set everything to
		util.println("Borrower " + bor.getCardNo() + " will be set as follows: Name : " + bor.getName() + " Address : " + bor.getAddress() + " Phone : " + bor.getPhone());
		util.println("Is this what you want Borrower set as? (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.updBorrower(bor);util.println("Borrower updated.");}
		borrMenu();
	}
	//Method to delete a borrower
	private void delBorr() {
		//Ask the user what borrower they would like to delete
		int max = bors.size();
		util.println("Select the Borrower you would like to delete from the following  : ");
		admSer.printBorrowers();
		util.println("What Borrower ID would you like to delete : ");
		int choice = util.getInput(max, 1);
		Borrower del = bors.get(choice-1);
		//Confirm and delete
		util.println("Are you sure you would like to delete Borrower # " + choice + ") " + del.getName() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.delBorrower(del); util.println("Borrower Deleted.");}
		borrMenu();
	}
	//Method to print borrowers
	private void prntBorr() {
		//Print all borrowers and return to admMain
		util.println("\nHere is a list of all Borrowers in the library system!");
		admSer.printBorrowers();
		borrMenu();
	}
	//Method to perform CRUD operations on branch table
	private void branchMenu() {
		brs = libSer.getBranches();
		util.println("\nWhat would you like to do?\n1) Add Branch\n2) Update Branch\n3) Delete Branch\n4) Print list of Branches\n5) Return to previous menu");
		int choice = util.getInput(5, 1);
		switch(choice) {
		case 1:
			addBranch();break;
		case 2:
			updBranch();break;
		case 3:
			delBranch();break;
		case 4:
			prntBranch();break;
		case 5:
			admMain();break;
		}
		
	}
	//Method to add a branch
	private void addBranch() {
		//Create branchId one higher than highest current branchId
		int brId = brs.get(brs.size()-1).getBranchId()+1;
		//Take name of new branch
		util.println("What is the name of the new Branch : ");
		String name = scan.nextLine();
		//Take address of new branch
		util.println("What is the address of the new Branch : ");
		String add = scan.nextLine();
		//Confirm they would like to add branch with these traits, then add branch.
		Branch br = new Branch(brId,name,add);
		util.println("Are you sure you would like to add Branch by name : " + name + " and address : " + add + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.addBranch(br);util.println("New Branch added. The Branch ID is : " + brId);}
		branchMenu();
	}
	//Method to update a branch
	private void updBranch() {
		int max = brs.size();
		util.println("What Branch would you like to update from the following : ");
		libSer.printBranches();
		util.println("Which Branch would you like to update : ");
		int choice = util.getInput(max, 1);
		Branch br = brs.get(choice-1);
		//Ask if they would like to update the name
		util.println("Would you like to update the name from " + br.getBranchName() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			util.println("What would you like to change name to : ");
			br.setBranchName(scan.nextLine());
		}
		//Ask if they would like to change the address
		util.println("Would you like to update the address from " + br.getBranchAddress() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			util.println("What would you like to change the address to? ");
			br.setBranchAddress(scan.nextLine());
		}
		//Ask if the new Branch is what they want to set everything to
		util.println("Branch " + br.getBranchId() + " will be set as follows: Name : " + br.getBranchName() + " Address : " + br.getBranchAddress());
		util.println("Is this what you want Branch set as? (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.updBranch(br);util.println("Branch updated.");}
		branchMenu();
	}
	//Method to delete a branch
	private void delBranch() {
		//Set max to the size of brs
		int max = brs.size();
		//Prompt the user to select the branch for deleting then show them all branches and have them input their choice
		util.println("Select the Branch to delete from the following : ");
		libSer.printBranches();
		util.println("Which Branch would you like to delete : ");
		int choice = util.getInput(max, 1);
		Branch del = brs.get(choice-1);
		//Confirm their choice and delete the branch
		util.println("Are you sure you would like to delete Branch #" + choice + ") " + del.getBranchName() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.delBranch(del);util.println("Branch Deleted");}
		branchMenu();
	}
	//Method to print all branches
	private void prntBranch() {
		//print out all branches in the system
		util.println("\nHere is a list of all Branches in the Library System!");
		libSer.printBranches();
		branchMenu();
	}
	//Method to perform CRUD operations on publisher table
	private void pubMenu() {
		pubs = admSer.getPublishers();
		util.println("\nWhat would you like to do?\n1) Add Publisher\n2) Update Publisher\n3) Delete Publisher\n4) Print list of Publishers\n5) Return to previous menu");
		int choice = util.getInput(5, 1);
		switch(choice) {
		case 1:
			addPub();break;
		case 2:
			updPub();break;
		case 3:
			delPub();break;
		case 4:
			prntPub();break;
		case 5:
			admMain();break;
		}
		
	}
	//Method to add a publisher
	private void addPub() {
		//Set pubId to highest current pubId + 1
		int pubId = pubs.get(pubs.size()-1).getId()+1;
		//Ask user for publisher name
		util.println("What is the name of the new Publisher : ");
		String name = scan.nextLine();
		//Ask user for publisher address
		util.println("What is the address of the new Publisher : ");
		String add = scan.nextLine();
		//Ask user for publisher phone #
		util.println("What is the phone number of the new Publisher (ex. 888-888-8888) : ");
		String phone = scan.nextLine();
		//Create a new publisher and confirm details with user
		Publisher pub = new Publisher(pubId,name,add,phone);
		util.println("Are you sure you would like to create a new Publisher with name : " + name + ", address : " + add + " and phone : " + phone + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.addPublisher(pub);util.println("New Publisher added. Their Publisher ID is : " + pubId);}
		pubMenu();
	}
	//Method to update a publisher
	private void updPub() {
		int max = pubs.size();
		util.println("What Publisher would you like to update from the following : ");
		admSer.printPublishers();
		util.println("Which Publisher would you like to update : ");
		int choice = util.getInput(max, 1);
		Publisher pub = pubs.get(choice-1);
		//Ask if they would like to update the name
		util.println("Would you like to update the name from " + pub.getName() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			util.println("What would you like to change name to : ");
			pub.setName(scan.nextLine());
		}
		//Ask if they would like to change the address
		util.println("Would you like to update the address from " + pub.getAddress() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			util.println("What would you like to change the address to? ");
			pub.setAddress(scan.nextLine());
		}
		//Ask if they would like to change the phone number
		util.println("Would you like to update the phone number " + pub.getPhoneNumber() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			util.println("What would you like to change the phone number to? : ");
			pub.setPhoneNumber(scan.nextLine());
		}
		//Ask if the new Publisher is what they want to set everything to
		util.println("Publisher " + pub.getId() + " will be set as follows: Name : " + pub.getName() + " Address : " + pub.getAddress() + " Phone : " + pub.getPhoneNumber());
		util.println("Is this what you want Publisher set as? (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.updPublisher(pub);util.println("Publisher updated.");}
		pubMenu();
	}
	//Method to delete a publisher
	private void delPub() {
		//Set max to the size of pubs
		int max = pubs.size();
		//Prompt the user to select the publisher for deleting then show them all publishers and have them input their choice
		util.println("Select the Publisher to delete from the following : ");
		admSer.printPublishers();
		util.println("Which Publisher would you like to delete : ");
		int choice = util.getInput(max, 1);
		Publisher del = pubs.get(choice-1);
		//Confirm their choice and delete the publisher
		util.println("Are you sure you would like to delete Publisher #" + choice + ") " + del.getName() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			//Delete all books by this publisher
			admSer.delBook(del);
			//Delete the publisher
			admSer.delPublisher(del);
			util.println("Publisher Deleted");
			}
		pubMenu();
	}
	//Method to print all publishers
	private void prntPub() {
		//print out all branches in the system
		util.println("\nHere is a list of all Publishers in the Library System!");
		admSer.printPublishers();
		pubMenu();
	}
	//Method to perform CRUD operations on genre table
	private void genMenu() {
		//Set gens to all genres
		gens = admSer.getGenres();
		util.println("\nWhat would you like to do?\n1) Add Genre\n2) Update Genre\n3) Delete Genre\n4) Print list of Genres\n5) Return to previous menu");
		int choice = util.getInput(5, 1);
		switch(choice) {
		case 1:
			addGenre();break;
		case 2:
			updGenre();break;
		case 3:
			delGenre();break;
		case 4:
			prntGenre();break;
		case 5:
			admMain();break;
		}
		
	}
	//Method to add a genre
	private void addGenre() {
		//Set genreID to the current highest genreId + 1
		int genId = gens.get(gens.size()-1).getGenreId()+1;
		//Ask user for genre name
		util.println("What is the name of the new Genre : ");
		String name = scan.nextLine();
		//Make sure the user wants to create a genre by that name then create it
		Genre gen = new Genre(genId,name);
		util.println("Are you sure you want to create a new Genre by name : " + name + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.addGenre(gen); util.println("New Genre added. The Genre ID is : " + genId);}
		genMenu();
	}
	//Method to update a genre
	private void updGenre() {
		int max = gens.size();
		util.println("What Genre would you like to update from the following : ");
		admSer.printGenres();
		util.println("Which Genre would you like to update : ");
		int choice = util.getInput(max, 1);
		Genre gen = gens.get(choice-1);
		//Ask if they would like to update the name
		util.println("Would you like to update the name from " + gen.getGenreName() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			util.println("What would you like to change name to : ");
			gen.setGenreName(scan.nextLine());
		}
		//Ask if the new Genre is what they want to set everything to
		util.println("Genre " + gen.getGenreId() + " will be set as follows: Name : " + gen.getGenreName());
		util.println("Is this what you want Genre set as? (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.updGenre(gen);util.println("Genre updated.");}
		genMenu();
	}
	//Method to delete a genre
	private void delGenre() {
		//Set max to the size of gens
		int max = gens.size();
		//Prompt the user to select the genre for deleting then show them all genres and have them input their choice
		util.println("Select the Genre to delete from the following : ");
		admSer.printGenres();
		util.println("Which Genre would you like to delete : ");
		int choice = util.getInput(max, 1);
		Genre del = gens.get(choice-1);
		//Confirm their choice and delete the branch
		util.println("Are you sure you would like to delete Genre #" + choice + ") " + del.getGenreName() + " (y/n) : ");
		//Since we are deleting a genre we have to delete any books in the genre
		if(util.confirmChoice(scan.nextLine())) {
			//Get all books in the genre
			ArrayList<BookGenres> gens = admSer.getBookGenres(del);
			ArrayList<Book> bks = new ArrayList<Book>();
			for(BookGenres bkg : gens) {
				bks.add(admSer.getBook(bkg.getBookId()));
			}
			//Delete all books in the genre
			for(Book bk : bks) {
				admSer.delBook(bk);
			}
			//Finally delete the genre
			admSer.delGenre(del);
			util.println("Genre Deleted");
		}
		genMenu();
	}
	//Method to print all genres
	private void prntGenre() {
		//print out all branches in the system
		util.println("\nHere is a list of all genres in the Library System!");
		admSer.printGenres();
		genMenu();
	}
	//Method to perform CRUD operations on author table
	private void authMenu() {
		//Set auths = all authors
		auths = admSer.getAuthors();
		util.println("\nWhat would you like to do?\n1) Add Author\n2) Update Author\n3) Delete Author\n4) Print list of Authors\n5) Return to previous menu");
		int choice = util.getInput(5, 1);
		switch(choice) {
		case 1:
			addAuth();break;
		case 2:
			updAuth();break;
		case 3:
			delAuth();break;
		case 4:
			prntAuth();break;
		case 5:
			admMain();break;
		}
		
	}
	//Method to add an author
	private void addAuth() {
		//Make the authId 1 greater than current max
		int authId = auths.get(auths.size()-1).getId()+1;
		//Ask user for new author name
		util.println("What is the new Author's name : ");
		String name = scan.nextLine();
		//Verify that the user would like to make a new author by that name, then make it
		Author auth = new Author(authId,name);
		util.println("Are you sure you would like to create a new Author named : " + name + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.addAuthor(auth);util.println("New Author added. The Author ID for this Author is : " + authId);}
		authMenu();
	}
	//Method to update a author
	private void updAuth() {
		int max = auths.size();
		util.println("What Author would you like to update from the following : ");
		admSer.printAuthors();
		util.println("Which Author would you like to update : ");
		int choice = util.getInput(max, 1);
		Author auth = auths.get(choice-1);
		//Ask if they would like to update the name
		util.println("Would you like to update the name from " + auth.getName() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			util.println("What would you like to change name to : ");
			auth.setName(scan.nextLine());
		}
		//Ask if the new Author is what they want to set everything to
		util.println("Author " + auth.getId() + " will be set as follows: Name : " + auth.getName());
		util.println("Is this what you want Author set as? (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.updAuthor(auth);util.println("Author updated.");}
		authMenu();
	}
	//Method to delete an author
	private void delAuth() {
		//Set max to the size of auths
		int max = auths.size();
		//Prompt the user to select the Author for deleting then show them all Authors and have them input their choice
		util.println("Select the Author to delete from the following : ");
		admSer.printAuthors();
		util.println("Which Author would you like to delete : ");
		int choice = util.getInput(max, 1);
		Author del = auths.get(choice-1);
		//Confirm their choice and delete the Author
		util.println("Are you sure you would like to delete Author #" + choice + ") " + del.getName() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			//Since we're deleting an author, we have to delete all books written by them.
			ArrayList<BookAuthors> bkas = admSer.getBookAuthors(del);
			ArrayList<Book> bks = new ArrayList<Book>();
			for(BookAuthors bka : bkas) {
				bks.add(admSer.getBook(bka.getBookId()));
			}
			for(Book bk : bks) {
				admSer.delBook(bk);
				util.println("We here");
			}
			admSer.delAuthor(del);
			util.println("Author Deleted");
			}
		authMenu();
	}
	//Method to print all authors
	private void prntAuth() {
		//print out all branches in the system
		util.println("\nHere is a list of all Authors in the Library System!");
		admSer.printAuthors();
		authMenu();
	}
	//Method to perform CRUD operations on book table
	private void bookMenu() {
		//Set our ArrayList to all books in the LMS
		books = libSer.getBooks();
		util.println("\nWhat would you like to do?\n1) Add Book\n2) Update Book\n3) Delete Book\n4) Print list of Books\n5) Return to previous menu");
		int choice = util.getInput(5, 1);
		//Based on user choice take them to the menu for what they want to do
		switch(choice) {
		case 1:
			addBook();break;
		case 2:
			updBook();break;
		case 3:
			delBook();break;
		case 4:
			prntBook();break;
		case 5:
			admMain();break;
		}
	}
	//Method to print all books in the LMS then return to book menu
	private void prntBook() {
		//Print all books and return to bookMenu
		util.println("\nHere is a list of all books in the library system!");
		admSer.printBooks(books);
		bookMenu();
	}
	//Method to delete a book from the LMS
	private void delBook() {
		//Print the user all books and have them choose one to delete
		util.println("What book would you like to delete from the library system : ");
		admSer.printBooks(books);
		int max = books.size()+1;
		util.println(max + ") quit to previous menu");
		int choice = util.getInput(max, 1);
		//If they chose max return to bookMenu
		if(choice == max) {bookMenu();}
		//Get the book they wanted to delete and confirm delete again
		Book del = books.get(choice-1);
		util.println("Confirm deleting : " + del.getTitle() + " from the library system (y/n)");
		//If they typed y or Y delete the book and print that it was deleted
		if(util.confirmChoice(scan.nextLine())) {admSer.delBook(del); util.println(del.getTitle() + " deleted successfully");}
		//Return to bookMenu regardless
		util.println("Returning to Book Menu.\n");
		bookMenu();
	}
	//Method to update a book
	private void updBook() {
		pubs = admSer.getPublishers();
		util.println("What book would you like to update in the library system : ");
		admSer.printBooks(books);
		int max = books.size()+1;
		util.println(max + ") quit to previous menu");
		int choice = util.getInput(max, 1);
		//If they chose max return to bookMenu
		if(choice == max) {bookMenu();}
		//Get the book they wanted to update, then ask for each update-able value
		Book upd = books.get(choice-1);
		//Allow admin to change the title
		util.println("Enter the new title for "+upd.getTitle() + " or N/A for no change : ");
		String title = scan.nextLine();
		//If string is N/A do nothing, else set the title
		if(title.equalsIgnoreCase("N/A")) {}
		else{upd.setTitle(title);}
		//Allow admin to change the publisher
		util.println("Select the publisher : ");
		//print all publishers and allow user to choose one
		admSer.printPublishers();
		max = pubs.size()+1;
		choice = util.getInput(max, 1);
		if(choice == max) {bookMenu();}
		Publisher pub = pubs.get(choice-1);
		//set book pubId to chosen publisher's id
		upd.setPubId(pub.getId());
		//Allow admin to change the Author
		util.println("Would you like to change the author (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			//Get a list of authors, print all authors, then take the author they select
			auths = admSer.getAuthors();
			util.println("Select the author of this book : ");
			admSer.printAuthors();
			max = auths.size()+1;
			choice = util.getInput(max, 1);
			Author auth = auths.get(choice-1);
			//Confirm they'd like to make the change, then make it
			util.println("Are you sure you would like to change "+upd.getTitle()+"'s author to " + auth.getName() + " (y/n) : ");
			if(util.confirmChoice(scan.nextLine())){admSer.updBookAuth(new BookAuthors(upd.getId(),auth.getId()));}
		}
		//Allow admin to change the Genre
		util.println("Would you like to change the genre (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {
			//Set gens to all genres then have the user select the genre they'd like to change to
			gens = admSer.getGenres();
			util.println("Select the genre of this book : ");
			admSer.printGenres();
			max = gens.size()+1;
			choice = util.getInput(max, 1);
			Genre gen = gens.get(choice-1);
			//Confirm choice then update bookGenres table
			util.println("Are you sure you would like to change " + upd.getTitle() + "'s genre to " + gen.getGenreName() + " (y/n) : ");
			if(util.confirmChoice(scan.nextLine())) {admSer.updBookGenre(new BookGenres(upd.getId(),gen.getGenreId()));}
		}
		//confirm changes
		util.println("Confirm changes to set title to " + upd.getTitle() + " and publisher to " + pub.getName() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.updBook(upd);util.println("Changes completed!");}
		//Return to bookMenu
		util.println("Returning to book menu.\n");
		bookMenu();
	}
	//Method to add a book
	private void addBook() {
		//Fill the publishers ArrayList
		pubs = admSer.getPublishers();
		//Set the bookId to the first available bookId, which will be the last book in books id + 1
		int bkId = books.get(books.size()-1).getId()+1;
		util.println("New book will be id : " + bkId);
		//Set the title
		util.println("Enter the title for new book : ");
		String title = scan.nextLine();
		//Allow admin to set the Author
		util.println("Select the author of the new book : ");
		//Get a list of authors, print all authors, then take the author they select
		auths = admSer.getAuthors();
		util.println("Select the author of this book : ");
		admSer.printAuthors();
		int max = auths.size();
		int choice = util.getInput(max, 1);
		Author auth = auths.get(choice-1);
		//Set gens to all genres, print all genres, then have the user select the genre they'd like to select
		gens = admSer.getGenres();
		util.println("Select the genre of this book : ");
		admSer.printGenres();
		max = gens.size();
		choice = util.getInput(max, 1);
		Genre gen = gens.get(choice-1);
		//Choose the publisher of the book
		util.println("Select the publisher of this book : ");
		admSer.printPublishers();
		max = pubs.size();
		choice = util.getInput(max, 1);
		Publisher pub = pubs.get(choice-1);
		//Create our book
		Book newBk = new Book(bkId,title,pub.getId());
		//Update the tables to represent add a new book, bookAuthors, and bookGenres element
		util.println("Are you sure you would like to add a book with title : " + title + ", author : " 
				+ auth.getName() + ", genre : " + gen.getGenreName() + " and publisher : " + pub.getName() + " (y/n) : ");
		if(util.confirmChoice(scan.nextLine())) {admSer.addBook(newBk, gen.getGenreId(), auth.getId()); util.println("Book added successfully! The Book ID is : " + bkId);}
		//Return to bookMenu
		util.println("Returning to book menu.\n");
		bookMenu();
	}
}