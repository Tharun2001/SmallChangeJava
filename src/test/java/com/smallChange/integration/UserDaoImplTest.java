package com.smallChange.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.smallChange.integration.DbTestUtils;
import com.smallChange.user.Profile;



class UserDaoImplTest {
	
	SimpleDataSource sds;
	UserDao dao;
	Connection connection;
	TransactionManager manager;
	JdbcTemplate jdbcTemplate;
	DbTestUtils dbTestUtils;


	@BeforeEach
	void setUp() throws Exception {
		sds = new SimpleDataSource();
		
		connection = sds.getConnection();
		
						
		// Start the transaction
		dao = new UserDaoImpl(sds);
		dbTestUtils = new DbTestUtils(connection);
		jdbcTemplate = dbTestUtils.initJdbcTemplate();
		manager = new TransactionManager(sds);
		manager.startTransaction();
	}

	@AfterEach
	void tearDown() throws Exception {
		manager.rollbackTransaction();
		sds.shutdown();
	}

	@Test
	void testNotNull() {
		assertNotNull(dao);
	}
	
	@Test
	void testGetAllUsers() {
		List<Profile> p = dao.getAllUsers();
		assertTrue(p.size() > 0);
	}
	
	@Test
	void testSignupUser() {
		//Getting old size of client table
		int oldClientSize = countRowsInTable(jdbcTemplate, "sc_users");
		
		//Checking if values already exist or not with id 566 in client table
		String clientsQuery = "select * from sc_users where username = 'Test'";
		assertEquals(0, jdbcTemplate.queryForList(clientsQuery).size(), "Should not contain new values before insert");
		
		//Inserting client with phone number
		Profile p = new Profile("Test", "User", LocalDate.now(), "Test", "test", "test@gmail.com", "+91-9999999999", 5);
		dao.signupUser(p);

		//Getting new sizes for both tables after insert
		int newClientSize = countRowsInTable(jdbcTemplate, "sc_users");

		//Old Sizes + 1 = New Sizes 
		assertEquals(oldClientSize + 1, newClientSize, "Should have one more Clients after insert");	
	}
}
