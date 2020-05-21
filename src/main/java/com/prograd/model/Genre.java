package com.prograd.model;

import java.util.List;

public class Genre {
	private String genreName;
	private List<Book> books;
	private int genreId;

	public Genre(int genreId,String genreName, List<Book> books) {
		this.genreId=genreId;
		this.genreName = genreName;
		this.books = books;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public void displayGenreDetails() {
		System.out.println(this.genreId+"."+this.genreName);
	}
	
	public void displayBooksUnderGenre() {
		for(Book book:books) {
			System.out.println(book.getBookId()+"."+book.getBookName());
		}
	}
	

}
