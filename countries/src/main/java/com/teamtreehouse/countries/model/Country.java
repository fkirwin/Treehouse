package com.teamtreehouse.countries.model;

public class Country 
{
	private String name;
	private int population;
	private String capitol;
	private String language;
	
	public Country(String name, int population, String capitol, String language) 
	{
		this.name = name;
		this.population = population;
		this.capitol = capitol;
		this.language = language;
	}
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public int getPopulation() 
	{
		return population;
	}
	public void setPopulation(int population) 
	{
		this.population = population;
	}

	public String getCapitol() 
	{
		return capitol;
	}
	public void setCapitol(String capitol) 
	{
		this.capitol = capitol;
	}
	public String getLanguage() 
	{
		return language;
	}
	public void setLanguage(String language) 
	{
		this.language = language;
	}
	
	public String searchifyName()
	{
		if(this.name.contains(" "))
		{
			return this.name.replace(" ", "%20");
		}
		else
			return this.name;
	}

}
