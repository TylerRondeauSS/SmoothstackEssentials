//@author: TylerRondeau
//Object representing the branch table of the library database
package com.ss.lms.entity;

public class Branch {
	private int branchId;
	private String branchName,branchAddress;
	
	//Branch Constructor
	public Branch(int branchId, String branchName, String branchAddress) {
		setBranchId(branchId);
		setBranchName(branchName);
		setBranchAddress(branchAddress);
	}
	//get and set branchId
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	//get and set branchName
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	//get and set branchAddress
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
}