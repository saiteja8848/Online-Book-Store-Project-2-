package com.prograd.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.prograd.utilites.ExcelOperations;
import com.prograd.utilites.Notification;
import com.prograd.view.UserInterface;

public class SignUpImplementation implements SignUpInterface {

	private ExcelOperations excelOperations;
	private Scanner input = new Scanner(System.in);

	//Start of the taking SignUp Details ,calling validation method
	public void start() throws Exception {
		System.out.println("\nPlease Enter the Following Details\n----------------------------------");
		System.out.print("Enter your Full Name:");
		String name = input.nextLine();
		System.out.print("Enter your emailId:");
		String emailId = input.nextLine();
		System.out.print("Enter your Mobile Name:");
		String phoneNumber = input.nextLine();
		System.out.print("Enter your role(admin/customer):");
		String role = input.nextLine();
		System.out.print("Enter password(min-length-8,atleast 1 upperCase,LowerCase,SpecialCharacter,a digit):");
		String password = input.nextLine();
		System.out.print("Confirm-Password:");
		String retype = input.nextLine();
		System.out.println(name + " " + emailId + " " + phoneNumber + " " + role + " " + password);

		if (password.contentEquals(retype))
			validate(name, emailId, phoneNumber, role, password);
		else {
			System.out.println("\nPasswords Does Not Match to go further validating further Details\n");
			UserInterface.main(null);
		}

	}

	public boolean validateName(String name) {
		return name.matches("^[a-zA-Z]*$");
	}

	public boolean validateEmailId(String emailId) {
		return emailId.matches("^[^@\\s]+@[^@\\s\\.]+\\.[^@\\.\\s]+$");
	}

	public boolean validatePassword(String password) {
		return password.matches("^(?=.{8,}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).*$");
	}

	public boolean validatePhoneNumber(String phoneNumber) {
		return phoneNumber.matches("(0/91)?[7-9][0-9]{9}");
	}

	public boolean checkForExistenceOFSameFields(String emailId, String password, String phoneNumber)
			throws IOException {
		boolean status = true;
		excelOperations = new ExcelOperations();
		List<List<String>> data = excelOperations.read("resources/BookStoreData.xlsx", "RegisteredUserDetails");
		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).size(); j++) {
				if (data.get(i).get(j).contains(emailId)) {
					status = false;
					break;
				}
			}
		}
		return status;
	}

	
	// This methods forwards validationss to respective validation methods and go forward based on the return result 
	public void validate(String name, String emailId, String phoneNumber, String role, String password)
			throws Exception {
		boolean validatedName = validateName(name);
		boolean validatedMail = validateEmailId(emailId);
		boolean validatedMobileNo = validatePhoneNumber(phoneNumber);
		boolean validatedPassword = validatePassword(password);
		boolean dataExisting = checkForExistenceOFSameFields(emailId, password, phoneNumber);
		if (validatedName && validatedMail && validatedMobileNo && validatedPassword && dataExisting) {
			excelOperations = new ExcelOperations();
			List<List<String>> data = new ArrayList<>();
			List<String> user = new ArrayList<>();
			user.add(name);
			user.add(emailId);
			user.add(phoneNumber);
			user.add(role);
			user.add(password);
			data.add(user);
			excelOperations.write("resources/BookStoreData.xlsx", "RegisteredUserDetails", data);
			sendNotification(name, emailId, password, phoneNumber);
			LoginInterface login = new LoginImplementation();
			login.start();

		} else if (dataExisting == false)
			System.out.println("\n-----------Some One has already Registered with Same Fields------!");
		else if (validatedName == false)
			System.out.println("\n------please Enter valid Name-----!");
		else if (validatedMail == false)
			System.out.println("\n----Please Enter valid MailID----!");
		else if (validatedMobileNo == false)
			System.out.println("\n--------Please Enter valid mobile Number-----!");
		else if (validatedPassword == false) {
			System.out.println(
					"\n--------Please Provide Password following rules(Min-length(8),atleast a upperCase,lowerCase,Digit,SpecialCharacter)---------");
		}

	}

	//send login credentials on successfully login to the registered user
	public void sendNotification(String name, String mailId, String password, String phoneNumber) {
		String message = "Hi " + name + "you are successfully logged in!!! userId:" + mailId + " password:" + password;
		Notification.sendSms(message, phoneNumber);
	}

}