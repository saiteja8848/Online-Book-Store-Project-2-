package com.prograd.model;

public class Book {

	private int genreId; 
	private int bookId;
	private String bookName;
	private int price;
	private int quantity;
	private boolean status;

	public Book(int genreId,int bookId,String bookName, int price, int quantity, boolean status) {
		this.genreId=genreId;
		this.bookId=bookId;
		this.bookName = bookName;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
	}

	public String getBookName() {
		return bookName;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void display() {
		if(this.status) 
			System.out.println(bookId+"."+bookName);
		
		
	}

}
