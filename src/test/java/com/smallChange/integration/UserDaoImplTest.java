package com.smallChange.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;


class UserDaoImplTest {
	
	SimpleDataSource sds;
	ClientDao dao;
	JdbcTemplate jdbcTemplate;
	DbTestUtils dbTestUtils;
	Connection connection;
	TransactionManager manager;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
