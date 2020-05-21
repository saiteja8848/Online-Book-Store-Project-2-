package com.prograd.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.prograd.model.Book;
import com.prograd.model.Cart;
import com.prograd.model.Genre;
import com.prograd.model.PurchaseDetails;
import com.prograd.model.User;
import com.prograd.utilites.ExcelOperations;

public class LoginImplementation implements LoginInterface {

	private ExcelOperations excelOperations;
	private Scanner input = new Scanner(System.in);

	public void start() throws Exception {
		String[] options = { "admin", "customer" };
		String emailId = null;
		String password = null;
		System.out.print("\nLogin As\n--------\n1.ADMIN\n2.CUSTOMER\nplease enter your choice:");
		int choice = input.nextInt();
		input.nextLine();
		if (choice == 1 || choice == 2) {
			System.out.print("\nPlease Enter Your Registerd EmailID:");
			emailId = input.nextLine();
			System.out.print("Please Enter Your Registered Password:");
			password = input.nextLine();
		} else {
			System.out.println("Invalid Option");
		}

		if (validate(emailId, password, options[choice - 1])) {
			switch (options[choice - 1]) {
			case "admin":
				new Admin().adminOperations();
				break;
			case "customer":
				new Customer().userOperations();
				break;
			}
		}

	}

	public Map<String, String> getData(String mailId,String role) throws Exception {
		Map<String, String> map = new HashMap<>();
		excelOperations = new ExcelOperations("resources/BookStoreData.xlsx", "RegisteredUsers");
		List<User> usersList = excelOperations.readData();
		System.out.println(usersList.size());
		map=usersList.stream().filter(user->user.getEmailId().contentEquals(mailId)&&user.getRole().contentEquals(role)).collect(Collectors.toMap(User::getEmailId,User::getPassword));
		System.out.println("Map:"+map);
		return map;
	}

	public boolean validate(String mailId, String password, String role) {
		boolean status = false;
		try {
			Map<String, String> map1 = new HashMap<>();
			map1.put(mailId, password);
			Map<String, String> map2 =  getData(mailId,role);	
			if (map1.equals(map2)) {
				status = true;
			} else
				throw new Exception();

		} catch (Exception e) {
			System.out.println("Invalid Creditionals");
		}

		return status;
	}

}

class Admin {
	private Scanner inputReader;
	private String[] options = { "ADD", "UPDATE", "DELETE", "VIEW_SALES" };

	public void adminOperations() {
		System.out.println("\nWelcome to Admin Operations\n-----------------------------");
		inputReader = new Scanner(System.in);
		System.out.print("1.ADD\n2.UPDATE\n3.DELETE\nPlease Enter Your Choice:");
		int choice = inputReader.nextInt();
		switch (options[choice - 1]) {
		case "ADD":
			Add();
			break;
		case "UPDATE":
			update();
			break;
		case "DELETE":
			delete();
			break;
		case "VIEW_SALES":
			viewSales();
			break;
		}
	}

	public void Add() {
		System.out.println("\nADDING DATA\n-----------\n1.New Genre\n2.New Book\nPlease Enter Your Choice:");
	}

	public void update() {
		System.out.println("\nUPDATING DATA\n------------\n1.Update Genre Details\n2.Book Details\nPlease Enter Your Choice:");
	}

	public void delete() {
		System.out.println("\nDELETING DATA\n------------\n1.Delete Genre\n2.Delete Book\nPlease Enter Your Choice:");
	}

	public void viewSales() {
		System.out.println("Which User Purchased What ?");
	}

}



class Customer {
	private Scanner inputReader = new Scanner(System.in);
	private ExcelOperations excelOperations = new ExcelOperations();
	private List<PurchaseDetails> purchaseDetails = new ArrayList<>();
	private Cart cart = new Cart();

	public void userOperations() {
		String[] options = { "SHOPPING", "VIEW_CART", "CHECKOUT", "ALERT_ME", "EXIT" };
		System.out.print("\nWelcome To User Operations\n-----------------------");
		System.out.print("\n1.Store\n2.ViewCart\n3.Checkout\n4.Notify Me\n5.Exit\nPlease Enter Your Choice:");
		int choice = inputReader.nextInt();
		switch (options[choice - 1]) {
		case "SHOPPING":
			shopping();
			break;
		case "VIEW_CART":
			viewCart();
			break;
		case "CHECKOUT":
			checkOut();
			break;
		case "ALERT_ME":
			alertMe();
			break;
		case "EXIT":
			System.out.println("Thank You for Visting the Store,Have a Nice Day!");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid");
			break;
		}
	}

	public void shopping() {
		System.out.println("\nGenres\n-------");
		List<Genre> Genres = excelOperations.loadData();
		for (Genre genre : Genres) {
			genre.displayGenreDetails();
		}
		System.out.print("Please Enter Your Choice(-1 to return shop):");
		int genreChoice = inputReader.nextInt();
		if (genreChoice == -1) {
			userOperations();
		}
		System.out.println("\nBooks\n------");
		Genres.get(genreChoice - 1).displayBooksUnderGenre();
		System.out.print("Please Enter Your Choice:");
		int bookChoice = inputReader.nextInt();
		System.out.print("Please Enter the Quantity to Purchase:");
		int quantity = inputReader.nextInt();
		addToCart(Genres.get(genreChoice - 1), bookChoice - 1, quantity);
	}

	public void addToCart(Genre genre, int bookId, int quantity) {
		Book book = genre.getBooks().get(bookId);
		if (quantity <= book.getQuantity()) {
			PurchaseDetails purchaseABook = new PurchaseDetails();
			purchaseABook.setGenreId(genre.getGenreId());
			purchaseABook.setGenreName(genre.getGenreName());
			purchaseABook.setBookId(bookId + 1);
			purchaseABook.setBookName(book.getBookName());
			purchaseABook.setQuantity(quantity);
			purchaseDetails.add(purchaseABook);
			cart.setDetails(purchaseDetails);
			System.out.println("\nBook added to the cart Successfully");
			userOperations();
		} else
			System.out.println("out of stock");
	}

	public void viewCart() {
		if (cart.getDetails() == null || cart.getDetails().size() <= 0) {
			System.out.println("\nNo Books are added to Your Cart");
		} else {
			System.out.println("\nBooks in the Cart\n-----------------");
			cart.display();
		}
		userOperations();
	}

	public void checkOut() {
		System.out.println("Checkout, ");
		cart.getDetails().clear();
		userOperations();

	}

	public void alertMe() {

	}

}