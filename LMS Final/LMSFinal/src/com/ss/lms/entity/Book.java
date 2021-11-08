//@author: TylerRondeau
//Object representing the book table of the library database
package com.ss.lms.entity;

public class Book {
	private int id,publishId;
	private String title;
	
	//Book constructor
	public Book(int id, String titleName, int publishId) {
		setId(id);
		setPubId(publishId);
		setTitle(titleName);
	}
	//get and set Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//get and set publishId
	public int getPublishId() {
		return publishId;
	}
	public void setPubId(int publishId) {
		this.publishId = publishId;
	}
	//get and set title
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}