package com.smallChange.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SecurityTest {
	
	private Security security;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void createSecurity_Success() {
		
		String assetClass = "NIFTY";

		security = new Security(2,"Infosys","INFY",1523.43,assetClass);		
		
		String result = security.getStockAssetClass();
		
		assertEquals(assetClass, result);
	}

	@Test
	void createSecurity_NullSymbol_ThrowsException( ) {
		assertThrows(IllegalArgumentException.class, () -> {
			security = new Security(1,"Infosys",null,1523.43,"Smallcap");		
		});
	}

}
