package com.smallChange.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEmptyUsername() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        User user = new User("",  "password");
	    });

	    String expectedMessage = "Username cannot be empty or null.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testEmptyPassword() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
	        User user = new User("User",  "");
	    });

	    String expectedMessage = "Password cannot be empty or null.";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}

}
