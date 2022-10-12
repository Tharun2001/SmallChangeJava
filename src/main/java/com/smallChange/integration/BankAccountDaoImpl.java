package com.smallChange.integration;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smallChange.account.Account;
import com.smallChange.account.BankAccount;

public class BankAccountDaoImpl implements BankAccountDao{
	private final Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);

	private DataSource datasource;
	
	public BankAccountDaoImpl(DataSource datasource) {
		super();
		this.datasource = datasource;
	}
	
	@Override
	public Set<BankAccount> getBankAccounts(String acct_num) {
		Set<BankAccount> bankAccounts = new HashSet<>();
		String sql = """
				select bank_acctnum, bank_name, balance from sc_bankaccount where acct_num = ?
				""";
		try (Connection conn = datasource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, acct_num);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String bank_acctnum = rs.getString("bank_acctnum");
				String bank_name = rs.getString("bank_name");
				BigDecimal balance = rs.getBigDecimal("balance");
				BankAccount bankAccount = new BankAccount(bank_acctnum, bank_name, balance);
				bankAccounts.add(bankAccount);
			}
		} catch (SQLException e) {
			logger.error("Unable to retrieve bank accounts",  e);
			throw new DatabaseException("Unable to retrieve bank accounts ", e);
		}
		return bankAccounts;
	}


	@Override
	public void addBankAccount(String acct_num, BankAccount bankAccount) {
		String sql = """
				INSERT INTO sc_bankaccount(acct_num, bank_acctnum, bank_name, balance) 
				values(?, ?, ?, ?)
				""";
		try (Connection conn = datasource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, acct_num);
			stmt.setString(2, bankAccount.getbankAccNumber());
			stmt.setString(3, bankAccount.getBankName());
			stmt.setBigDecimal(4, bankAccount.getBalance());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.error("Unable to insert bank account",  e);
			throw new DatabaseException("Unable to insert bank account ", e);
		}
	}

	@Override
	public void deleteBankAccount(String bankAcctNumber) {
		String sql1 = "DELETE FROM sc_bankaccount WHERE BANK_ACCTNUM = ?";

		try(Connection conn = datasource.getConnection(); 
				PreparedStatement stmt1 = conn.prepareStatement(sql1)) {
			
			stmt1.setString(1, bankAcctNumber);

			stmt1.executeUpdate();
			
		} 
		catch (SQLException e1) {
			logger.error("Cannot delete from sc_bankaccount", sql1, e1);
			throw new DatabaseException("Cannot delete from " + sql1, e1);
		}
	}

	@Override
	public void updateBankAccount(BankAccount bankAccount) {
		String sql = """
				UPDATE sc_bankaccount
				SET 
					bank_name = ?, 
					balance = ?
				where bank_acctnum = ?
				""";
		try (Connection conn = datasource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, bankAccount.getBankName());
			stmt.setBigDecimal(2, bankAccount.getBalance());
			
			stmt.setString(3, bankAccount.getbankAccNumber());
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.error("Unable to update bank account",  e);
			throw new DatabaseException("Unable to update bank account ", e);
		}
		
	}

}
