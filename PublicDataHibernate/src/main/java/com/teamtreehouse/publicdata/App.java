package com.teamtreehouse.publicdata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.lang.model.element.AnnotationValue;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Order;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;

import com.teamtreehouse.publicdata.annotations.FieldAnnotation;
import com.teamtreehouse.publicdata.annotations.MethodAnnotation;
import com.teamtreehouse.publicdata.calculator.Calculator;
import com.teamtreehouse.publicdata.data.CountryDAO;
import com.teamtreehouse.publicdata.format.Formatter;
import com.teamtreehouse.publicdata.menu.Menu;
import com.teamtreehouse.publicdata.model.Country;

public class App 
{
	
	public static void main(String[] args) 
	{
		run();
		//System.out.println(Formatter.formatDataAsTable(CountryDAO.fetchAllCountries()));
		
	}
	
	public static void run()
	{
		Menu menu = new Menu();
		menu.runMenu();
	}

}
