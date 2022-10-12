package com.smallChange.integration;

import java.util.List;

import com.smallChange.account.Account;

public interface AccountDao {
	List<Account> getAllAccounts(int userId);
}
