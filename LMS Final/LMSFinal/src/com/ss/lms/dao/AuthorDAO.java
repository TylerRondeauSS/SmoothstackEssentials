// @author TylerRondeau
// Data Access Object for pulling data from the author table in our Library database
package com.ss.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.Author;

public class AuthorDAO extends BaseDAO<Author>{
	
	//AuthorDAO constructor
	public AuthorDAO(Connection conni) {
		super(conni);
	}
	//Method to get all Authors
	public ArrayList<Author> getAuthors() throws ClassNotFoundException, SQLException{
		return read("SELECT authorId, authorName FROM tbl_author", null);
	}
	//Method to delete an Author
	public void delete(int authId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_author WHERE authorId = ?", new Object[] {authId});
	}
	//Method to update a Author
	public void update(Author auth) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_author SET authorName=? WHERE authorId=?",new Object[] {auth.getName(),auth.getId()});
	}
	//Method to create a Author
	public void create(Author auth) throws ClassNotFoundException,SQLException{
		exe("INSERT INTO tbl_author (authorId, authorName) VALUES (?, ?)",new Object[] {auth.getId(),auth.getName()});
	}
	//Method to get one Author
	public Author getAuthor(int authId) throws ClassNotFoundException, SQLException{
		ArrayList<Author> auth = read("SELECT authorId, authorName FROM tbl_author WHERE authorId = ?", new Object[] {authId});
		return auth.get(0);
	}
	//Method to convert ResultSet into ArrayList<Author>
	@Override
	protected ArrayList<Author> extractData(ResultSet rs) throws SQLException {
		ArrayList<Author> auths = new ArrayList<Author>();
		while(rs.next()) {
			int authId = rs.getInt("authorId");
			String name = rs.getString("authorName");
			Author auth = new Author(authId,name);
			auths.add(auth);
		}
		rs.close();
		return auths;
	}
}