//@author: TylerRondeau
//Service for connecting between Borrower view and DAO's
package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.BookLoansDAO;
import com.ss.lms.dao.BorrowerDAO;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.BookLoans;
import com.ss.lms.entity.Borrower;
import com.ss.lms.view.LibraryMain;
import com.ss.lms.view.UtilityFunctions;

public class BorrowerService {
	UtilityFunctions util = new UtilityFunctions();
	ConnectionUtil cUtil = new ConnectionUtil();
	
	public void printBooks(ArrayList<Book> books) {
		int count = 1;
		for(Book book : books) {
			util.println(count + ") " + book.getTitle());
			count++;
		}
	}
	//Method to validate users input as a borrower
	public Boolean checkCard(int cardNo) {
		ArrayList<Borrower> borrowers = getBorrowers();
		for(Borrower borrower : borrowers) {
			if(cardNo == borrower.getCardNo()) {
				return true;
			}
		}
		return false;
	}
	//Method to get all borrowers
	public ArrayList<Borrower> getBorrowers(){
		Connection conn = null;
		ArrayList<Borrower> borrowers = new ArrayList<Borrower>();
		try {
		conn = cUtil.getConnection();
		BorrowerDAO borDAO = new BorrowerDAO(conn);
		borrowers = borDAO.getBorrowers();
		conn.commit();
		}catch(Exception e ) {
			if(conn!=null) {
				try {
					e.printStackTrace();
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return borrowers;
	}
	//Method to get available books at a branch
	public ArrayList<Book> getAvailableBooks(int branchId) {
		ArrayList<BookCopies> bookCopies = new ArrayList<BookCopies>();
		ArrayList<Book> books = new ArrayList<Book>();
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookCopiesDAO bkcDAO = new BookCopiesDAO(conn);
		BookDAO bkDAO = new BookDAO(conn);
		bookCopies = bkcDAO.getAvailableBooks(branchId);
		//Transform the array from BookCopies to Book by using the bookId in BookCopies to find the Book
		for(BookCopies bk : bookCopies) {
			books.add(bkDAO.getBook(bk.getBookId()));
		}
		conn.commit();
		}catch(Exception e ) {
			if(conn!=null) {
				try {
					e.printStackTrace();
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return books;
	}
	//Method to loan a book from a branch to a borrower with card on dateOut till dateDue
	public void loanBook(int bkId, int brId, int cardNo,LocalDate dateOut,LocalDate dateDue) {
		Connection conn = null;
		if(loanExists(bkId,cardNo)) {
			System.out.println("Book is already loaned to you! Returning to main menu.");
			LibraryMain.mainMenu();
		}
		try {
		conn = cUtil.getConnection();
		BookLoansDAO bklDAO = new BookLoansDAO(conn);
		bklDAO.create(new BookLoans(bkId,brId,cardNo,dateOut,dateDue));
		conn.commit();
		}catch(Exception e ) {
			if(conn!=null) {
				try {
					e.printStackTrace();
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//Checks if a loan of a certain book to borrower with cardNo exists
	public Boolean loanExists(int bkId, int cardNo) {
		ArrayList <BookLoans> loans = getBookLoans();
		for (BookLoans loan : loans) {
			if(bkId == loan.getBookId()&& cardNo == loan.getCardNo()) {
				return true;
			}
		}
	return false;
	}
	//Method to get all bookLoans
	public ArrayList<BookLoans> getBookLoans(){
		Connection conn = null;
		ArrayList<BookLoans> loans = new ArrayList<BookLoans>();
		try {
		conn = cUtil.getConnection();
		BookLoansDAO bklDAO = new BookLoansDAO(conn);
		loans = bklDAO.getBookLoans();
		conn.commit();
		}catch(Exception e ) {
			if(conn!=null) {
				try {
					e.printStackTrace();
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loans;
	}
	//Method to get copies of one book
	public BookCopies getCopy(int bkId, int brId){
		Connection conn = null;
		BookCopies copy = null;
		try {
		conn = cUtil.getConnection();
		BookCopiesDAO bkcDAO = new BookCopiesDAO(conn);
		copy = bkcDAO.getBookCopy(bkId,brId);
		conn.commit();
		}catch(Exception e ) {
			if(conn!=null) {
				try {
					e.printStackTrace();
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return copy;
	}
	//Method to update copies of a book
	public void updateCopies(BookCopies copy){
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookCopiesDAO bkcDAO = new BookCopiesDAO(conn);
		bkcDAO.update(copy);
		conn.commit();
		}catch(Exception e ) {
			if(conn!=null) {
				try {
					e.printStackTrace();
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//Method to get books loaned to cardNo from branch
	public ArrayList<Book> getLoanedBooks(int brId, int cardNo){
		Connection conn = null;
		ArrayList<Book> books = new ArrayList<Book>();
		try {
		conn = cUtil.getConnection();
		BookLoansDAO bklDAO = new BookLoansDAO(conn);
		BookDAO bkDAO = new BookDAO(conn);
		ArrayList<BookLoans> loans = bklDAO.getLoanedBooks(brId, cardNo);
		//Transform the array from BookLoans to Book by using the bookId in BookCopies to find the Book
		for(BookLoans loan : loans) {
			books.add(bkDAO.getBook(loan.getBookId()));
		}
		conn.commit();
		}catch(Exception e ) {
			if(conn!=null) {
				try {
					e.printStackTrace();
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return books;
	}
	//Method to check in a book
	public void returnBook(int bkId, int brId, int cardNo) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookLoansDAO bklDAO = new BookLoansDAO(conn);
		bklDAO.delete(bkId, brId, cardNo);
		conn.commit();
		}catch(Exception e ) {
			if(conn!=null) {
				try {
					e.printStackTrace();
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}