package com.prograd.model;

import java.util.List;

public class Cart {

	private List<PurchaseDetails> details;

	public List<PurchaseDetails> getDetails() {
		return details;
	}

	public void setDetails(List<PurchaseDetails> details) {
		this.details = details;
	}

	public void display() {
		    System.out.println("GenreName        BookName      Quantity");
		for(PurchaseDetails detail: details) {
			System.out.println(detail.getGenreName()+"    "+detail.getBookName()+"    "+detail.getQuantity());
		}
	}
}
