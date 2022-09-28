package com.smallChange.account;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Account {
	private String type;
	private Set<BankAccount> accounts;
	
	public Account(String type) {
		super();
		if(type == null || type.equals("")) {
			throw new IllegalArgumentException("Type should not be null or empty.");
		}
		this.type = type;
		this.accounts = new HashSet<>();
	}
	
	public void addAccount(BankAccount account) {
		accounts.add(account);
	}
	
	public void removeAccount(BankAccount account) {
		if(accounts.contains(account)) {
			accounts.remove(account);
		}
		else {
			throw new IllegalArgumentException("Provided account is not present.");
		}
	}

	public BankAccount getBankAccount(BankAccount account) {
		for (BankAccount ele : accounts) {
		    if (ele.equals(account)) {
		      return account;
		    }
		}
		throw new IllegalArgumentException("Provided account is not present.");
	}
	
	public int getNoOfBankAccounts() {
		return accounts.size();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(accounts, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accounts, other.accounts) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Account [type=" + type + ", accounts=" + accounts + "]";
	}
	
	
}
