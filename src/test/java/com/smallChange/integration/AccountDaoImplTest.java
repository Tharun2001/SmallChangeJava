package com.smallChange.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.support.DaoSupport;

import com.smallChange.account.Account;

class AccountDaoImplTest {
	AccountDao dao;
	private Account account;
	private SimpleDataSource dataSource;
	private Connection connection;
	private TransactionManager transactionManager;
	
	@BeforeEach
	void setUp() throws Exception {
		dataSource = new SimpleDataSource();
		connection = dataSource.getConnection();
		transactionManager = new TransactionManager(dataSource);
		dao = new AccountDaoImpl(dataSource);
	
		transactionManager.startTransaction();
	}

	@AfterEach
	void tearDown() throws Exception {
		transactionManager.rollbackTransaction();
		dataSource.shutdown();
	}

	@Test
	void getAllAccounts() {
		Set<Account> accounts = dao.getAllAccounts("Tharun");
		assertEquals(accounts.size(), 1);
	}

	@Test
	void addAccount() {
		Account account = new Account("CCC112", "Brokerage");
		dao.addAccount("Tharun", account);
		Set<Account> accounts = dao.getAllAccounts("Tharun");
		assertEquals(accounts.size(),2);
		assertTrue(accounts.contains(account));
	}
	
	@Test
	void addAccount_Duplicate_Account() {
		Account account = new Account("CCC112", "Brokerage");
		dao.addAccount("Tharun", account);
	}
	
	@Test
	void deleteAccount() {
//		Account account = new Account("CCC112", "Brokerage");
//		dao.deleteAccount("ABC123");
//		Set<Account> accounts = dao.getAllAccounts("Tharun");
//		assertEquals(accounts.size(),0);
	}
}
