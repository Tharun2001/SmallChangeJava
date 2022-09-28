package com.smallChange.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MutualFundTest {
	
	private MutualFund mutualfund;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void createMF_Success( ) {
		String fundManager = "Rahul Nair";
		mutualfund = new MutualFund("ABC",23.49,"Axis Bluechip",fundManager);		
		
		String result = mutualfund.getFundManager();
		
		assertEquals(fundManager, result);
	}

	@Test
	void createMF_NullFundManager_ThrowsException( ) {
		assertThrows(IllegalArgumentException.class, () -> {
			mutualfund = new MutualFund("ABC",23.49,"Axis Bluechip",null);	
		});
	}

}
