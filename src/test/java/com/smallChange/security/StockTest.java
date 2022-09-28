package com.smallChange.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StockTest {
	
	private Stock stock;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void createStock_Success( ) {
		String assetClass = "Nifty 50";
		stock = new Stock("INFY",1523.43,"Infosys",assetClass);		
		
		String result = stock.getAssetClass();
		
		assertEquals(assetClass, result);
	}

	@Test
	void createStock_NullAssetClass_ThrowsException( ) {
		assertThrows(IllegalArgumentException.class, () -> {
			stock = new Stock("INFY",1523.43,"Infosys",null);		
		});
	}

}
