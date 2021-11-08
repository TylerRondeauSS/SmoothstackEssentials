//@author: TylerRondeau
//Object representing the publisher table of the library database
package com.ss.lms.entity;

public class Publisher {
	private int publisherId;
	private String name,address,phoneNumber;
	
	//Publisher Constructor
	public Publisher(int publisherId, String name, String address, String phoneNumber) {
		setId(publisherId);
		setName(name);
		setAddress(address);
		setPhoneNumber(phoneNumber);
	}
	//Get and set Id
	public int getId() {
		return publisherId;
	}
	public void setId(int publisherId) {
		this.publisherId = publisherId;
	}
	//Get and set Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//Get and set Address
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	//Get and set phone number
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}