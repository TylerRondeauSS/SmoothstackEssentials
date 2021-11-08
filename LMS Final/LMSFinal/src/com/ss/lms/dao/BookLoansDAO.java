// @author TylerRondeau
// Data Access Object for pulling data from the bookLoans table in our Library database
package com.ss.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.ss.lms.entity.BookLoans;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;

public class BookLoansDAO extends BaseDAO<BookLoans>{
	
	//BookLoansDAO Constructor
	public BookLoansDAO(Connection conn) {
		super(conn);
	}
	//Method to get all BookLoans
	public ArrayList<BookLoans> getBookLoans() throws ClassNotFoundException, SQLException{
		return read("SELECT bookId, branchId, cardNo, dateOut, dueDate FROM tbl_book_loans", null);
	}
	//Method to delete a BookLoans
	public void delete(int bkId, int brId, int cardNo) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo= ?", new Object[] {bkId,brId,cardNo});
	}
	//Method to delete a BookLoans by only bkId
	public void delete(int bkId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_loans WHERE bookId = ?", new Object[] {bkId});
	}
	//Method to delete a BookLoans by only brId
	public void delete(Branch br) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_loans WHERE branchId = ?", new Object[] {br.getBranchId()});
	}
	//Method to delete a BookLoans by only borId
	public void delete(Borrower bor) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_loans WHERE cardNo = ?", new Object[] {bor.getCardNo()});
	}
	//Method to update a BookLoans to a new due date using cardNo and bookId
	public void update(BookLoans bkl) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_book_loans SET dueDate=? WHERE cardNo=? AND bookId=?",new Object[] {bkl.getDateDue(),bkl.getCardNo(),bkl.getBookId()});
	}
	//Method to create a BookLoans
	public void create(BookLoans loan) throws ClassNotFoundException,SQLException{
		exe("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES (?, ?, ?,?,?)",
				new Object[] {loan.getBookId(),loan.getBranchId(),loan.getCardNo(),loan.getDateOut(),loan.getDateDue()});
	}
	//Method to get one BookLoans
	public BookLoans getBookLoan(int bkId,int brId, int cardNo) throws ClassNotFoundException, SQLException{
		ArrayList<BookLoans> loan = read("SELECT bookId, branchId, cardNo, dateOut, dueDate FROM tbl_book_loans WHERE bookId=? AND branchId=? AND cardNo=?", 
				new Object[] {bkId,brId,cardNo});
		return loan.get(0);
	}
	//Get all Loans by a specific person at a specific branch
	public ArrayList<BookLoans> getLoanedBooks(int brId, int cardNo) throws ClassNotFoundException, SQLException{
		return read("SELECT bookId,branchId,cardNo,dateOut,dueDate FROM tbl_book_loans WHERE branchId=? AND cardNo=?",new Object[] {brId,cardNo});
	}
	//Method to convert ResultSet into ArrayList<Book>
	@Override
	protected ArrayList<BookLoans> extractData(ResultSet rs) throws SQLException {
		ArrayList<BookLoans> loans = new ArrayList<BookLoans>();
		while(rs.next()) {
			int bkId = rs.getInt("bookId");
			int brId = rs.getInt("branchId");
			int cardNo = rs.getInt("cardNo");
			LocalDate out = rs.getObject("dateOut", LocalDate.class);
			LocalDate due = rs.getObject("dueDate", LocalDate.class);
			BookLoans loan = new BookLoans(bkId,brId,cardNo,out,due);
			loans.add(loan);
		}
		rs.close();
		return loans;
	}
}