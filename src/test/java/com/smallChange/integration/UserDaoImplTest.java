package com.smallChange.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class UserDaoImplTest {
	
	SimpleDataSource sds;
	UserDao dao;
	Connection connection;
	TransactionManager manager;

	@BeforeEach
	void setUp() throws Exception {
		sds = new SimpleDataSource();
		
		connection = sds.getConnection();
						
		// Start the transaction
		dao = new UserDaoImpl(sds);
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

}
