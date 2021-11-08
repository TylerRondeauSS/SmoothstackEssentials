//@author: TylerRondeau
//Service for connecting between adminView and DAO's
package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookAuthorsDAO;
import com.ss.lms.dao.BookCopiesDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.dao.BookGenresDAO;
import com.ss.lms.dao.BookLoansDAO;
import com.ss.lms.dao.BorrowerDAO;
import com.ss.lms.dao.BranchDAO;
import com.ss.lms.dao.GenreDAO;
import com.ss.lms.dao.PublisherDAO;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookAuthors;
import com.ss.lms.entity.BookGenres;
import com.ss.lms.entity.BookLoans;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.Publisher;
import com.ss.lms.view.UtilityFunctions;

public class AdminService {
	UtilityFunctions util = new UtilityFunctions();
	ConnectionUtil cUtil = new ConnectionUtil();
	LibrarianService libSer = new LibrarianService();
	BorrowerService borSer = new BorrowerService();
	
	//Method to get a single loan
	public BookLoans getLoan(int bkId,int brId, int cardNo) {
		Connection conn = null;
		BookLoans loan = null;
		try {
		conn = cUtil.getConnection();
		BookLoansDAO bklDAO = new BookLoansDAO(conn);
		loan = bklDAO.getBookLoan(bkId,brId,cardNo);
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
		return loan;
	}
	//Method to print borrowers
	public void printBorrowers() {
		ArrayList<Borrower> borrs = borSer.getBorrowers();
		int count = 1;
		for(Borrower borr : borrs) {
			util.println(count + ") " + borr.getName() + " Address : " + borr.getAddress() + " Phone number : " + borr.getPhone());
			count++;
		}
	}
	//Method to update dueDate of a loan
	public void updateLoanDue(BookLoans loan) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookLoansDAO bklDAO = new BookLoansDAO(conn);
		bklDAO.update(loan);
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
	//Method to delete a book
	public void delBook(Book bk) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		//Connect to relevant DAO's (anywhere bookId is stored)
		BookGenresDAO bkgDAO = new BookGenresDAO(conn);
		BookCopiesDAO bkcDAO = new BookCopiesDAO(conn);
		BookLoansDAO bklDAO = new BookLoansDAO(conn);
		BookAuthorsDAO bkaDAO = new BookAuthorsDAO(conn);
		BookDAO bkDAO = new BookDAO(conn);
		//delete from book, bookAuthors, bookLoans, bookCopies, and bookGenres tables
		bkDAO.delete(bk.getId());
		bkaDAO.delete(bk.getId());
		bklDAO.delete(bk.getId());
		bkcDAO.delete(bk.getId());
		bkgDAO.delete(bk.getId());
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
	//Method to update a book
	public void updBook(Book bk) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookDAO bkDAO = new BookDAO(conn);
		bkDAO.update(bk);
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
	//Method to add a book
	public void addBook(Book bk, int genId, int authId) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		//Connect to relevant DAO's
		BookGenresDAO bkgDAO = new BookGenresDAO(conn);
		BookAuthorsDAO bkaDAO = new BookAuthorsDAO(conn);
		BookDAO bkDAO = new BookDAO(conn);
		//Create objects needed to add to bookGenres and bookAuthors
		BookGenres bkg = new BookGenres(genId,bk.getId());
		BookAuthors bka = new BookAuthors(bk.getId(),authId);
		//Create the book in necessary tables
		bkDAO.create(bk);
		bkgDAO.create(bkg);
		bkaDAO.create(bka);
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
	//Method to print Publishers
	public void printPublishers() {
		ArrayList<Publisher> pubs = getPublishers();
		int count = 1;
		for(Publisher pub : pubs) {
			util.println(count + ") " + pub.getName());
			count++;
		}
	}
	//Method to get all Publishers
	public ArrayList<Publisher> getPublishers(){
		Connection conn = null;
		ArrayList<Publisher> pubs = new ArrayList<Publisher>();
		try {
		conn = cUtil.getConnection();
		PublisherDAO pubDAO = new PublisherDAO(conn);
		pubs = pubDAO.getPublishers();
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
		return pubs;
	}
	//Method to get all Authors
	public ArrayList<Author> getAuthors(){
		Connection conn = null;
		ArrayList<Author> auths = new ArrayList<Author>();
		try {
		conn = cUtil.getConnection();
		AuthorDAO authDAO = new AuthorDAO(conn);
		auths = authDAO.getAuthors();
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
		return auths;
	}
	//Method to print all authors
	public void printAuthors() {
		ArrayList<Author> auths = getAuthors();
		int count = 1;
		for(Author auth : auths) {
			util.println(count + ") " + auth.getName());
			count++;
		}
	}
	//Method to update bookAuthors table
	public void updBookAuth(BookAuthors bka) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookAuthorsDAO bkaDAO = new BookAuthorsDAO(conn);
		bkaDAO.updateAuth(bka);
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
	//Method to get all Genres
	public ArrayList<Genre> getGenres(){
		Connection conn = null;
		ArrayList<Genre> gens = new ArrayList<Genre>();
		try {
		conn = cUtil.getConnection();
		GenreDAO genDAO = new GenreDAO(conn);
		gens = genDAO.getGenres();
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
		return gens;
	}
	//Method to print all genres
	public void printGenres() {
		ArrayList<Genre> gens = getGenres();
		int count = 1;
		for(Genre gen : gens) {
			util.println(count + ") " + gen.getGenreName());
			count++;
		}
	}
	//Method to update bookGenres table
	public void updBookGenre(BookGenres bkg) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookGenresDAO bkgDAO = new BookGenresDAO(conn);
		bkgDAO.updateGenre(bkg);
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
	//method to print an ArrayList of books and their genre
	public void printBooks(ArrayList<Book> books) {
		int count = 1;
		for(Book book : books) {
			util.println(count + ") " + book.getTitle() + " by " + libSer.getAuthorOfBook(book).getName() + " genre : " 
					+ getGenreOfBook(book).getGenreName() + " publisher : " + getPublisher(book.getPublishId()).getName());
			count++;
		}
	}
	//Method to get the genre of a book
	public Genre getGenreOfBook(Book bk) {
		Connection conn = null;
		Genre gen = null;
		try {
		conn = cUtil.getConnection();
		BookGenresDAO bkgDAO = new BookGenresDAO(conn);
		GenreDAO genDAO = new GenreDAO(conn);
		BookGenres bkGen = bkgDAO.getBookGenre(bk.getId());
		gen = genDAO.getGenre(bkGen.getGenreId());
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
		return gen;
	}
	//Method to get a publisher by id
	public Publisher getPublisher(int pubId) {
		Connection conn = null;
		Publisher pub = null;
		try {
		conn = cUtil.getConnection();
		PublisherDAO pubDAO = new PublisherDAO(conn);
		pub = pubDAO.getPublisher(pubId);
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
		return pub;
	}
	//Method to add a borrower
	public void addBorrower(Borrower bor) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BorrowerDAO borDAO = new BorrowerDAO(conn);
		borDAO.create(bor);
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
	//Method to add a branch
	public void addBranch(Branch br) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BranchDAO brDAO = new BranchDAO(conn);
		brDAO.create(br);
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
	//Method to add a publisher
	public void addPublisher(Publisher pub) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		PublisherDAO pubDAO = new PublisherDAO(conn);
		pubDAO.create(pub);
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
	//Method to add a genre
	public void addGenre(Genre gen) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		GenreDAO genDAO = new GenreDAO(conn);
		genDAO.create(gen);
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
	//Method to add a author
	public void addAuthor(Author auth) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		AuthorDAO authDAO = new AuthorDAO(conn);
		authDAO.create(auth);
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
	//Method to delete a borrower
	public void delBorrower(Borrower bor) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BorrowerDAO borDAO = new BorrowerDAO(conn);
		BookLoansDAO bklDAO = new BookLoansDAO(conn);
		borDAO.delete(bor.getCardNo());
		bklDAO.delete(bor);
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
	//Method to delete a branch
	public void delBranch(Branch br) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BranchDAO brDAO = new BranchDAO(conn);
		BookCopiesDAO bkcDAO = new BookCopiesDAO(conn);
		BookLoansDAO bklDAO = new BookLoansDAO(conn);
		brDAO.delete(br.getBranchId());
		bkcDAO.delete(br);
		bklDAO.delete(br);
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
	//Method to delete a publisher
	public void delPublisher(Publisher pub) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		PublisherDAO pubDAO = new PublisherDAO(conn);
		pubDAO.delete(pub.getId());
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
	//Method to delete a genre
	public void delGenre(Genre gen) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		GenreDAO genDAO = new GenreDAO(conn);
		BookGenresDAO bkgDAO = new BookGenresDAO(conn);
		genDAO.delete(gen.getGenreId());
		bkgDAO.delete(gen);
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
	//Method to delete a author
	public void delAuthor(Author auth) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		AuthorDAO authDAO = new AuthorDAO(conn);
		authDAO.delete(auth.getId());
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
	//Method to update a borrower
	public void updBorrower(Borrower bor) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BorrowerDAO borDAO = new BorrowerDAO(conn);
		borDAO.update(bor);
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
	//Method to update a branch
	public void updBranch(Branch br) {
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
	//Method to add a publisher
	public void updPublisher(Publisher pub) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		PublisherDAO pubDAO = new PublisherDAO(conn);
		pubDAO.update(pub);
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
	//Method to add a genre
	public void updGenre(Genre gen) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		GenreDAO genDAO = new GenreDAO(conn);
		genDAO.update(gen);
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
	//Method to add a author
	public void updAuthor(Author auth) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		AuthorDAO authDAO = new AuthorDAO(conn);
		authDAO.update(auth);
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
	//Method to get bookAuthors with a certain author
	public ArrayList<BookAuthors> getBookAuthors(Author auth) {
		ArrayList<BookAuthors> bka = new ArrayList<BookAuthors>();
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookAuthorsDAO bkaDAO = new BookAuthorsDAO(conn);
		bka = bkaDAO.getBookAuthors(auth.getId());
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
		return bka;
	}
	//method to get a single book by bookId
	public Book getBook(int bkId){
		Connection conn = null;
		Book bk = null;
		try {
		conn = cUtil.getConnection();
		BookDAO bkDAO = new BookDAO(conn);
		bk = bkDAO.getBook(bkId);
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
		return bk;
	}
	//Method to get bookGenres with a certain genre
	public ArrayList<BookGenres> getBookGenres(Genre gen) {
		ArrayList<BookGenres> bkg = new ArrayList<BookGenres>();
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookGenresDAO bkgDAO = new BookGenresDAO(conn);
		bkg = bkgDAO.getBookGenres(gen);
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
		return bkg;
	}
	//Method to delete a book by publisherId
	public void delBook(Publisher pub) {
		Connection conn = null;
		try {
		conn = cUtil.getConnection();
		BookDAO bkDAO = new BookDAO(conn);
		bkDAO.delete(pub);
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