//@author: TylerRondeau
//Object representing the bookCopies table of the library database
package com.ss.lms.entity;

public class BookCopies {
	private int bookId, branchId, noOfCopies;
	
	//bookCopies constructor
	public BookCopies(int bookId, int branchId, int copies) {
		setBookId(bookId);
		setBranchId(branchId);
		setCopies(copies);
	}
	//get and set bookId
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	//get and set branchId
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	//get and set noOfCopies
	public int getCopies() {
		return noOfCopies;
	}
	public void setCopies(int copies) {
		this.noOfCopies = copies;
	}
}