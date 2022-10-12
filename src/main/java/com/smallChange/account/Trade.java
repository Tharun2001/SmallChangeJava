package com.smallChange.account;

import java.time.LocalDateTime;

import com.smallChange.security.Security;

public class Trade {
	private String trade_type;
	private Security security;
	private LocalDateTime trade_time;
	private double quantity;
	private double unit_price;

	public Trade(String trade_type, Security security, LocalDateTime trade_time, double quantity, double unit_price) {
		super();
		this.setTrade_type(trade_type);
		this.setSecurity(security);
		this.setTrade_time(trade_time);
		this.setQuantity(quantity);
		this.setUnit_price(unit_price);
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public LocalDateTime getTrade_time() {
		return trade_time;
	}

	public void setTrade_time(LocalDateTime trade_time) {
		this.trade_time = trade_time;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(double unit_price) {
		this.unit_price = unit_price;
	}

}
