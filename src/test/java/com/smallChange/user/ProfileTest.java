package com.smallChange.user;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfileTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEmptyUsername() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        Profile user = new Profile("FName",  "Lname", LocalDate.now(), "", "a@gmail.com", "A",  "9239291929", 3);
	    });
	    assertTrue(exception.getMessage().contains("Username cannot be empty or null."));
	}
	
	@Test
	void testEmptyFirstName() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        Profile user = new Profile("",  "Lname", LocalDate.now(), "sda", "a@gmail.com", "A", "9239291929", 3);
	    });
	    assertTrue(exception.getMessage().contains("First Name cannot be empty or null."));
	}
	
	@Test
	void testEmptyLastName() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        Profile user = new Profile("FName",  "", LocalDate.now(), "asd", "a@gmail.com", "A", "9239291929", 3);
	    });
	    assertTrue(exception.getMessage().contains("Last Name cannot be empty or null."));
	}
	
	@Test
	void testEmptyEmail() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        Profile user = new Profile("FName",  "Lname", LocalDate.now(), "asda", "", "A","9239291929", 3);
	    });
	    assertTrue(exception.getMessage().contains("Email cannot be empty or null."));
	}
	
	@Test
	void testInvalidEmail() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        Profile user = new Profile("FName",  "Lname", LocalDate.now(), "asda", "a@gmail-com", "A","9239291929", 3);
	    });
	    assertTrue(exception.getMessage().contains("Email is invalid."));
	}
	
	@Test
	void testEmptyDate() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        Profile user = new Profile("FName",  "Lname", null, "asd", "a@gmail.com", "A","9239291929", 3);
	    });
	    assertTrue(exception.getMessage().contains("Date cannot be null."));
	}
	
	@Test
	void testEmptyPhone() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        Profile user = new Profile("FName",  "Lname", LocalDate.now(), "sda", "a@gmail.com","A", "", 3);
	    });
	    assertTrue(exception.getMessage().contains("Phone number must be 10 digits."));
	}
	
	@Test
	void testInvalidPhone() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        Profile user = new Profile("FName",  "Lname", LocalDate.now(), "sda", "a@gmail.com", "A","939292929", 3);
	    });
	    assertTrue(exception.getMessage().contains("Phone number must be 10 digits."));
	}
	
	@Test
	void testInvalidRisk() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        Profile user = new Profile("FName",  "Lname", LocalDate.now(), "das", "a@gmail.com", "A","9239291929", 6);
	    });
	    assertTrue(exception.getMessage().contains("Risk must be between 1 & 5."));
	}

}
