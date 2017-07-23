package com.teamtreehouse.publicdata.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.teamtreehouse.publicdata.data.CountryDAO;
import com.teamtreehouse.publicdata.format.Formatter;
import com.teamtreehouse.publicdata.model.Country;
import com.teamtreehouse.publicdata.model.Country.CountryBuilder;

public class Menu 
{
	private static List<MenuOptions> menuOptions;

	
	public Menu()
	{
		Menu.menuOptions= Arrays.asList(MenuOptions.values());
	}
	
	
	public static String getUserMenuChoice(Scanner scan)
	{
		//Print opts
		System.out.println("Please select valid option: ");
		menuOptions.forEach(System.out::println);
		
		//Get Response
		String response = scan.next();
		
		for(MenuOptions mo : menuOptions)
		{
			if(mo.toString().equalsIgnoreCase("EXIT"))
			{
				System.exit(0);
			}
			else if(mo.toString().equalsIgnoreCase(response))
			{
				break;
			}
		}
		
		return response.toUpperCase();
		
	}
	
	public void updateCountry(String countryCode, Scanner scan)
	{ 
		System.out.println("Enter country code for country you would like to update:");
		countryCode = scan.next();
		System.out.println("Select the field to update - Name, InternetUsers, LiteracyRate:");
		String fieldToUpdate = scan.next();
		Country target = retrieveCountryCode(countryCode, scan);
		if(fieldToUpdate.equalsIgnoreCase("name"))
		{
			System.out.println("Please enter updated name:");
			scan.nextLine();
			String newName = scan.nextLine();
			target.setName(newName);
			CountryDAO.editCountryData(target);
		}
		else if (fieldToUpdate.equalsIgnoreCase("internetusers"))
		{
			System.out.println("Please enter updated user number:");
			String newUserNumber = scan.next();
			target.setInternetUsers(new BigDecimal(newUserNumber));
			CountryDAO.editCountryData(target);
		}
		else if (fieldToUpdate.equalsIgnoreCase("literacyrate"))
		{
			System.out.println("Please enter updated literacy rate:");
			String newLiteracyRate = scan.next();
			target.setAdultLiteracyRate(new BigDecimal(newLiteracyRate));
			CountryDAO.editCountryData(target);
		}
		else
		{
			System.out.println("Invalid choice, heading back to main menu.");
		}
	}
	
	public void addCountry(String countryCode, String countryName, String countryInternetUsers, String countryLiteracy, Scanner scan)
	{
		System.out.println("Enter country code.  Please use three letters:");
		countryCode = scan.next();
		if (countryCode.equals(null)||countryCode.isEmpty())
		{
			System.out.println("Enter country code.  Please use three letters: \n");
			countryCode = scan.next();
		}
		System.out.println("Enter country name:");
		scan.nextLine();
		countryName = scan.nextLine();
		if (countryName.equals(null)||countryName.isEmpty())
		{
			System.out.println("Enter country name:");
			countryName = scan.nextLine();
		}
		System.out.println("Enter country literacy rate in decimal format:");
		countryLiteracy = scan.next();
		if (countryLiteracy.equals(null)||countryLiteracy.isEmpty())
		{
			System.out.println("Enter country literacy rate in decimal format:");
			countryLiteracy = scan.next();
		}
		System.out.println("Enter country internet user rate in decimal format:");
		countryInternetUsers = scan.next();
		if (countryInternetUsers.equals(null)||countryInternetUsers.isEmpty())
		{
			System.out.println("Enter country internet user rate in decimal format:");
			countryInternetUsers = scan.next();
		}
		Country c = new Country.CountryBuilder(countryCode)
				.withName(countryName)
				.withLiteracy(new BigDecimal(countryLiteracy))
				.withInternetUsers(new BigDecimal(countryInternetUsers))
				.build();
		CountryDAO.addCountryData(c);
		countryCode = null;
		countryName = null;
		countryLiteracy =null;
		countryInternetUsers =null;
	}
	
	public void deleteCountry(String countryCode, Scanner scan)
	{
		System.out.println("Enter country code for country you would like to delete:");
		countryCode = scan.next();
		Country target = retrieveCountryCode(countryCode, scan);
		CountryDAO.deleteCountryData(target);
	}
	
	public Country retrieveCountryCode(String countryCode, Scanner scan)
	{
		Country target;
		try
		{
			CountryDAO.findCountryByCode(countryCode);
			target = CountryDAO.findCountryByCode(countryCode);
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Enter valid code");
			System.out.println("Enter country code for country you would like to update:");
			countryCode = scan.next();
			CountryDAO.findCountryByCode(countryCode);
			target = CountryDAO.findCountryByCode(countryCode);
		}
		return target;
	}
	
	public void runMenu()
	{
		Scanner scan = new Scanner(System.in);
		boolean runMenu = true;
		String response = getUserMenuChoice(scan);
		String countryCode = null;
		String countryName= null;
		String countryLiteracy= null;
		String countryInternetUsers =null;
		
		while(runMenu)
		{
			switch(response)
			{
				case "VIEWTABLE":
					System.out.println(Formatter.formatDataAsTable(CountryDAO.fetchAllCountries()));
					response = getUserMenuChoice(scan);
			        break;
				case "VIEWSTATS":
					System.out.println(Formatter.formatStats(CountryDAO.fetchAllCountries()));
					response = getUserMenuChoice(scan);
					break;
				case "ADDCOUNTRY":
					addCountry(countryCode, countryName, countryInternetUsers, countryLiteracy, scan);
					response = getUserMenuChoice(scan);
					break;
				case "UPDATECOUNTRY":
					updateCountry(countryCode, scan);
					response = getUserMenuChoice(scan);
					break;
				case "DELETECOUNTRY":
					deleteCountry(countryCode, scan);
					response = getUserMenuChoice(scan);
					break;
				case "EXIT":
					runMenu=false;
					break;
							
			     default:
			    	response = getUserMenuChoice(scan);
			        break;
			  }
			
		}
		
	}
}