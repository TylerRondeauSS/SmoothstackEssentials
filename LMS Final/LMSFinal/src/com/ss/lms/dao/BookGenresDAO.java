// @author TylerRondeau
// Data Access Object for pulling data from the bookGenres table in our Library database
package com.ss.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.BookGenres;
import com.ss.lms.entity.Genre;

public class BookGenresDAO extends BaseDAO<BookGenres>{
	
	//BookGenresDAO constructor
	public BookGenresDAO(Connection conni) {
		super(conni);
	}
	//Method to get all BookGenres
	public ArrayList<BookGenres> getBookGenres() throws ClassNotFoundException, SQLException{
		return read("SELECT genre_id, bookId FROM tbl_book_genres", null);
	}
	//Method to get all BookGenres with a certain genre
	public ArrayList<BookGenres> getBookGenres(Genre gen) throws ClassNotFoundException, SQLException{
		return read("SELECT genre_id, bookId FROM tbl_book_genres WHERE genre_id = ?", new Object[] {gen.getGenreId()});
	}
	//Method to delete a BookGenres
	public void delete(int genId, int bkId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_genres WHERE bookId = ? AND genre_id = ?", new Object[] {bkId,genId});
	}
	//Method to delete a BookGenres by only bookId
	public void delete(int bkId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_genres WHERE bookId = ? ", new Object[] {bkId});
	}
	//Method to delete a BookGenres by only genreId
	public void delete(Genre gen) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_book_genres WHERE genre_id = ? ", new Object[] {gen.getGenreId()});
	}
	//Method to update a BookGenres' genre
	public void updateGenre(BookGenres bkGen) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_book_genres SET genre_id=? WHERE bookId=?",new Object[] {bkGen.getGenreId(),bkGen.getBookId()});
	}
	//Method to update a BookGenres' book
	public void updateBook(BookGenres bkGen) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_book_genres SET bookId=? WHERE genre_id=?",new Object[] {bkGen.getBookId(),bkGen.getGenreId()});
	}
	//Method to create a BookGenre
	public void create(BookGenres bkGen) throws ClassNotFoundException,SQLException{
		exe("INSERT INTO tbl_book_genres (genre_id, bookId) VALUES (?, ?)",new Object[] {bkGen.getGenreId(),bkGen.getBookId()});
	}
	//Method to get one BookGenre
	public BookGenres getBookGenre(int bkId, int genId) throws ClassNotFoundException, SQLException{
		ArrayList<BookGenres> bookGen = read("SELECT bookId, genre_id FROM tbl_book_genres WHERE bookId = ? AND genreid = ?", new Object[] {bkId,genId});
		return bookGen.get(0);
	}
	//Method to get one BookGenre with only bookId
	public BookGenres getBookGenre(int bkId) throws ClassNotFoundException, SQLException{
		ArrayList<BookGenres> bookGen = read("SELECT bookId, genre_id FROM tbl_book_genres WHERE bookId = ?", new Object[] {bkId});
		return bookGen.get(0);
	}
	//Method to convert ResultSet into ArrayList<BookGenres>
	@Override
	protected ArrayList<BookGenres> extractData(ResultSet rs) throws SQLException {
		ArrayList<BookGenres> bookGenres = new ArrayList<BookGenres>();
		while(rs.next()) {
			int bkId = rs.getInt("bookId");
			int genId = rs.getInt("genre_id");
			BookGenres book = new BookGenres(genId,bkId);
			bookGenres.add(book);
		}
		rs.close();
		return bookGenres;
	}
}