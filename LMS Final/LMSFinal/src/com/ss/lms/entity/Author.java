//@author: TylerRondeau
//Object representing the author table of the library database
package com.ss.lms.entity;

public class Author {
	private int id;
	private String name;
	
	//Author constructor
	public Author(int id, String name) {
		setId(id);
		setName(name);
	}
	//get and set Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//get and set name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}