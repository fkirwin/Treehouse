package com.teamtreehouse.publicdata.calculator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.hibernate.internal.util.compare.ComparableComparator;

import com.teamtreehouse.publicdata.model.Country;

//Class to handle calcs outside of hibernate.  Written but not used.
public class Calculator 
{
	public static BigDecimal calculateMaximumInternetUsers(List<Country> countries)
	{
		BigDecimal topDog = new BigDecimal("0.0");
		for (Country c : countries)
		{
			BigDecimal users = c.getInternetUsers()==null?new BigDecimal(0.0):c.getInternetUsers();
			if(users.compareTo(topDog)>=1)
			{
				topDog=c.getInternetUsers();
			}
		}
		
		return topDog;
		
	}
	
	public static String calculateMinimumInternetUsers(List<Country> countries)
	{
		return null;
		
	}
	
	public static BigDecimal calculateMaximumAdultLiteracyRate(List<Country> countries)
	{
		BigDecimal topDog = new BigDecimal("0.0");
		for (Country c : countries)
		{
			BigDecimal users = c.getAdultLiteracyRate()==null?new BigDecimal(0.0):c.getAdultLiteracyRate();
			if(users.compareTo(topDog)>=1)
			{
				topDog=c.getAdultLiteracyRate();
			}
		}
		
		return topDog;
		
	}
	
	public static String calculateMinimumAdultLiteracyRate(List<Country> countries)
	{
		return null;
		
	}
	
	public static String calculateCorrelation(List<Country> countries)
	{
		return null;
		
	}

}
