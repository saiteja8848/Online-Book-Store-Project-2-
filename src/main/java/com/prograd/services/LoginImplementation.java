package com.prograd.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.prograd.model.Book;
import com.prograd.model.CartItems;
import com.prograd.model.Genre;
import com.prograd.utilites.AdminExcelOperation;
import com.prograd.utilites.ExcelOperations;


/*
 * This class details with Registered Users.
 * 1.ADMIN
 * 2.CUSTOMER
 * */


//This class takes login details on created instance based on input, if Administrator, creates instance for Administrator, like for user ,,create instance of customer 
public class LoginImplementation implements LoginInterface {
	private ExcelOperations excelOperations;
	private Scanner input = new Scanner(System.in);
	private String userName = null;
	private String phoneNumber = null;

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
				Customer customer = new Customer();
				customer.setMailID(emailId);
				customer.setUserName(userName);
				customer.setPhoneNumber(phoneNumber);
				customer.userOperations();
				break;
			}
		} else
			System.out.println("\nInvalid Details");

	}

	public boolean getData(String mailId, String role, String password) throws Exception {
		boolean status = false;
		excelOperations = new ExcelOperations();
		List<List<String>> data = excelOperations.read("resources/BookStoreData.xlsx", "RegisteredUserDetails");
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).contains(mailId) && data.get(i).contains(role) && data.get(i).contains(password)) {
				status = true;
				userName = data.get(i).get(0);
				phoneNumber = data.get(i).get(2);
				break;
			}
		}
		return status;
	}

	public boolean validate(String mailId, String password, String role) throws Exception {
		return getData(mailId, password, role);
	}

}







//Administrator class which deals  with  manipulation on the data in the excel sheet

class Admin {
	private Scanner inputReader = new Scanner(System.in);
	private String[] options = { "ADD", "UPDATE", "DELETE", "VIEW_SALES", "EXIT" };
	private AdminExcelOperation adminOperation = new AdminExcelOperation();

	public void adminOperations() throws FileNotFoundException, IOException {
		System.out.println("\nWelcome to Admin Operations\n-----------------------------");
		inputReader = new Scanner(System.in);
		System.out.print("1.ADD\n2.UPDATE\n3.DELETE\n4.VIEW_SALES\n5.Exit\nPlease Enter Your Choice:");
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
		case "EXIT":
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Option");
			break;
		}
	}

	public void Add() throws FileNotFoundException, IOException {
		String[] options = { "ADDING_GENRE", "ADDING_BOOK" };
		System.out.print("\nADDING DATA\n-----------\n1.New Genre\n2.New Book\nPlease Enter Your Choice:");
		int choice = inputReader.nextInt();
		switch (options[choice - 1]) {
		case "ADDING_GENRE":
			System.out.print("\nPlease Enter Number of genres to add:");
			int count = inputReader.nextInt();
			while (count != 0) {
				System.out.print("\nEnter genreId:");
				int genreId = inputReader.nextInt();
				inputReader.nextLine();
				System.out.print("Enter genreName:");
				String genreName = inputReader.nextLine();
				Genre genre = new Genre(genreId, genreName, true);
				adminOperation.addGenre(genre);
				count--;
			}
			this.adminOperations();
			break;
		case "ADDING_BOOK":
			System.out.print("\nPlease Enter Number of books to add:");
			count = inputReader.nextInt();
			while (count != 0) {
				System.out.print("\nEnter genreId which it belongs to:");
				int genreId = inputReader.nextInt();
				System.out.print("Enter the BookId:");
				int bookId = inputReader.nextInt();
				inputReader.nextLine();
				System.out.print("Enter book name:");
				String bookName = inputReader.nextLine();
				System.out.print("Enter book Price:");
				int price = inputReader.nextInt();
				System.out.print("Enter Quantity:");
				int quantity = inputReader.nextInt();
				System.out.println(genreId + " " + bookId + " " + bookName + " " + price + " " + quantity);
				adminOperation.addBook(new Book(genreId, bookId, bookName, price, quantity, true));
				count--;
			}
			this.adminOperations();
			break;
		default:
			System.out.println("Invalid");
			break;

		}
	}

	public void update() throws IOException {
		ExcelOperations e = new ExcelOperations();
		String fileName = "resources/BookStoreData.xlsx";
		String sheetName;
		String[] options = { "UPDATING_GENRE", "UPDATING_BOOK" };
		System.out.print(
				"\nUPDATING DATA\n------------\n1.Updating Genre Details\n2.Updating Book Details\nPlease Enter Your Choice:");
		int choice = inputReader.nextInt();
		switch (options[choice - 1]) {
		case "UPDATING_GENRE":
			inputReader.nextLine();
			System.out.print("Please Enter old data value/name:");
			String oldData = inputReader.nextLine();
			System.out.print("Please Enter new data value/name:");
			String newData = inputReader.nextLine();
			sheetName = "Genres";
			e.update(fileName, sheetName, oldData, newData);
			this.adminOperations();
			break;
		case "UPDATING_BOOK":
			inputReader.nextLine();
			System.out.print("Please Enter Book Name:");
			String bookName = inputReader.nextLine();
			System.out.print("Please Enter old data value/name:");
			oldData = inputReader.nextLine();
			System.out.print("Please Enter new data value/name:");
			newData = inputReader.nextLine();
			sheetName = "Books";
			e.update(fileName, sheetName, bookName, oldData, newData);
			this.adminOperations();
			break;
		default:
			System.out.println("Invalid");
			break;

		}
	}

	public void delete() throws IOException {
		String[] options = { "DELETING_GENRE", "DELETING_BOOK" };
		System.out.print("\nDELETING DATA\n------------\n1.Delete Genre\n2.Delete Book\nPlease Enter Your Choice:");
		int choice = inputReader.nextInt();
		switch (options[choice - 1]) {
		case "DELETING_GENRE":
			inputReader.nextLine();
			System.out.print("\nEnter genreName:");
			String genreName = inputReader.nextLine();
			adminOperation.removeGenre(genreName);
			this.adminOperations();
			break;
		case "DELETING_BOOK":
			inputReader.nextLine();
			System.out.print("\nEnter bookName:");
			String bookName = inputReader.nextLine();
			adminOperation.removeBook(bookName);
			this.adminOperations();
			break;
		default:
			System.out.println("Invalid");
			break;

		}

	}

	public void viewSales() throws IOException {
		List<CartItems> saledBooks = adminOperation.getSalesData();
		if (saledBooks.size() > 0) {
			int total = 0;
			System.out.print("\n----------BOOK SALES-----------\n");
			for (CartItems item : saledBooks) {
				System.out.println("MailId   :-" + item.getMailId());
				System.out.println("BookName :-" + item.getBookName());
				System.out.println("UnitPrice:-" + item.getUnitPrice());
				System.out.println("Quantity :-" + item.getQuantity());
				int t = (item.getQuantity() * item.getUnitPrice());
				System.out.println("TotalBill:-" + t);
				total += t;
				System.out.println();
			}
			System.out.println("\n---------TOTAL SALES:-" + total);
		} else
			System.out.println("No one Purchased the books Yet");
		this.adminOperations();
	}

}




//customer class which deals with the operations like adding books to cart, billing 
class Customer {
	private Scanner inputReader = new Scanner(System.in);
	private ExcelOperations excelOperations = new ExcelOperations();
	private AdminExcelOperation adminOperation = new AdminExcelOperation();
	private List<CartItems> itemsInCart = new ArrayList<>();
	private String mailID = null;
	private String userName = null;
	private String phoneNumber = null;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMailID() {
		return mailID;
	}

	public void setMailID(String mailID) {
		this.mailID = mailID;
	}

	public void userOperations() throws FileNotFoundException, IOException {
		String[] options = { "SHOPPING", "VIEW_CART", "CHECKOUT", "EXIT" };
		System.out.print("\n------Hi " + this.getUserName() + "-------\n");
		System.out.print("\nWelcome To User Operations\n--------------------------");
		System.out.print("\n1.Store\n2.ViewCart\n3.Billing\n4.Exit\nPlease Enter Your Choice:");
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
		case "EXIT":
			System.out.println("\n------Thank You for Visting the Store,Have a Nice Day!-----");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid");
			break;
		}
	}

	public void shopping() throws FileNotFoundException, IOException {
		List<Genre> genres = adminOperation.getGenres();
		System.out.println("\nId GenreName\n------------");
		genres.stream().forEach(genre -> genre.displayGenreDetails());
		System.out.print("Please enter your choice:(enter -1 to return back):");
		int genreChoice = inputReader.nextInt();
		if (genreChoice == -1) {
			this.userOperations();
		}
		List<Book> books = adminOperation.getBooks(genreChoice);
		if (books.size() > 0) {
			System.out.println("\nId BookName\n-----------");
			books.stream().filter(book -> book.getGenreId() == genreChoice).forEach(book -> book.display());
			System.out.print("Please enter your choice:(enter -1 to return back):");
			int bookChoice = inputReader.nextInt();
			if (bookChoice == -1) {
				this.shopping();
			}
			System.out.print("Please enter quantity:(less than or equal to 10):");
			int quantity = inputReader.nextInt();
			if (quantity <= 10)
				addToCart(genreChoice, bookChoice, books, quantity);
			else
				System.out.println("Limit Exceed,Stock is not avaliable");
		} else {
			System.out.println("\nBooks are not Avaliable");
			this.userOperations();
		}

	}

	public void addToCart(int genreId, int bookId, List<Book> books, int quantity)
			throws FileNotFoundException, IOException {
		CartItems item = new CartItems();
		Book purchasedBook = null;
		for (Book book : books) {
			if (book.getBookId() == bookId && book.getGenreId() == genreId) {
				purchasedBook = book;
				break;
			}
		}
		item.setPurchaseStatus(false);
		item.setRemoveStatus(false);
		item.setBookId(bookId);
		item.setBookName(purchasedBook.getBookName());
		item.setGenreId(genreId);
		item.setMailId(this.getMailID());
		item.setQuantity(quantity);
		item.setUnitPrice(purchasedBook.getPrice());
		adminOperation.addCartItem(item);
		System.out.println("\n-------Book added to the Cart Successfully----!");
		this.userOperations();
	}

	public void viewCart() throws FileNotFoundException, IOException {
		System.out.println("\nItems in the Cart\n-----------------");
		itemsInCart = adminOperation.getCartData(this.getMailID(), false, false);
		if (itemsInCart.size() > 0) {
			for (CartItems item : itemsInCart) {
				item.display();
			}
		} else
			System.out.println("No books are added to cart");
		this.userOperations();
	}

	public void checkOut() throws FileNotFoundException, IOException {
		adminOperation.doCheckOut(this.getMailID());
		itemsInCart = adminOperation.getCartData(this.getMailID(), true, false);
		if (itemsInCart.size() > 0) {
			int totalPrice = 0;
			System.out.print("\nCheckout Details\n---------------");
			for (CartItems item : itemsInCart) {
				item.display();
				totalPrice += (item.getQuantity() * item.getUnitPrice());
			}
			System.out.println("Total Price:" + totalPrice);
		} else
			System.out.println("---------No items in the cart to checkout---------!");
		adminOperation.resetCart();
		this.userOperations();

	}

	public void alertMe() throws IOException {
		adminOperation.subscribed(this.userName, this.phoneNumber);
		this.userOperations();
	}

}