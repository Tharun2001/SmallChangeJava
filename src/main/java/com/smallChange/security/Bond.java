package com.smallChange.security;

public class Bond extends Security{
	
	private String assetClass;
	
	public Bond(String symbol, double ltp, String name, String assetClass) {
		super(symbol,ltp,name);
		if(assetClass == null || assetClass.length()==0) {
			throw new IllegalArgumentException("Asset Class cannot be empty");
		}
		this.assetClass = assetClass;
	}
	
	public String getAssetClass() {
		return assetClass;
	}

}
