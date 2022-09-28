package com.smallChange.account;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.smallChange.security.Security;

public class Portfolio {
	private BankAccount bankAcct;

	private ArrayList<Holding> holdings;

	public Portfolio(String accNumber, String bankName, BigDecimal balance) {
		this.holdings = new ArrayList<Holding>();
		this.bankAcct = new BankAccount(accNumber, bankName, balance);
	}

	public void addNewHolding(Security security, double quantity, double buyPrice) throws Exception {

		this.holdings.add(new Holding(security, quantity, buyPrice));
	}

	public Holding getHoldingBySymbol(String symbol) {
		for (Holding holding : this.holdings) {
			if (holding.getSecurity().getSymbol() == symbol) {
				return holding;
			}
		}
		return null;
	}

	public void updateTrade(Security security, String tradeType, double quantity, double price) throws Exception {
		Holding holding = getHoldingBySymbol(security.getSymbol());
		if (tradeType == "buy") {
			if(bankAcct.getBalance().compareTo(BigDecimal.valueOf(quantity*price))<0) {
				throw new Exception("Insufficient balance to buy");
			}
			else if (holding == null) {
				this.addNewHolding(security, quantity, price);
			} else {
				holding.add(quantity, price);
			}
		} else if (tradeType == "sell") {
			if (holding == null) {
				throw new Exception("Security not found in Portfolio");
			}
			holding.exit(quantity, price);
		} else {
			throw new IllegalArgumentException("tradeType must be either buy or sell");
		}

	}

	public int getNumberOfHoldings() {
		return holdings.size();
	}

	public ArrayList<Holding> getHoldings() {
		return holdings;
	}

	public double getCurrentValue() {
		double currentValue = 0;
		for (Holding holding : this.holdings) {
			currentValue += holding.getCurrentValue();
		}
		return currentValue;

	}

	public double getInvestedAmount() {
		double investedAmount = 0;
		for (Holding holding : this.holdings) {
			investedAmount += holding.getInvestedAmount();
		}
		return investedAmount;
	}

	public double getPnL() {
		return getCurrentValue() - getInvestedAmount();
	}

	public double getPnLPercentage() {
		return getPnL() / getInvestedAmount();
	}

}
