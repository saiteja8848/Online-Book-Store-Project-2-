package com.prograd.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.prograd.services.LoginImplementation;
import com.prograd.services.LoginInterface;
import com.prograd.services.SignUpImplementation;
import com.prograd.services.SignUpInterface;




public class UserInterface {
	public static void main(String args[]) throws Exception {
		String[] options = { "Login", "SignUp", "Exit" };
		BufferedReader InputReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("WELCOME TO ONLINE BOOK STORE\n----------------------------");
		System.out.print("1.Login\n2.SignUp\n3.Exit\nPlease Enter Your Choice:");
		
		try {
		int choice = Integer.parseInt(InputReader.readLine());
           
		if(choice>=1&&choice<=3) {
		switch (options[choice-1]) {
		case "Login":
			LoginInterface login = new LoginImplementation();
			login.start();
			break;
		case "SignUp":
		   SignUpInterface signUp = new SignUpImplementation();
		   signUp.start();
			break;
		case "Exit":
			System.out.println("\n---Thank You for Visting the Store!Hava a nice Day.----");
			System.exit(0);
			break;
		default:
			System.out.println("Invalid Option");
			break;
		}
		}else
			throw new Exception();
		
		}catch(Exception e) {
			System.out.println("\n-----------Please Provide Valid Input!------\n");
			System.out.println(e);
			UserInterface.main(null);
		}
		
		

	}
}

















