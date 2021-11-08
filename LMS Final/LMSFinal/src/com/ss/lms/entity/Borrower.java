//@author: TylerRondeau
//Object representing the borrower table of the library database
package com.ss.lms.entity;

public class Borrower {
	private int cardNo;
	private String name,address,phone;
	
	//Borrower constructor
	public Borrower(int cardNum, String name, String address, String phone) {
		setCardNo(cardNum);
		setName(name);
		setAddress(address);
		setPhone(phone);
	}
	//get and set cardNo
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNum) {
		this.cardNo = cardNum;
	}
	//get and set name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//get and set address
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	//get and set phone
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}