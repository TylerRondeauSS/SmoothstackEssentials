// @author TylerRondeau
// Data Access Object for pulling data from the bookCopies table in our Library database
package com.ss.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.Branch;


public class BookCopiesDAO extends BaseDAO<BookCopies>{
	
	//BookCopiesDAO constructor
	public BookCopiesDAO(Connection conn) {
		super(conn);
	}
	//Method to get all BookCopies
	public ArrayList<BookCopies> getBookCopies() throws ClassNotFoundException, SQLException{
		return read("SELECT bookId, branchId, noOfCopies FROM tbl_book_copies ", null);
	}
	//Method to delete a BookCopies
	public void delete(int bkId,int brId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[] {bkId, brId});
	}
	//Method to delete a BookCopies by only bookId
	public void delete(int bkId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_copies WHERE bookId = ?", new Object[] {bkId});
	}
	//Method to delete a BookCopies by only bookId
	public void delete(Branch br) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_copies WHERE branchId = ?", new Object[] {br.getBranchId()});
	}
	//Method to update a BookCopies
	public void update(BookCopies bkc) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? AND branchId = ?",new Object[] {bkc.getCopies(),bkc.getBookId(),bkc.getBranchId()});
	}
	//Method to create a BookCopies
	public void create(BookCopies bkc) throws ClassNotFoundException,SQLException{
		exe("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?, ?, ?)",new Object[] {bkc.getBookId(),bkc.getBranchId(),bkc.getCopies()});
	}
	//Method to get one BookCopies object from bookId and branchId
	public BookCopies getBookCopy(int bkId, int brId) throws ClassNotFoundException, SQLException{
		ArrayList<BookCopies> book = read("SELECT bookId,branchId,noOfCopies FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[] {bkId,brId});
		//If the ArrayList is empty, return null otherwise return the first BookCopies (the only one)
		if(book.isEmpty()) {return null;}
		else{return book.get(0);}
	}
	//Method to get all available books and their number of copies from a branch
	public ArrayList<BookCopies> getAvailableBooks(int brId) throws ClassNotFoundException, SQLException{
		return read("SELECT bookId,branchId, noOfCopies FROM tbl_book_copies WHERE branchId=? AND noOfCopies!=0", new Object[] {brId});
	}
	//Method to convert ResultSet into ArrayList<BookCopies>
	@Override
	protected ArrayList<BookCopies> extractData(ResultSet rs) throws SQLException {
		ArrayList<BookCopies> books = new ArrayList<BookCopies>();
		while(rs.next()) {
			int bkId = rs.getInt("bookId");
			int brId = rs.getInt("branchId");
			int copies = rs.getInt("noOfCopies");
			BookCopies book = new BookCopies(bkId,brId,copies);
			books.add(book);
		}
		rs.close();
		return books;
	}
}