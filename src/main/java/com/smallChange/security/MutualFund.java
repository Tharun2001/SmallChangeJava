package com.smallChange.security;

public class MutualFund extends Security{
	
	private String fundManager;
	
	public MutualFund(String symbol, double ltp, String name, String fundManager) {
		super(symbol,ltp,name);
		if(fundManager == null || fundManager.length()==0) {
			throw new IllegalArgumentException("Fund Manager cannot be empty");
		}
		this.fundManager = fundManager;
	}
	
	public String getFundManager() {
		return fundManager;
	}

}
