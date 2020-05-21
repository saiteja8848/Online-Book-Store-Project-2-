package com.prograd.services;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.prograd.model.User;
import com.prograd.utilites.ExcelOperations;

public class SignUpImplementation implements SignUpInterface {

	private ExcelOperations excelOperations;
	private Scanner input = new Scanner(System.in);

	public void start() throws IOException {
		System.out.println("\nPlease Enter the Following Details\n----------------------------------");
		System.out.print("Enter your Full Name:");
		String name = input.nextLine();
		System.out.print("Enter your emailId:");
		String emailId = input.nextLine();
		System.out.print("Enter your Mobile Name:");
		String phoneNumber = input.nextLine();
		System.out.print("Enter your role(admin/customer):");
		String role = input.nextLine();
		System.out.print("Enter password:");
		String password = input.nextLine();
		System.out.print("ReType-Password:");
		String retype = input.nextLine();
		if(password.contentEquals(retype))
		validate(name,emailId,phoneNumber,role,password);
		else
			System.out.println("Passwords Does Not Match");

	}

	//alphanumeric@gmail.com
	public boolean validateEmailId(String emailId) {
		//System.out.println("email");
		return true;
	}

	 //atleast 1uppercase, 1lowercase,1special,min-8 length, max-12 
	public boolean validatePassword(String password) {
		//System.out.println("password");
		return true;
	}

	//[0-9][10]
	public boolean validatePhoneNumber(String phoneNumber) {
		//System.out.println("mobile");
		//return  phoneNumber.matches("(0/91)?[7-9][0-9]{9}");
		return true;
	}

	public boolean checkForExistenceOFSameFields(String emailId, String password, String phoneNumber)
			throws IOException {
		//System.out.println("data");
		excelOperations = new ExcelOperations("resources/BookStoreData.xlsx", "RegisteredUsers");
		List<User> usersList = excelOperations.readData();
		boolean status = true;
		for (User user : usersList) {
         if(user.getEmailId().contentEquals(emailId)||user.getPhoneNumber().contentEquals(password)&&user.getPhoneNumber().contentEquals(phoneNumber))
         {status=false;	break;}			
		}
		return status;
	}

	public void validate(String name,String emailId,String phoneNumber,String role,String password) throws IOException {
	   boolean a,b,c,d;
	   a=validateEmailId(emailId);
	   b=validatePhoneNumber(password);
	   c=validatePassword(password);
	   d=checkForExistenceOFSameFields(emailId, password, phoneNumber);

	   System.out.println(a+""+b+""+c+d);
	   if (a==true&&b==true&&c==true&&d==true) {
			excelOperations = new ExcelOperations("resources/BookStoreData.xlsx", "RegisteredUsers");
			excelOperations.writeData(name, emailId, phoneNumber,role, password);
		}
		else if(d==false)
			System.out.println("Same Data Exists");
		else
			System.out.println("Details Not According to the ");

	}

}