package com.smallChange.integration;

import java.util.List;

import com.smallChange.user.Profile;
import com.smallChange.user.User;

public interface UserDao {
	List<Profile> getAllUsers();
	void signupUser(Profile p);
	void deleteUser(String username);
	boolean loginUser(User user);
}
