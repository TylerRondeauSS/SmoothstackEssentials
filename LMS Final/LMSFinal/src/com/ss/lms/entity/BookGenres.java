//@author: TylerRondeau
//Object representing the bookGenres table of the library database
package com.ss.lms.entity;

public class BookGenres {
	private int genreId, bookId;
	
	//BookGenres constructor
	public BookGenres(int genreId, int bookId) {
		this.genreId = genreId;
		this.bookId = bookId;
	}
	//get and set genreId
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	//get and set bookId
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
}