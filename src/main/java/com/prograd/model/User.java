package com.prograd.model;

public class User {

	private String fullName;
	private String emailId;
	private String phoneNumber;
	private String password;
	private String role;
	
	public User() {}
	
	public User(String fullName, String emailId, String phoneNumber,String role, String password) {
		this.fullName = fullName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.role =role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	
}
