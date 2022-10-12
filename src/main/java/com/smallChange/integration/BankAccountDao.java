package com.smallChange.integration;

import java.util.Set;

import com.smallChange.account.BankAccount;

public interface BankAccountDao {
	Set<BankAccount> getBankAccounts(String acct_num);
	void addBankAccount(String acct_num, BankAccount bankAccount);
	void deleteBankAccount(String bankAcctNumber);
	void updateBankAccount(BankAccount bankAccount);
}
