// @author TylerRondeau
// Data Access Object for pulling data from the publisher table in our Library database
package com.ss.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher>{

	//publisherDAO constructor
	public PublisherDAO(Connection conni) {
		super(conni);
	}
	//Method to get all Publishers
	public ArrayList<Publisher> getPublishers() throws ClassNotFoundException, SQLException{
		return read("SELECT publisherId,publisherName,publisherAddress,publisherPhone FROM tbl_publisher", null);
	}
	//Method to delete a Publisher
	public void delete(int pubId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_publisher WHERE publisherId=?", new Object[] {pubId});
	}
	//Method to update a Publisher
	public void update(Publisher pub) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_publisher SET publisherName=?,publisherAddress=?,publisherPhone=? WHERE publisherId=?",
				new Object[] {pub.getName(),pub.getAddress(),pub.getPhoneNumber(),pub.getId()});
	}
	//Method to create a Publisher
	public void create(Publisher pub) throws ClassNotFoundException,SQLException{
		exe("INSERT INTO tbl_publisher (publisherId,publisherName,publisherAddress,publisherPhone) VALUES (?, ?, ?,?)",
				new Object[] {pub.getId(),pub.getName(),pub.getAddress(),pub.getPhoneNumber()});
	}
	//Method to get one Publisher
	public Publisher getPublisher(int pubId) throws ClassNotFoundException, SQLException{
		ArrayList<Publisher> pub = read("SELECT publisherId,publisherName,publisherAddress,publisherPhone FROM tbl_publisher WHERE publisherId=?", new Object[] {pubId});
		return pub.get(0);
	}
	//Method to convert ResultSet into ArrayList<Publisher>
	@Override
	protected ArrayList<Publisher> extractData(ResultSet rs) throws SQLException {
		ArrayList<Publisher> pubs = new ArrayList<Publisher>();
		while(rs.next()) {
			int pubId = rs.getInt("publisherId");
			String pubName = rs.getString("publisherName");
			String pubAdd = rs.getString("publisherAddress");
			String pubPh = rs.getString("publisherPhone");
			Publisher pub = new Publisher(pubId,pubName,pubAdd,pubPh);
			pubs.add(pub);
		}
		rs.close();
		return pubs;
	}
}