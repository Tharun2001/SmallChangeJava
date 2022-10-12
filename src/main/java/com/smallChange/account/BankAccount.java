package com.smallChange.account;

import java.math.BigDecimal;
import java.util.Objects;

public class BankAccount {
	private String bankAccNumber;
	private String bankName;
	private BigDecimal balance;
	
	public BankAccount(String bankAccNumber, String bankName, BigDecimal balance) {
		super();
		if(bankAccNumber == null || bankAccNumber.equals("")) {
			throw new IllegalArgumentException("Account number should not be null or empty.");
		}
		
		this.bankAccNumber = bankAccNumber;
		if(bankName == null || bankName.equals("")) {
			throw new IllegalArgumentException("Account number should not be null or empty.");
		}
		
		this.bankName = bankName;
		if(balance == null || bankName.equals("")) {
			throw new IllegalArgumentException("Account number should not be null or empty.");
		}
		
		if(balance.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Balance cannot be negative.");
		}
		this.balance = balance;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void creditAmt(BigDecimal amt) {
		balance = balance.add(amt);
	}
	
	public void debitAmt(BigDecimal amt) {
		if(balance.compareTo(amt) < 0) {
			throw new IllegalArgumentException("Insufficient balance.");
		}
		balance = balance.subtract(amt);
	}
	
	public String getbankAccNumber() {
		return bankAccNumber;
	}

	public void setbankAccNumber(String bankAccNumber) {
		this.bankAccNumber = bankAccNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bankAccNumber, balance, bankName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return Objects.equals(bankAccNumber, other.bankAccNumber) && Objects.equals(balance, other.balance)
				&& Objects.equals(bankName, other.bankName);
	}
	
	@Override
	public String toString() {
		return "BankAccount [bankAccNumber=" + bankAccNumber + ", bankName=" + bankName + ", balance=" + balance + "]";
	}
}
