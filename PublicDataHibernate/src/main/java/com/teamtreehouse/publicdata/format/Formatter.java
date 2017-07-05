package com.teamtreehouse.publicdata.format;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.teamtreehouse.publicdata.model.Country;

public class Formatter 
{
	public static String formatData(List<Country> target)
	{
		String table="";
		List<Country> t2 = new ArrayList<Country>(target);
		//Create header
		Field[] fields =Country.class.getDeclaredFields();
		for (Field field : fields) 
		{
			table = table +" | "+ field.getName();
		}
		for (Country country: t2)
		{
			country.toString();
		}
		
		return table;
	}
}
