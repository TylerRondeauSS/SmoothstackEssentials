// @author TylerRondeau
// Data Access Object for pulling data from the borrower table in our Library database
package com.ss.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower>{
	
	//BorrowerDAO constructor
	public BorrowerDAO(Connection conn) {
		super(conn);
	}
	//Method to get all borrowers
	public ArrayList<Borrower> getBorrowers() throws ClassNotFoundException, SQLException{
		return read("SELECT cardNo, name, address,phone FROM tbl_borrower GROUP BY cardNO", null);
	}
	//Method to delete a borrower
	public void delete(int cardNo) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_borrower WHERE cardNo=?", new Object[] {cardNo});
	}
	//Method to update a borrower
	public void update(Borrower bor) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_borrower SET name = ?,address=?,phone=? WHERE cardNo=?",new Object[] {bor.getName(),bor.getAddress(),bor.getPhone(),bor.getCardNo()});
	}
	//Method to create a Borrower
	public void create(Borrower bor) throws ClassNotFoundException,SQLException{
		exe("INSERT INTO tbl_borrower (name, address, phone, cardNo) VALUES (?, ?, ?,?)",new Object[] {bor.getName(),bor.getAddress(),bor.getPhone(),bor.getCardNo()});
	}
	//Method to get one borrower
	public Borrower getBorrower(int cardNo) throws ClassNotFoundException, SQLException {
		ArrayList<Borrower> borrower = read("SELECT cardNo, name, address,phone FROM tbl_borrower WHERE cardNo=?",new Object[] {cardNo});
		return borrower.get(0);
	}
	//Method to extract data from a ResultSet into an ArrayList<Branch>
	@Override
	protected ArrayList<Borrower> extractData(ResultSet rs) throws SQLException {
		ArrayList<Borrower> borrowers = new ArrayList<Borrower>();
		while(rs.next()) {
			int cardNo = rs.getInt("cardNo");
			String name = rs.getString("name");
			String address = rs.getString("address");
			String phone = rs.getString("phone");
			Borrower b = new Borrower(cardNo,name,address,phone);
			borrowers.add(b);
		}
		rs.close();
		return borrowers;
	}
}