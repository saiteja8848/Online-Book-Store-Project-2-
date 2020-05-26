package com.prograd.utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.prograd.model.Book;
import com.prograd.model.CartItems;
import com.prograd.model.Genre;

public class AdminExcelOperation {

	private FileInputStream fileInputStream;
	private FileOutputStream fileOutputStream;
	private XSSFWorkbook workBook;
	private XSSFSheet workSheet;

	// Genre
	public void addGenre(Genre genre) throws FileNotFoundException, IOException {
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("Genres");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		Row row = workSheet.createRow(totalNoOfRows++);
		row.createCell(0).setCellValue(Integer.toString(genre.getGenreId()));
		row.createCell(1).setCellValue(genre.getGenreName());
		row.createCell(2).setCellValue(true);
		fileOutputStream = new FileOutputStream("resources/BookStoreData.xlsx");
		workBook.write(fileOutputStream);
		fileOutputStream.close();
		System.out.println("\nGenre Successfully added to ExcelSheet");
		fileInputStream.close();
	}

	public List<Genre> getGenres() throws FileNotFoundException, IOException {
		List<Genre> Genres = new ArrayList<>();
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("Genres");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		if (totalNoOfRows > 0) {

			for (int i = 0; i < totalNoOfRows; i++) {
				Row row = workSheet.getRow(i);
				int genreId = Integer.parseInt(row.getCell(0).toString());
				String genreName = row.getCell(1).getStringCellValue();
				boolean status = row.getCell(2).getBooleanCellValue();
				Genres.add(new Genre(genreId, genreName, status));
			}
			fileInputStream.close();
			return Genres;
		} else {
			fileInputStream.close();
			return Genres;
		}
	}

	public void removeGenre(String genreName) throws IOException {
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("Genres");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);
			if (row.getCell(1).toString().contentEquals(genreName)) {
				row.getCell(2).setCellValue(false);
			}
		}
		fileOutputStream = new FileOutputStream("resources/BookStoreData.xlsx");
		workBook.write(fileOutputStream);
		fileOutputStream.close();
		fileInputStream.close();
		System.out.println("Genre removed sucessfully from excelSheet");
	}

	// BOOK DETAILS
	public void addBook(Book book) throws FileNotFoundException, IOException {
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("Books");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		Row row = workSheet.createRow(totalNoOfRows++);
		row.createCell(0).setCellValue(Integer.toString(book.getGenreId()));
		row.createCell(1).setCellValue(Integer.toString(book.getBookId()));
		row.createCell(2).setCellValue(book.getBookName());
		row.createCell(3).setCellValue(Integer.toString(book.getPrice()));
		row.createCell(4).setCellValue(Integer.toString(book.getQuantity()));
		row.createCell(5).setCellValue(true);
		fileOutputStream = new FileOutputStream("resources/BookStoreData.xlsx");
		workBook.write(fileOutputStream);
		System.out.println("Book Successfully Write to the excelSheet");
		fileInputStream.close();
	}

	public void removeBook(String bookName) throws IOException {
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("Books");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);
			if (row.getCell(2).toString().contentEquals(bookName)) {
				row.getCell(5).setCellValue(false);
			}
		}
		fileOutputStream = new FileOutputStream("resources/BookStoreData.xlsx");
		workBook.write(fileOutputStream);
		System.out.println("Data updated sucessfully in excelSheet");
		fileInputStream.close();
	}

	public List<Book> getBooks(int gId) throws FileNotFoundException, IOException {
		List<Book> books = new ArrayList<>();
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("Books");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);
			int genreId = Integer.parseInt(row.getCell(0).toString());
			if (genreId == gId) {
				int bookId = Integer.parseInt(row.getCell(1).toString());
				String bookName = row.getCell(2).toString();
				int price = Integer.parseInt(row.getCell(3).toString());
				int count = Integer.parseInt(row.getCell(4).toString());
				boolean status = row.getCell(5).getBooleanCellValue();
				books.add(new Book(genreId, bookId, bookName, price, count, status));
			}
		}
		fileInputStream.close();
		return books;
	}

	// cart
	public void addCartItem(CartItems item) throws FileNotFoundException, IOException {
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("UserPurchaseDetails");
		if (findExistence(item) == false) {
			int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
			Row row = workSheet.createRow(totalNoOfRows++);
			row.createCell(0).setCellValue(item.getMailId());
			row.createCell(1).setCellValue(Integer.toString(item.getGenreId()));
			row.createCell(2).setCellValue(Integer.toString(item.getBookId()));
			row.createCell(3).setCellValue(item.getBookName());
			row.createCell(4).setCellValue(Integer.toString(item.getUnitPrice()));
			row.createCell(5).setCellValue(Integer.toString(item.getQuantity()));
			row.createCell(6).setCellValue(item.isPurchaseStatus());// move to checkout or not
			row.createCell(7).setCellValue(item.isRemoveStatus());// delete status
			fileOutputStream = new FileOutputStream("resources/BookStoreData.xlsx");
			workBook.write(fileOutputStream);
			fileOutputStream.close();
			System.out.println("\nCart Successfully added to ExcelSheet");
			fileInputStream.close();
		}
	}

	public boolean findExistence(CartItems item) throws IOException {
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("UserPurchaseDetails");
		boolean status = false;
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		if (totalNoOfRows > 0) {
			for (int i = 0; i < totalNoOfRows; i++) {
				Row row = workSheet.getRow(i);
				if (row.getCell(0).toString().contentEquals(item.getMailId())
						&& row.getCell(3).toString().contentEquals(item.getBookName())) {
					int q = Integer.parseInt(row.getCell(5).toString());
					int r = q + item.getQuantity();
					row.createCell(5).setCellValue(Integer.toString(r));
					status = true;
				}

			}
			fileOutputStream = new FileOutputStream("resources/BookStoreData.xlsx");
			workBook.write(fileOutputStream);
			fileInputStream.close();
			return status;
		} else
			return status;

	}

	public List<CartItems> getCartData(String mailId, boolean value, boolean remove) throws IOException {
		List<CartItems> items = new ArrayList<>();
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("UserPurchaseDetails");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);
			if (row.getCell(0).toString().contentEquals(mailId) && row.getCell(6).getBooleanCellValue() == value
					&& row.getCell(7).getBooleanCellValue() == remove) {
				CartItems item = new CartItems();
				item.setMailId(mailId);
				item.setGenreId(Integer.parseInt(row.getCell(1).toString()));
				item.setBookId(Integer.parseInt(row.getCell(2).toString()));
				item.setBookName(row.getCell(3).toString());
				item.setQuantity(Integer.parseInt(row.getCell(5).toString()));
				item.setUnitPrice(Integer.parseInt(row.getCell(4).toString()));
				item.setPurchaseStatus(row.getCell(6).getBooleanCellValue());
				item.setRemoveStatus(row.getCell(7).getBooleanCellValue());
				items.add(item);
			}

		}
		fileInputStream.close();
		return items;
	}

	public void doCheckOut(String mailId) throws IOException {
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("UserPurchaseDetails");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);
			if (row.getCell(0).toString().contentEquals(mailId) && row.getCell(7).getBooleanCellValue() == false) {
				row.getCell(6).setCellValue(true);
			}
		}
		fileOutputStream = new FileOutputStream("resources/BookStoreData.xlsx");
		workBook.write(fileOutputStream);
		fileInputStream.close();
	}

	public void resetCart() throws IOException {
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("UserPurchaseDetails");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);
			row.getCell(7).setCellValue(true);
		}
		fileOutputStream = new FileOutputStream("resources/BookStoreData.xlsx");
		workBook.write(fileOutputStream);
		fileInputStream.close();
	}

	public List<CartItems> getSalesData() throws IOException {
		List<CartItems> items = new ArrayList<>();
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("UserPurchaseDetails");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);
			if (row.getCell(7).getBooleanCellValue() == true) {
				CartItems item = new CartItems();
				item.setMailId(row.getCell(0).toString());
				item.setGenreId(Integer.parseInt(row.getCell(1).toString()));
				item.setBookId(Integer.parseInt(row.getCell(2).toString()));
				item.setBookName(row.getCell(3).toString());
				item.setQuantity(Integer.parseInt(row.getCell(5).toString()));
				item.setUnitPrice(Integer.parseInt(row.getCell(4).toString()));
				item.setPurchaseStatus(row.getCell(6).getBooleanCellValue());
				item.setRemoveStatus(row.getCell(7).getBooleanCellValue());
				items.add(item);
			}
		}
		fileInputStream.close();
		return items;
	}

	public void subscribed(String userName, String phoneNumber) throws IOException {
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("Subscribed");
		if (existence(phoneNumber) == false) {
			int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
			Row row = workSheet.createRow(totalNoOfRows++);
			row.createCell(0).setCellValue(userName);
			row.createCell(1).setCellValue(phoneNumber);
			fileOutputStream = new FileOutputStream("resources/BookStoreData.xlsx");
			workBook.write(fileOutputStream);
			fileOutputStream.close();
			fileInputStream.close();
			System.out.println("You subscribed now and receive any latest updates");
			String message = "Hi " + userName + ",You know Subscribed to Prograd Store";
			Notification.sendSms(message, phoneNumber);
		} else {
			System.out.println("You alread subsribed for Notifcations");
			// String message ="Hi" + userName +",You know Subscribed to Prograd Store";
			// Notification.sendSms(message, phoneNumber);
		}
	}

	public List<String> getSubscribedList() throws IOException {
		List<String> numbers = new ArrayList<String>();
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("Subscribed");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		Row row = workSheet.createRow(totalNoOfRows++);
		numbers.add(row.getCell(1).getStringCellValue());
		fileInputStream.close();
		return numbers;
	}

	public boolean existence(String phoneNumber) throws IOException {
		boolean status = false;
		fileInputStream = new FileInputStream("resources/BookStoreData.xlsx");
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet("Subscribed");
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);
			if (row.getCell(1).toString().contentEquals(phoneNumber)) {
				status = true;
				break;
			}
		}
		fileOutputStream = new FileOutputStream("resources/BookStoreData.xlsx");
		workBook.write(fileOutputStream);
		fileInputStream.close();
		fileOutputStream.close();
		return status;
	}

}
