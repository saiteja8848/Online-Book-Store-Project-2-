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
import com.prograd.model.Genre;

public class ExcelOperations {

	private FileInputStream fileInputStream;
	private FileOutputStream fileOutputStream;
	private XSSFWorkbook workBook;
	private XSSFSheet workSheet;

	// Appending Data to the file
	public void write(String fileName, String sheetName, List<List<String>> data) throws IOException {
		fileInputStream = new FileInputStream(fileName);
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet(sheetName);
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < data.size(); i++) {
			Row row = workSheet.createRow(totalNoOfRows++);
			for (int j = 0; j < data.get(i).size(); j++) {
				row.createCell(j).setCellValue(data.get(i).get(j));
			}
		}
		fileOutputStream = new FileOutputStream(fileName);
		workBook.write(fileOutputStream);
		fileOutputStream.close();
		fileInputStream.close();
		System.out.println("\nYour Account is Successfully Registered");
	}

	public List<List<String>> read(String fileName, String sheetName) throws IOException {
		fileInputStream = new FileInputStream(fileName);
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet(sheetName);
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		List<List<String>> main = new ArrayList<>();

		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);
			List<String> data = new ArrayList<>();
			for (int j = 0; j < row.getLastCellNum(); j++) {
				data.add(row.getCell(j).toString());
			}
			main.add(data);
		}
		fileInputStream.close();
		return main;
	}

	public void display(String fileName, String sheetName) throws FileNotFoundException, IOException {
		fileInputStream = new FileInputStream(fileName);
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet(sheetName);
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		System.out.println("Data in the Excel Sheet is:");
		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				System.out.print(row.getCell(j) + " ");
			}
			System.out.println();
		}
		fileInputStream.close();
	}

	public void update(String fileName, String sheetName, String oldData, String newData) throws IOException {
		workBook = new XSSFWorkbook(new FileInputStream(fileName));
		workSheet = workBook.getSheet(sheetName);
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				if (row.getCell(j).toString().contentEquals(oldData)) {
					row.getCell(j).setCellValue(newData);
				}
			}
		}
		fileOutputStream = new FileOutputStream(fileName);
		workBook.write(fileOutputStream);
		System.out.println("Data updated sucessfully in excelSheet");
	}

	public void update(String fileName, String sheetName, String searchValue, String oldData, String newData)
			throws IOException {
		workBook = new XSSFWorkbook(new FileInputStream(fileName));
		workSheet = workBook.getSheet(sheetName);
		int totalNoOfRows = workSheet.getPhysicalNumberOfRows();
		for (int i = 0; i < totalNoOfRows; i++) {
			Row row = workSheet.getRow(i);

			if (row.getCell(2).toString().contentEquals(searchValue)) {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					if (row.getCell(j).toString().contentEquals(oldData)) {
						row.getCell(j).setCellValue(newData);
					}
				}
			}

		}
		fileOutputStream = new FileOutputStream(fileName);
		workBook.write(fileOutputStream);
		System.out.println("Data updated sucessfully in excelSheet");
	}

}
