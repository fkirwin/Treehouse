package com.teamtreehouse.publicdata.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.teamtreehouse.publicdata.data.CountryDAO;
import com.teamtreehouse.publicdata.format.Formatter;

public class Menu 
{
	private static List<MenuOptions> menuOptions;

	
	public Menu()
	{
		Menu.menuOptions= Arrays.asList(MenuOptions.values());
	}
	
	
	public static String getUserInputChoice()
	{
		//Print opts
		System.out.println("Please select valid option: ");
		menuOptions.forEach(System.out::println);
		
		//Get Response
		Scanner scan = new Scanner(System.in);
		String response = scan.next();
		
		for(MenuOptions mo : menuOptions)
		{
			if(mo.toString().equals(response))
			{
				break;
			}
		}
		
		return response;
		
	}
	
	
	public void runMenu()
	{
		boolean runMenu = true;
		String response = getUserInputChoice();
		
		while(runMenu)
		{
			switch(response)
			{
				case "VIEWTABLE":
					System.out.println(Formatter.formatDataAsTable(CountryDAO.fetchAllCountries()));
					response = getUserInputChoice();
			        break;
				case "VIEWSTATS":
					response = getUserInputChoice();
					break;
				case "ADDCOUNTRY":
					response = getUserInputChoice();
					break;
				case "UPDATECOUNTRY":
					response = getUserInputChoice();
					break;
				case "DELETECOUNTRY":
					response = getUserInputChoice();
					break;
				case "EXIT":
					runMenu=false;
					break;
							
			     default:
			    	response = getUserInputChoice();
			        break;
			  }
			
		}
		
	}
}