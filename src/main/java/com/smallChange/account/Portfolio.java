package com.smallChange.account;

import java.util.ArrayList;

import com.smallChange.security.Security;

public class Portfolio {

	private ArrayList<Holding> holdings;

	public Portfolio() {
		this.holdings = new ArrayList<Holding>();
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
			if (holding == null) {
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
