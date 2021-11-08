// @author TylerRondeau
// Data Access Object for pulling data from the bookAuthors table in our Library database
package com.ss.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.Author;
import com.ss.lms.entity.BookAuthors;

public class BookAuthorsDAO extends BaseDAO<BookAuthors>{
	
	//BookAuthorsDAO constructor
	public BookAuthorsDAO(Connection conni) {
		super(conni);
	}
	//Method to get all BookAuthors
	public ArrayList<BookAuthors> getBookAuthors() throws ClassNotFoundException, SQLException{
		return read("SELECT bookId, authId FROM tbl_book_authors", null);
	}
	//Method to get all BookAuthors with a specific author
	public ArrayList<BookAuthors> getBookAuthors(int authId) throws ClassNotFoundException, SQLException{
		return read("SELECT bookId, authorId FROM tbl_book_authors WHERE authorId = ?", new Object[] {authId});
	}
	//Method to delete a BookAuthors
	public void delete(int bkId, int authId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_authors WHERE bookId = ? AND authorId = ?", new Object[] {bkId,authId});
	}
	//Method to delete a BookAuthors by bookId
	public void delete(int bkId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_authors WHERE bookId = ?", new Object[] {bkId});
	}
	//Method to delete a BookAuthors by authId
	public void delete(Author auth) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_authors WHERE authorId = ?", new Object[] {auth.getId()});
	}
	//Method to update a BookAuthors bookId
	public void updateBook(BookAuthors bkA) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_book_authors SET bookId=? WHERE authorId=?",new Object[] {bkA.getBookId(),bkA.getAuthId()});
	}
	//Method to update a BookAuthors
	public void updateAuth(BookAuthors bkA) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_book_authors SET authorId=? WHERE bookId=?",new Object[] {bkA.getAuthId(),bkA.getBookId()});
	}
	//Method to create a BookAuthors
	public void create(BookAuthors bkA) throws ClassNotFoundException,SQLException{
		exe("INSERT INTO tbl_book_authors (bookId, authorId) VALUES (?, ?)",new Object[] {bkA.getBookId(),bkA.getAuthId()});
	}
	//Method to get one BookAuthors
	public BookAuthors getBookAuthor(int bkId,int authId) throws ClassNotFoundException, SQLException{
		ArrayList<BookAuthors> bkAuths = read("SELECT bookId, authorId FROM tbl_book_authors WHERE bookId = ? AND authorId = ?", new Object[] {bkId,authId});
		return bkAuths.get(0);
	}
	//Method to get one BookAuthors with only bookId
	public BookAuthors getBookAuthor(int bkId) throws ClassNotFoundException, SQLException{
		ArrayList<BookAuthors> bkAuths = read("SELECT bookId, authorId FROM tbl_book_authors WHERE bookId = ?", new Object[] {bkId});
		return bkAuths.get(0);
	}
	//Method to convert ResultSet into ArrayList<BookAuthors>
	@Override
	protected ArrayList<BookAuthors> extractData(ResultSet rs) throws SQLException {
		ArrayList<BookAuthors> bkAuths = new ArrayList<BookAuthors>();
		while(rs.next()) {
			int bkId = rs.getInt("bookId");
			int authId = rs.getInt("authorId");
			BookAuthors bkAuth = new BookAuthors(bkId,authId);
			bkAuths.add(bkAuth);
		}
		rs.close();
		return bkAuths;
	}
}