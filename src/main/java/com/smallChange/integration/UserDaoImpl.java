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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smallChange.user.Profile;
import com.smallChange.user.User;

public class UserDaoImpl implements UserDao{

	private final Logger logger = LoggerFactory.getLogger(UserDao.class);
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
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		        LocalDate localDate = LocalDate.parse(dob, formatter);
		        
				String email = rs.getString("email");
				String phone = rs.getString("phone_number");
				String username = rs.getString("username");
				String password = rs.getString("user_password");
				int risk = rs.getInt("risk_tolerance");
				
				Profile p = new Profile(first_name, last_name, localDate, username, password, email, phone, risk);
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
		String sql1 = "Insert into sc_users (first_name, last_name, date_of_birth, email, phone_number, username, user_password) values (?, ?, ?, ?, ?, ?, ?)";

		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement stmt1 = conn.prepareStatement(sql1)) {
			
			stmt1.setString(1, p.getFirstName());
			stmt1.setString(2, p.getLastName());
			stmt1.setDate(3, java.sql.Date.valueOf(p.getDob()));
			stmt1.setString(4, p.getEmail());
			stmt1.setString(5, p.getPhone());
			stmt1.setString(6, p.getUsername());
			stmt1.setString(7, p.getPassword());

			
			stmt1.executeUpdate();
			
		} 
		catch (SQLException e1) {
			//logger.error("Cannot insert into sc_users", sql1, e1);
			throw new DatabaseException("Cannot insert into " + sql1, e1);
		}
		
	}

	@Override
	public void deleteUser(String username) {
		String sql1 = "DELETE FROM sc_users WHERE username = ?";

		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement stmt1 = conn.prepareStatement(sql1)) {
			
			stmt1.setString(1, username);

			stmt1.executeUpdate();
			
		} 
		catch (SQLException e1) {
			logger.error("Cannot delete from sc_users", sql1, e1);
			throw new DatabaseException("Cannot delete from " + sql1, e1);
		}
		
	}

	@Override
	public boolean loginUser(User user) {
		String sql1 = "select COUNT(username) from sc_users\r\n"
				+ "WHERE \r\n"
				+ "username = ?\r\n"
				+ "AND\r\n"
				+ "user_password = ?";

		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement stmt1 = conn.prepareStatement(sql1)) {
			
			stmt1.setString(1, user.getUsername());
			stmt1.setString(2, user.getPassword());

			ResultSet rs = stmt1.executeQuery();
			while(rs.next()) {
				if(rs.getInt("count(username)") == 1) {
					return true;
				}
				else {
					return false;
				}
			}
			
		} 
		catch (SQLException e1) {
			logger.error("Cannot delete from sc_users", sql1, e1);
			throw new DatabaseException("Cannot delete from " + sql1, e1);
		}
		return false;
	}

}
