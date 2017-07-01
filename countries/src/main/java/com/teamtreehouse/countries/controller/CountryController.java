package com.teamtreehouse.countries.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtreehouse.countries.data.CountryRepository;
import com.teamtreehouse.countries.model.Country;




@Controller
public class CountryController 
{
	@Autowired
	private CountryRepository countryRepository;
	
	@RequestMapping(value = "/")
	public String listCountries(ModelMap modelMap)
	{
		List<Country> allCountries = countryRepository.getAllCountries();
		modelMap.put("countries", allCountries);
		return "home";
	}
	
	@RequestMapping(value = "/country/{name}")
	public String listCountry(@PathVariable String name, ModelMap modelMap)
	{
		Country country  =  countryRepository.getCountryByName(name);
		modelMap.put("country", country);
		return "countries";
	}
	
}
