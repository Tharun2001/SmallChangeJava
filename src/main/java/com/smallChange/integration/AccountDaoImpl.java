package com.smallChange.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.accessibility.AccessibleEditableText;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.smallChange.account.Account;

public class AccountDaoImpl implements AccountDao{
	private final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);

	private DataSource datasource;
	
	public AccountDaoImpl(DataSource datasource) {
		super();
		this.datasource = datasource;
	}

	@Override
	public Set<Account> getAllAccounts(String username) {
		Set<Account> accounts = new HashSet<>();
		String sql = """
				select acct_num, acct_type from sc_account where username = ?
				""";
		try (Connection conn = datasource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String acct_num = rs.getString("acct_num");
				String acct_type = rs.getString("acct_type");
				Account account = new Account(acct_num, acct_type);
				accounts.add(account);
			}
		} catch (SQLException e) {
			this.logger.error("Unable to retrieve accounts", e);
			throw new DatabaseException("Unable to retrieve accounts", e);
		}
		return accounts;
	}

	@Override
	public void addAccount(String username, Account acc) {
		String sql = """ 
				INSERT INTO sc_account(USERNAME, ACCT_NUM, ACCT_TYPE) VALUES (?, ?, ?)
				""";
		try (Connection conn = datasource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, username );
			stmt.setString(2, acc.getAcct_num());
			stmt.setString(3, acc.getType());
			stmt.executeUpdate();
		} catch (SQLException e) {
			this.logger.error("Unable to add trade account", e);
			throw new DatabaseException("Unable to add trade account",e);
		}
	}

	@Override
	public void deleteAccount(String acct_num) {
		String sql = """
				delete from sc_account where acct_num = ?
				""";
		try (Connection conn = datasource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			deleteBankAccountsWithAcctNumber(acct_num);
			stmt.setString(1, acct_num );
			stmt.executeQuery();
		} catch (SQLException e) {
			this.logger.error("Unable to delete trade account", e);
			throw new DatabaseException("Unable to delete trade account",e);
		}
	}
	
	private void deleteBankAccountsWithAcctNumber(String acct_num) {
		String sql = """
				DELETE FROM SC_BANKACCOUNT where acct_num = ?
				""";
		try (Connection conn = datasource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, acct_num );
			stmt.executeQuery();
		} catch (SQLException e) {
			this.logger.error("Unable to delete bank account", e);
			throw new DatabaseException("Unable to delete bank account",e);
		}
	}

}
