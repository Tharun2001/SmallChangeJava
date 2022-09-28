package com.smallChange.user;

import java.time.LocalDate;

public class Profile {
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String username;
	private String email;
	private String phone;
	private int risk;
	
	Profile(String fname, String lname, LocalDate date, String uname, String email, String phone, int risk) {
		
		if(fname.equals("") || fname.equals(null)) {
			throw new IllegalArgumentException("First Name cannot be empty or null.");
		}
		else {
			this.firstName = fname;
		}
		
		if(lname.equals("") || lname.equals(null)) {
			throw new IllegalArgumentException("Last Name cannot be empty or null.");
		}
		else {
			this.lastName = lname;
		}
		
		if(uname.equals("") || uname.equals(null)) {
			throw new IllegalArgumentException("Username cannot be empty or null.");
		}
		else {
			this.username = uname;
		}
		
		if(email.equals("") || email.equals(null)) {
			throw new IllegalArgumentException("Email cannot be empty or null.");
		}
		else {
			this.email = email;
		}
		
		if(phone.length() < 10) {
			throw new IllegalArgumentException("Phone number must be 10 digits.");
		}
		else {
			this.phone = phone;
		}
		
		if(risk >= 1 && risk <= 5) {
			this.risk = risk;
		}
		else {
			throw new IllegalArgumentException("Risk must be between 1 & 5.");
		}
		
		if(date == null) {
			throw new IllegalArgumentException("Date cannot be null.");
		}
		else {
			this.dob = date;
		}
	}
	
	public boolean signup(String fname, String lname, LocalDate date, String uname, String email, String phone, int risk) {
		System.out.println("First Name : " + fname);
		System.out.println("Last Name : " + lname);
		System.out.println("UserName : " + uname);
		System.out.println("DOB : " + date);
		System.out.println("E-Mail : " + email);
		System.out.println("Phone : " + phone);
		System.out.println("Risk : " + risk);
		return true;
	}
	

	public boolean display(String fname, String lname, LocalDate date, String uname, String email, String phone, int risk) {
		System.out.println("First Name : " + fname);
		System.out.println("Last Name : " + lname);
		System.out.println("UserName : " + uname);
		System.out.println("DOB : " + date);
		System.out.println("E-Mail : " + email);
		System.out.println("Phone : " + phone);
		System.out.println("Risk : " + risk);
		return true;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public int getRisk() {
		return risk;
	}
}
