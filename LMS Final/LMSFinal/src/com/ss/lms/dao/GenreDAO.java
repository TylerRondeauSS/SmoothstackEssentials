// @author TylerRondeau
// Data Access Object for pulling data from the genre table in our Library database
package com.ss.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.Genre;

public class GenreDAO extends BaseDAO<Genre>{
	
	//Genre DAO constructor
	public GenreDAO(Connection conni) {
		super(conni);
	}
	//Method to get all Books
	public ArrayList<Genre> getGenres() throws ClassNotFoundException, SQLException{
		return read("SELECT genre_id, genre_name FROM tbl_genre", null);
	}
	//Method to delete a Book
	public void delete(int genId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[] {genId});
	}
	//Method to update a Book
	public void update(Genre gen) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_genre SET genre_name=? WHERE genre_id=?",new Object[] {gen.getGenreName(),gen.getGenreId()});
	}
	//Method to create a Book
	public void create(Genre gen) throws ClassNotFoundException,SQLException{
		exe("INSERT INTO tbl_genre (genre_id,genre_name) VALUES (?, ?)",new Object[] {gen.getGenreId(),gen.getGenreName()});
	}
	//Method to get one Books
	public Genre getGenre(int genId) throws ClassNotFoundException, SQLException{
		ArrayList<Genre> genre = read("SELECT genre_id, genre_name FROM tbl_genre WHERE genre_id = ?", new Object[] {genId});
		return genre.get(0);
	}
	//Method to convert ResultSet into ArrayList<Genre>
	@Override
	protected ArrayList<Genre> extractData(ResultSet rs) throws SQLException {
		ArrayList<Genre> genres = new ArrayList<Genre>();
		while(rs.next()) {
			int genId = rs.getInt("genre_id");
			String name = rs.getString("genre_name");
			Genre genre = new Genre(genId,name);
			genres.add(genre);
		}
		rs.close();
		return genres;
	}
}