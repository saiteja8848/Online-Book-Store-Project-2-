package com.prograd.model;

public class PurchaseDetails {

	private int genreId;
	private String genreName;
	private int bookId;
	private String bookName;
	private int quantity;
	
	public PurchaseDetails() {}

	public PurchaseDetails(int genreId, String genreName, int bookId, String bookName, int quantity) {
		this.genreId = genreId;
		this.genreName = genreName;
		this.bookId = bookId;
		this.bookName = bookName;
		this.quantity = quantity;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
