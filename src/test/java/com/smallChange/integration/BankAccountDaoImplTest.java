package com.smallChange.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.smallChange.*;
import com.smallChange.account.BankAccount;

class BankAccountDaoImplTest {
	BankAccountDao dao;
	private SimpleDataSource dataSource;
	private Connection connection;
	private TransactionManager transactionManager;
	
	@BeforeEach
	void setUp() throws Exception {
		dataSource = new SimpleDataSource();
		connection = dataSource.getConnection();
		transactionManager = new TransactionManager(dataSource);
		
		dao = new BankAccountDaoImpl(dataSource);
		
		transactionManager.startTransaction();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		transactionManager.rollbackTransaction();
		dataSource.shutdown();
	}

	@Test
	void getAllBankAccounts() {
		BankAccount bankAccount = new BankAccount("608502111", "Brokerage", BigDecimal.valueOf(1050000));
		Set<BankAccount> bankAccounts = dao.getBankAccounts("ABC123");
		
		assertEquals(bankAccounts.size(), 1);
		assertTrue(bankAccounts.contains(bankAccount));
	}


	@Test
	void insertNewBankAccount() {
		BankAccount bankAccount = new BankAccount("123556111", "Brokerage", BigDecimal.valueOf(2050000));
		dao.addBankAccount("ABC123", bankAccount);
		
		Set<BankAccount> bankAccounts = dao.getBankAccounts("ABC123");
		assertEquals(bankAccounts.size(), 2);
		assertTrue(bankAccounts.contains(bankAccount));
	}
	
	@Test
	void insert_DuplicateBankAccount() {
		BankAccount bankAccount = new BankAccount("123556111", "Brokerage", BigDecimal.valueOf(2050000));
		dao.addBankAccount("ABC123", bankAccount);
		
		Set<BankAccount> bankAccounts = dao.getBankAccounts("ABC123");
		assertEquals(bankAccounts.size(), 2);
		assertTrue(bankAccounts.contains(bankAccount));
	}
	
	@Test
	void updateBankAccount() {
		BankAccount bankAccount = new BankAccount("608502111", "BANK A", BigDecimal.valueOf(2050000));
		//dao.updateBankAccount(bankAccount);
	}
	
	@Test
	void deleteBankAccount() {
		BankAccount bankAccount = new BankAccount("608502111", "Brokerage", BigDecimal.valueOf(1050000));
//		dao.deleteBankAccount("608502111");
		
	}
}
