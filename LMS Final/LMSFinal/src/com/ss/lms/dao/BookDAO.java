// @author TylerRondeau
// Data Access Object for pulling data from the book table in our Library database
package com.ss.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.Publisher;


public class BookDAO extends BaseDAO<Book>{
	
	//BookDAO constructor
	public BookDAO(Connection conn) {
		super(conn);
	}
	//Method to get all Books
	public ArrayList<Book> getBooks() throws ClassNotFoundException, SQLException{
		return read("SELECT bookId, title, pubId FROM tbl_book", null);
	}
	//Method to delete a Book by bookId
	public void delete(int bkId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book WHERE bookId = ?", new Object[] {bkId});
	}
	//Method to delete all Books by pubId
	public void delete(Publisher pub) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book WHERE bookId = ?", new Object[] {pub.getId()});
	}
	//Method to update a Book
	public void update(Book bk) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_book SET title=?,pubId=? WHERE bookId=?",new Object[] {bk.getTitle(),bk.getPublishId(),bk.getId()});
	}
	//Method to create a Book
	public void create(Book bk) throws ClassNotFoundException,SQLException{
		exe("INSERT INTO tbl_book (bookId, title, pubId) VALUES (?, ?, ?)",new Object[] {bk.getId(),bk.getTitle(),bk.getPublishId()});
	}
	//Method to get one Books
	public Book getBook(int bkId) throws ClassNotFoundException, SQLException{
		ArrayList<Book> book = read("SELECT bookId, title, pubId FROM tbl_book WHERE bookId = ?", new Object[] {bkId});
		return book.get(0);
	}
	//Method to convert ResultSet into ArrayList<Book>
	@Override
	protected ArrayList<Book> extractData(ResultSet rs) throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		while(rs.next()) {
			int id = rs.getInt("bookId");
			String title = rs.getString("title");
			int pubId = rs.getInt("PubId");
			Book book = new Book(id,title,pubId);
			books.add(book);
		}
		rs.close();
		return books;
	}
}