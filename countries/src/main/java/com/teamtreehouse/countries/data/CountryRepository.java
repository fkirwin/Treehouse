package com.teamtreehouse.countries.data;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.teamtreehouse.countries.model.Country;

@Component
public class CountryRepository 
{

	private static final List<Country> ALL_COUNTRIES = Arrays.asList(
	new Country("Afghanistan", 31000000, "Kabul", "Pashto"),
	new Country("Ireland", 4800000, "Dublin", "English"),
	new Country("Costa Rica", 4800000, "San Jose", "Spanish"),
	new Country("France", 66000000, "Paris", "French"),
	new Country("China", 1500000000, "Beijing", "Chinese")
	);

	public List<Country> getAllCountries()
	{
		return ALL_COUNTRIES;
	}
	
	public Country getCountryByName(String name)
	{
		Country country = null;
		for(Country c: ALL_COUNTRIES)
		{
			String cNameAlternate = c.searchifyName().toString();
			String cName = c.getName().toString();
			if(cName.equals(name)|| cNameAlternate.equals(name))
				country = c;
		}
		return country;
	}
}
