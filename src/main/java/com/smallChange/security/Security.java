package com.smallChange.security;

public abstract class Security {
	
	private String symbol; 
	private double ltp;
	private String name;
	
	
	public Security(String symbol, double ltp, String name) {
		
		if(symbol==null || symbol.length()==0) {
			throw new IllegalArgumentException("Symbol cannot be empty");
		}
		
		if(name==null || name.length()==0) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		
		if(ltp<0) {
			throw new IllegalArgumentException("LTP cannot be less than zero");
		}
		
		this.symbol = symbol;
		this.ltp = ltp;
		this.name = name;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public double getLtp() {
		return ltp;
	}
	
	public String getName() {
		return name;
	}

}
