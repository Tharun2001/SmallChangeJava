package com.smallChange.account;

import com.smallChange.security.Security;

public class Holding {
	private Security security;
	private double holdingQuantity;
	private double investedAmount;

	public Holding(Security security, double quantity, double buyPrice) throws Exception {
		this.setSecurity(security);
		this.setHoldingQuantity(quantity);
		this.investedAmount = buyPrice;
	}

	public void add(double quantity, double buyPrice) {
		this.holdingQuantity += quantity;
		this.investedAmount += buyPrice * quantity;
	}

	public void exit(double quantity, double sellPrice) throws Exception {
		if (quantity > holdingQuantity) {
			throw new Exception("Sell quantity cannot be greater than holding quantity.");
		}
		this.holdingQuantity -= quantity;
		this.investedAmount -= sellPrice * quantity;
	}

	public double getHoldingQuantity() {
		return holdingQuantity;
	}

	public void setHoldingQuantity(double holdingQuantity) throws Exception {
		if (holdingQuantity <= 0) {
			throw new Exception("Holding quantity should be greater than zero");
		}
		this.holdingQuantity = holdingQuantity;
	}

	public double getCurrentValue() {
		return security.getLtp() * this.holdingQuantity;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		if (security == null) {
			throw new IllegalArgumentException("security cannot be null");
		}
		this.security = security;
	}

	public double getInvestedAmount() {
		return this.investedAmount;
	}

}
