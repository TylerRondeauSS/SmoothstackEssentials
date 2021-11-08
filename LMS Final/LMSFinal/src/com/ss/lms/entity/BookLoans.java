//@author: TylerRondeau
//Object representing the bookLoans table of the library database
package com.ss.lms.entity;

import java.time.LocalDate;

public class BookLoans {
	private int bookId,branchId,cardNo;
	private LocalDate LocalDateOut,LocalDateDue;
	
	//BookLoans constructor
	public BookLoans(int bookId, int branchId, int cardNum, LocalDate LocalDateOut, LocalDate LocalDateDue) {
		setBookId(bookId);
		setBranchId(branchId);
		setCardNo(cardNum);
		setDateOut(LocalDateOut);
		setDateDue(LocalDateDue);
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
	//get and set cardNo
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNum) {
		this.cardNo = cardNum;
	}
	//get and set dateOut
	public LocalDate getDateOut() {
		return LocalDateOut;
	}
	public void setDateOut(LocalDate LocalDateOut) {
		this.LocalDateOut = LocalDateOut;
	}
	//get and set dateDue
	public LocalDate getDateDue() {
		return LocalDateDue;
	}
	public void setDateDue(LocalDate LocalDateDue) {
		this.LocalDateDue = LocalDateDue;
	}
}