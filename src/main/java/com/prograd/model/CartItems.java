package com.prograd.model;

public class CartItems {

	private String mailId;
	private int genreId;
	private int bookId;
	private String bookName;
	private int unitPrice;
	private int quantity;
	private boolean removeStatus;
	private boolean purchaseStatus;
	
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
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
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isRemoveStatus() {
		return removeStatus;
	}
	public void setRemoveStatus(boolean removeStatus) {
		this.removeStatus = removeStatus;
	}
	public boolean isPurchaseStatus() {
		return purchaseStatus;
	}
	public void setPurchaseStatus(boolean purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}
	

	public void display() {
		System.out.println("\n"+this.bookName+"    "+this.unitPrice+"     "+this.quantity+"     "+(this.unitPrice*this.quantity));
	}
	
	
	
	
}
