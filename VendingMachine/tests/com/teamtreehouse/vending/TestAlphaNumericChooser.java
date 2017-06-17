package com.teamtreehouse.vending;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestAlphaNumericChooser {

	private AlphaNumericChooser chooser;
	
	@Before
	public void setUp() throws Exception {
		chooser = new AlphaNumericChooser(26,10);
	}

	@Test
	public void validInputReturnsProperLocation() throws Exception 
	{
		AlphaNumericChooser.Location loc = chooser.locationFromInput("B4");
		assertEquals("Proper row", 1, loc.getRow());
		assertEquals("Proper column", 3, loc.getColumn());
	}
	
	@Test(expected = InvalidLocationException.class)
	public void choosingWrongInputIsNotAllowed() throws Exception
	{
		chooser.locationFromInput("WRONG");
	}
	
	@Test(expected = InvalidLocationException.class)
	public void choosingLargerThanMaxIsNotAllowed() throws Exception
	{
		chooser.locationFromInput("B52");
	}

}
