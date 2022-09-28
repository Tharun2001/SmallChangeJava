package com.smallChange.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BondTest {
	
	private Bond bond;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void createBond_Success( ) {
		String assetClass = "Government Bond";
		bond = new Bond("IND",213.43,"RBI GVN",assetClass);		
		
		String result = bond.getAssetClass();
		
		assertEquals(assetClass, result);
	}

	@Test
	void createBond_NullAssetClass_ThrowsException( ) {
		assertThrows(IllegalArgumentException.class, () -> {
			bond = new Bond("IND",213.43,"RBI GVN",null);	
		});
	}

}
