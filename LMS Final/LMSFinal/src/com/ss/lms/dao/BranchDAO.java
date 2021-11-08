// @author TylerRondeau
// Data Access Object for pulling data from the libraryBranch table in our Library database
package com.ss.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.lms.entity.Branch;

public class BranchDAO extends BaseDAO<Branch>{
	
	//BranchDAO constructor
	public BranchDAO(Connection conn) {
		super(conn);
	}
	//Method to get all branches
	public ArrayList<Branch> getBranches() throws ClassNotFoundException, SQLException{
		return read("SELECT branchId, branchName, branchAddress FROM tbl_library_branch", null);
	}
	//Method to delete a branch
	public void delete(int brId) throws ClassNotFoundException, SQLException {
		exe("DELETE FROM tbl_library_branch WHERE branchId = ?", new Object[] {brId});
	}
	//Method to update a branch
	public void update(Branch br) throws ClassNotFoundException, SQLException {
		exe("UPDATE tbl_library_branch SET branchName = ?,branchAddress=? WHERE branchId=?",new Object[] {br.getBranchName(),br.getBranchAddress(),br.getBranchId()});
	}
	//Method to create a branch
	public void create(Branch br) throws ClassNotFoundException,SQLException{
		exe("INSERT INTO tbl_library_branch (branchId, branchName, branchAddress) VALUES (?, ?, ?)",new Object[] {br.getBranchId(),br.getBranchName(),br.getBranchAddress()});
	}
	//Method to get one branch
	public Branch getBranch(int brId) throws ClassNotFoundException, SQLException {
		ArrayList<Branch> branch = read("SELECT branchId,branchName,branchAddress FROM tbl_library_branch WHERE branchId = ?",new Object[] {brId});
		return branch.get(0);
	}
	//Method to extract data from a ResultSet into an ArrayList<Branch>
	@Override
	protected ArrayList<Branch> extractData(ResultSet rs) throws SQLException {
		ArrayList<Branch> branches = new ArrayList<Branch>();
		while(rs.next()) {
			int id = rs.getInt("branchId");
			String name = rs.getString("branchName");
			String address = rs.getString("branchAddress");
			Branch branch = new Branch(id, name, address);
			branches.add(branch);
		}
		rs.close();
		return branches;
	}
}