package com.teamtreehouse.publicdata;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Projections;
import org.hibernate.service.ServiceRegistry;

import com.teamtreehouse.publicdata.annotations.MethodAnnotation;
import com.teamtreehouse.publicdata.data.CountryDAO;
import com.teamtreehouse.publicdata.model.Country;

public class Test 
{
	private static SessionFactory buildSessionFactory()
	{
		final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}

	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) 
	{
		Field[] fields =Country.class.getDeclaredFields();
		Method[] methods = Country.class.getMethods();
		List<Country> countries = new ArrayList<Country>();
		Country c = new Country.CountryBuilder("Freddland").build();
		countries.add(c);
		List<Method> ms = new ArrayList<Method>(Arrays.asList(methods));
		Session session = sessionFactory.openSession();
		PearsonsCorrelation pc = new PearsonsCorrelation();
		List<BigDecimal> internetUsers = session.createCriteria(Country.class).setProjection(Projections.property("internetUsers")).list();
		List<BigDecimal> literacyRate = session.createCriteria(Country.class).setProjection(Projections.property("adultLiteracyRate")).list();
		//internetUsers.forEach(user -> CountryDAO.handleDouble(user));
			
			//pc.computeCorrelationMatrix(data)
		//internetUsers.stream().forEach(System.out::println);
		double[] dList = new double[internetUsers.size()];
		for (BigDecimal bd: internetUsers)
		{
			int spot = dList.length-1;
			//System.out.println(spot);
			//System.out.println(CountryDAO.handleDouble(bd));
			double dVal = CountryDAO.handleDouble(bd);
			dList[spot]= dVal;
			//System.out.println(dVal);	
		}
		System.out.println(dList.length);
		System.out.println(internetUsers.size());
		
	}
}


