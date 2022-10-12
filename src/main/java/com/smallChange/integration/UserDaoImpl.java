package com.smallChange.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.smallChange.user.Profile;
import com.smallChange.user.User;

public class UserDaoImpl implements UserDao{

	
	DataSource dataSource;
	public UserDaoImpl(DataSource ds) {
		this.dataSource = ds;
	}

	@Override
	public List<Profile> getAllUsers() {
		String sql = "select scu.first_name, scu.last_name, scu.date_of_birth, scu.email, scu.phone_number, scu.username, scu.user_password, scp.risk_tolerance from sc_users scu\r\n"
				+ "left join sc_preferences scp\r\n"
				+ "on scp.username = scu.username";
		
		ArrayList<Profile> users = new ArrayList<Profile>();
		PreparedStatement stmt = null;
		try {
			Connection conn = dataSource.getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("first_name");
				
				String dob = rs.getString("date_of_birth");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yy");
		        LocalDate localDate = LocalDate.parse(dob, formatter);
		        
				String email = rs.getString("email");
				String phone = rs.getString("phone_number");
				String username = rs.getString("username");
				String password = rs.getString("user_password");
				int risk = rs.getInt("risk_tolerance");
				
				Profile p = new Profile(first_name, last_name, localDate, email, phone, username, password, risk);
				users.add(p);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void signupUser(Profile p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginUser(User user) {
		// TODO Auto-generated method stub
		
	}

}
