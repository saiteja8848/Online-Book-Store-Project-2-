package com.prograd.services;

public interface LoginInterface {
public boolean validate(String mailId, String password,String role) throws Exception;
public void start() throws Exception;
}
