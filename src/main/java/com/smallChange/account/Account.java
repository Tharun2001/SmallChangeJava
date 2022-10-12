package com.smallChange.account;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Account {
	private String acct_num;
	private String type;
	private Set<BankAccount> accounts;
	
	public Account(String acct_num, String type) {
		super();
		if(type == null || type.equals("")) {
			throw new IllegalArgumentException("Type should not be null or empty.");
		}
		this.acct_num = acct_num;
		this.type = type;
		this.accounts = new HashSet<>();
	}
	
	public String getAcct_num() {
		return acct_num;
	}

	public void setAcct_num(String acct_num) {
		this.acct_num = acct_num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<BankAccount> accounts) {
		this.accounts = accounts;
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
		return Objects.hash(accounts, acct_num, type);
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
		return Objects.equals(accounts, other.accounts) && Objects.equals(acct_num, other.acct_num)
				&& Objects.equals(type, other.type);
	}
	

	
}
