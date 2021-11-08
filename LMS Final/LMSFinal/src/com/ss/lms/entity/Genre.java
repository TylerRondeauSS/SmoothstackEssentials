package com.ss.lms.entity;

public class Genre {
	private int genreId;
	private String genreName;
	
	//Genre Constructor
	public Genre(int genreId, String genreName) {
		this.genreId = genreId;
		this.genreName = genreName;
	}
	//get and set Id
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	//get and set name
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}