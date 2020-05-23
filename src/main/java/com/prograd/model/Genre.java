package com.prograd.model;

import java.util.List;

public class Genre {
	private String genreName;
	private boolean status;
	private int genreId;

	public Genre(int genreId, String genreName, boolean status) {
		this.genreId = genreId;
		this.genreName = genreName;
		this.status = status;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public void displayGenreDetails() {
       if(this.status)
		System.out.println(this.genreId + "." + this.genreName);
	}

}
