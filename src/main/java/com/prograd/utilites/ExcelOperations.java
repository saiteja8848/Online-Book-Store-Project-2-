package com.prograd.utilites;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.prograd.model.Book;
import com.prograd.model.Genre;
import com.prograd.model.User;

public class ExcelOperations {

	private FileInputStream fileInputStream;
	private FileOutputStream fileOutStream;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private String fileName;
	private String sheetName;

	public ExcelOperations() {}
	
	public ExcelOperations(String fileName, String sheetName) throws IOException {
		this.fileName = fileName;
		this.sheetName = sheetName;
		fileInputStream = new FileInputStream(fileName);
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetName);
	}

	public List<User> readData() {
		System.out.println("entered"+sheet.getPhysicalNumberOfRows());
		
		Iterator<Row> rowIterator = sheet.iterator();
		List<User> userList = new ArrayList<>();
		while (rowIterator.hasNext()) {
			if(!rowIterator.hasNext()){
				break;
			}
			Row row = rowIterator.next();
			User user = new User();
			user.setFullName(row.getCell(0).getStringCellValue());
			user.setEmailId(row.getCell(1).getStringCellValue());
			user.setPhoneNumber(row.getCell(2).getStringCellValue());
			user.setRole(row.getCell(3).getStringCellValue());
			user.setPassword(row.getCell(4).getStringCellValue());
			userList.add(user);
		}
		return userList;

	}

	public void writeData(String name, String emailId, String phoneNumber,String role, String password) throws IOException {
		int rowSize = sheet.getPhysicalNumberOfRows() + 1;
		//System.out.println("ROWSIZE:" +rowSize);
		Row row = sheet.createRow(rowSize++);
		row.createCell(0).setCellValue(name);
		row.createCell(1).setCellValue(emailId);
		row.createCell(2).setCellValue(phoneNumber);
		row.createCell(3).setCellValue(role);
		row.createCell(4).setCellValue(password);
		fileOutStream = new FileOutputStream(fileName);
		workbook.write(fileOutStream);
		fileOutStream.close();
		System.out.println("\n\tYour Successfully Registered");
	}

	public List<Genre> loadData() {
	    List<Genre> Genres = new ArrayList<>();
		//Biography books
		List<Book> Books1 = new ArrayList<>();
        Books1.add(new Book(1,"Steve-Jobs",250,10,true));
        Books1.add(new Book(2,"Nelson-Mandal",200,10,true));
        Books1.add(new Book(3,"AbudulKalam",100,10,true));
        Genre Biography = new Genre(1,"Bio-Graphy",Books1);
        
        //comedy
        List<Book> Books2 = new ArrayList<>();
        Books2.add(new Book(1,"Good-Omens",250,10,true));
        Books2.add(new Book(2,"Deep thoughts",200,10,true));
        Books2.add(new Book(3,"Cruel Shoes",100,10,true));
        Genre Comedy = new Genre(2,"Comedy",Books2);
        
        //Fantasy
        List<Book> Books3 = new ArrayList<>();
        Books3.add(new Book(1,"Good-Omens",250,10,true));
        Books3.add(new Book(2,"Deep thoughts",200,10,true));
        Books3.add(new Book(3,"Cruel Shoes",100,10,true));
        Genre fantasy = new Genre(3,"Fantasy",Books3);
       
        Genres.add(Biography);
        Genres.add(Comedy);
        Genres.add(fantasy);
        return Genres;
        
	}
	

	
	

}
