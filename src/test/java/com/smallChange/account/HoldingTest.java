package com.smallChange.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.smallChange.security.Security;
import com.smallChange.security.Stock;

class HoldingTest {

	Holding holding;
	Security security;

	@BeforeEach
	void setUp() throws Exception {
		security = new Stock("AAA", 45.5, "Abc Abd Abe", "Large Cap");
		holding = new Holding(security, 5, 45.5);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInstantiation() {
		assertNotNull(holding);
	}

	@Test
	void testAddHolding() {
		holding.add(10.5, 50);
		assertEquals(holding.getHoldingQuantity(), 15.5);
	}

	@Test
	void testExitMoreQuantityThrowsError() {
		assertThrows(Exception.class, () -> {
			holding.exit(6, 55);
		});
	}

	@Test
	void testExitHolding() throws Exception {
		holding.exit(4, 55);
		assertEquals(holding.getHoldingQuantity(), 1);
	}

}
