//@author: TylerRondeau
//Object representing the bookAuthors table of the library database
package com.ss.lms.entity;

public class BookAuthors {
	private int authId,bookId;
	
	//BookAuthors constructor
	public BookAuthors(int bookId, int authId) {
		setBookId(bookId);
		setAuthId(authId);
	}
	//get and set bookId
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	//get and set authId
	public int getAuthId() {
		return authId;
	}
	public void setAuthId(int authId) {
		this.authId = authId;
	}
}