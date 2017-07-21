package com.teamtreehouse.publicdata.format;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.NumberFormatter;

import com.teamtreehouse.publicdata.annotations.FieldAnnotation;
import com.teamtreehouse.publicdata.annotations.MethodAnnotation;
import com.teamtreehouse.publicdata.model.Country;

public class Formatter 
{
	static private String separator="|";
	static private String header = "";
	static private String table="\n";

	public static String formatDataAsTable(List<Country> target)
	{
		return formatReportHeader(target) + formatReportBody(target);
	}
	
	public static String formatReportHeader(List<Country> target)
	{
		Integer centerBoundary = formatBoundary(target);
		Field[] fields =orderFields();
		
		for (Field field : fields) 
		{
			int fieldSize = (field.getName().length());
			int headerField = (int) centerBoundary-fieldSize;
			String boundary = String.format("%"+(headerField)+"s", " ");
			header = header + separator + field.getName() + boundary;
		}
		return header;
	}
	
	public static String formatReportBody(List<Country> target)
	{
		Integer centerBoundary = formatBoundary(target);
		Method[] methods = orderMethods();
		
		for (Country country: target)
		{
			for(Method m : methods)
			{
				boolean testMethod = m.isAnnotationPresent(MethodAnnotation.class);
				if(testMethod && m.getReturnType().equals(String.class))
					{
						try 
						{
							String curVal = nullConverter((String) m.invoke(country));
							int dataField = (int) centerBoundary-curVal.length();;
							String boundary = String.format("%"+(dataField)+"s", " ");
							table = table + separator + curVal + boundary;
						} 
						catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
						{
								e.printStackTrace();
						}
					}
				else if (testMethod && m.getReturnType().equals(BigDecimal.class))
				{
					try
					{
						String curVal = m.invoke(country)==null ? "--": String.valueOf( ((BigDecimal) m.invoke(country)).setScale(2, RoundingMode.CEILING));
						int dataField = (int) centerBoundary-curVal.length();
						String boundary = String.format("%"+(dataField)+"s", " ");
						table = table + separator + curVal + boundary;
					}
					catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
					{
							e.printStackTrace();
					}
				}
			}
			table += "\n";
		}
		return table;
	}
	
	public static Integer formatBoundary(List<Country> target)
	{
		int largestRowSize=0;
		for (Country country : target)
		{
			if (country.getName().length()>largestRowSize)
				{largestRowSize = country.getName().length();}
		}
		return largestRowSize;
	}
	
	public static String nullConverter(String subject)
	{
		if(subject.equals(null)||subject==null||subject.isEmpty())
			return "--";
		else
			return String.valueOf(subject);
	}
	
	public static Field[] orderFields()
	{
		Field[] fields =Country.class.getDeclaredFields();
		Field[] orderedFields = new Field[fields.length];
		
		for(Field field : fields)
		{
			FieldAnnotation fa = field.getAnnotation(FieldAnnotation.class);
			orderedFields[fa.sortOrder()]=field;
		}
		
		return orderedFields;
	}
	
	public static Method[] orderMethods()
	{
		Method[] methods =Country.class.getMethods();
		Method[] orderedMethods = new Method[1];
		
		for(Method method : methods)
		{
			MethodAnnotation ma = method.getAnnotation(MethodAnnotation.class);
			if(method.getAnnotation(MethodAnnotation.class)==null)
				;
			else
				Arrays.copyOf(orderedMethods, orderedMethods.length+1);
				orderedMethods[ma.sortOrder()]=method;
		}
		
		return orderedMethods;
	}
}


/*
String code = country.getCode().toString();
String name = country.getName().toString();
String internetUsers = country.getInternetUsers()==null ? null 
		: String.valueOf(country.getInternetUsers().setScale(2, RoundingMode.CEILING));
String adultLiteracyRate = country.getAdultLiteracyRate()==null ? null 
		: String.valueOf(country.getAdultLiteracyRate().setScale(2, RoundingMode.CEILING));


table = table 
		+boundary.toString()+ code
		+boundary.toString()+ name
		+boundary.toString()+ internetUsers
		+boundary.toString()+ adultLiteracyRate 
		+  "\n";
		*/

//header = header +String.format("|%s", boundary)+ field.getName();

//header = header + boundary.insert(boundary.length()/2,field.getName());


/*
for (Country country: t2)
{
	for(Method m : methods)
	{
		boolean testMethod = m.isAnnotationPresent(MethodAnnotation.class);
		
		if(testMethod && m.getReturnType().equals(String.class))
			{
				try 
				{
					String curVal = (String) m.invoke(country);
					Integer adjuster = curVal==null ? Integer.valueOf(0) : curVal.length();
					int fieldSize = (curVal.length());
					int dataField = (int) fieldSize%2==0?(((centerBoundary/2)-(fieldSize/2))):(((centerBoundary/2)-((fieldSize-1)/2)));
					String boundary = String.format("%"+(dataField)+"s", " ");
					System.out.println(curVal + " "+ adjuster+" "+dataField);
					table = table + "|" + boundary + m.invoke(country) + boundary;
				} 
				catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
				{
						e.printStackTrace();
				}
			}
		else if (testMethod && m.getReturnType().equals(BigDecimal.class))
		{
			try
			{
				String curVal = m.invoke(country)==null ? null : String.valueOf( ((BigDecimal) m.invoke(country)).setScale(2, RoundingMode.CEILING));
				Integer adjuster = curVal==null ? Integer.valueOf(0) : curVal.length();
				int dataField = adjuster%2==0?(((centerBoundary/2)-(adjuster/2))):(((centerBoundary/2)-((adjuster-1)/2)));
				String boundary = String.format("%"+(dataField)+"s", " ");
				System.out.println(curVal + " "+ adjuster+" "+dataField);
				table = table + "|" + boundary + curVal + boundary;
			}
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
			{
					e.printStackTrace();
			}
		}
	
	}
*/