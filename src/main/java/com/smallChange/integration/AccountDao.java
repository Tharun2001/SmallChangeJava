package com.smallChange.integration;

import java.util.List;
import java.util.Set;

import com.smallChange.account.Account;

public interface AccountDao {
	Set<Account> getAllAccounts(String username);
	void addAccount(String username, Account acc);
	void deleteAccount(String acct_num);
}
