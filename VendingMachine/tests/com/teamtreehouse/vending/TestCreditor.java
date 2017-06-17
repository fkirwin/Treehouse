package com.teamtreehouse.vending;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCreditor {
	private Creditor creditor;

	@Before
	public void setUp() throws Exception {
		creditor = new Creditor();
	}

	@Test
	public void addingFundsIncrementsAvailableFunds() throws Exception
	{
		creditor.addFunds(25);
		creditor.addFunds(25);
		assertEquals(50, creditor.getAvailableFunds());
	}
	
	@Test
	public void refundingReturnsAllAvailableFunds() throws Exception
	{
		creditor.addFunds(25);
		
		int refund = creditor.refund();
		
		assertEquals(25, refund);
		assertEquals(0, creditor.getAvailableFunds());
	}

}
