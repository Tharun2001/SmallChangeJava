package com.smallChange.integration;

import java.util.ArrayList;

import com.smallChange.account.Holding;
import com.smallChange.security.Security;

public interface PortfolioDao {

	void addNewHolding(Security security, double quantity, double buyPrice);

	public Holding getHoldingBySymbol(String symbol);

	public void updateTrade(Security security, String tradeType, double quantity, double price);

	public int getNumberOfHoldings();

	public ArrayList<Holding> getHoldings();

	public double getCurrentValue();

	public double getInvestedAmount();

	public double getPnL();

	public double getPnLPercentage();

}
