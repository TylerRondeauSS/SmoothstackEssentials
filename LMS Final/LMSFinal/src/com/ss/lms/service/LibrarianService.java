//@author TylerRondeau
//Service for connecting between the librarian view and DAO's
package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookAuthorsDAO;
import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.BranchDAO;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookAuthors;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.Branch;
import com.ss.lms.view.UtilityFunctions;

public class LibrarianService {
	UtilityFunctions util = new UtilityFunctions();
	ConnectionUtil cUtil = new ConnectionUtil();
	//Method to update an existing branch with an edited branch object
	public void updateBranch(Branch br) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BranchDAO brDAO = new BranchDAO(conn);
		brDAO.update(br);
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
	//Method to get an ArrayList of all branches
	public ArrayList<Branch> getBranches() {
		Connection conn = null;
		ArrayList<Branch> branches = new ArrayList<Branch>();
		try {
		conn = cUtil.getConnection();
		BranchDAO brDAO = new BranchDAO(conn);
		branches = brDAO.getBranches();
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
		return branches;
	}
	//Method to print all branches with formatting
	public void printBranches() {
		ArrayList<Branch> branches = getBranches();
		int count = 1;
		for(Branch branch : branches) {
			util.println(count + ") " + branch.getBranchName() + ", " + branch.getBranchAddress());
			count++;
		}
	}
	//method to get an ArrayList of all books
	public ArrayList<Book> getBooks(){
		Connection conn = null;
		ArrayList<Book> books = new ArrayList<Book>();
		try {
		conn = cUtil.getConnection();
		BookDAO bkDAO = new BookDAO(conn);
		books = bkDAO.getBooks();
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
	//method to print an ArrayList of books
	public void printBooks(ArrayList<Book> books) {
		int count = 1;
		for(Book book : books) {
			util.println(count + ") " + book.getTitle() + " by " + getAuthorOfBook(book).getName());
			count++;
		}
	}
	//Method to get author of a book
	public Author getAuthorOfBook(Book bk) {
		Connection conn = null;
		Author auth = null;
		try {
		conn = cUtil.getConnection();
		BookAuthorsDAO bkaDAO = new BookAuthorsDAO(conn);
		AuthorDAO authDAO = new AuthorDAO(conn);
		BookAuthors ba = bkaDAO.getBookAuthor(bk.getId());
		auth = authDAO.getAuthor(ba.getAuthId());
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
		return auth;
	}
	//method to get the number of copies of a book at a branch
	public int getCopies(int bookId, int branchId) {
		Connection conn = null;
		int copies = 0;
		try {
		conn = cUtil.getConnection();
		BookCopiesDAO bkcDAO = new BookCopiesDAO(conn);
		BookCopies book = bkcDAO.getBookCopy(bookId, branchId);
		//if book is null do nothing because copies is 0, otherwise take the noOfCopies
		if(book == null) {}
		else {copies = book.getCopies();}
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
		return copies;
	}
	//method to add a BookCopies to a branch, given the branchId
	public void addBook(int bkId, int brId, int quant) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookCopiesDAO bkcDAO = new BookCopiesDAO(conn);
		bkcDAO.create(new BookCopies(bkId,brId,quant));
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
	//method to update the number of copies of a book at a certain branch
	public void addCopies(int bkId, int brId, int quant) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookCopiesDAO bkcDAO = new BookCopiesDAO(conn);
		bkcDAO.update(new BookCopies(bkId,brId,quant));
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
