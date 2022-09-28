package com.smallChange.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.smallChange.security.Security;
import com.smallChange.security.Stock;

class PortfolioTest {
	Portfolio portfolio;

	Holding holding;
	Security security;

	@BeforeEach
	void setUp() throws Exception {
		portfolio = new Portfolio("Account123", "Bank", BigDecimal.valueOf(100000));
		security = new Stock("AAA", 45.5, "Abc Abd Abe", "Large Cap");
		holding = new Holding(security, 5, 45.5);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInstantiation() {
		assertNotNull(portfolio);
	}

	@Test
	void testUpdateTradeSellException() throws Exception {
		assertThrows(Exception.class, () -> {
			portfolio.updateTrade(security, "sell", 10, 20);
		});
	}

	@Test
	void testUpdateTradeBuy() throws Exception {
		portfolio.updateTrade(security, "buy", 10, 20);
		assertEquals(portfolio.getHoldingBySymbol("AAA").getHoldingQuantity(), 10);
	}

	@Test
	void testUpdateTradeSell() throws Exception {
		portfolio.updateTrade(security, "buy", 10, 20);
		portfolio.updateTrade(security, "sell", 9, 20);
		assertEquals(portfolio.getHoldingBySymbol("AAA").getHoldingQuantity(), 1);
	}

}
