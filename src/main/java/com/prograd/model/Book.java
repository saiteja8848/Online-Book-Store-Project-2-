package com.prograd.model;

public class Book {

	private int bookId;
	private String bookName;
	private int price;
	private int quantity;
	private boolean avaliablity;

	public Book(int bookId,String bookName, int price, int quantity, boolean avaliablity) {
		this.bookId=bookId;
		this.bookName = bookName;
		this.price = price;
		this.quantity = quantity;
		this.avaliablity = avaliablity;
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

	public boolean isAvaliablity() {
		return avaliablity;
	}

	public void setAvaliablity(boolean avaliablity) {
		this.avaliablity = avaliablity;
	}

}
