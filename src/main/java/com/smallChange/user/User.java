package com.smallChange.user;

public class User {
	private String username;
	private String password;
	
	User(String username, String password) {
		if(username.equals("") || username.equals(null)) {
			throw new IllegalArgumentException("Username cannot be empty or null.");
		}
		else {
			this.username = username;
		}
		
		if(password.equals("") || password.equals(null)) {
			throw new IllegalArgumentException("Password cannot be empty or null.");
		}
		else {
			this.password = password;
		}
	}
	
	public boolean login(String username, String password) {
		System.out.println("Username : " + username + ", Password : " + password);
		return true;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
}
