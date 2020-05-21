package com.prograd.services;

public interface SignUpInterface {
	public void start() throws Exception;
	public boolean validateEmailId(String emailId);
	public boolean validatePassword(String password);
	public boolean validatePhoneNumber(String phoneNumber);
}


