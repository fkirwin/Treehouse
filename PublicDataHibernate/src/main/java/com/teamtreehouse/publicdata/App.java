package com.teamtreehouse.publicdata;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;

import com.teamtreehouse.publicdata.format.Formatter;
import com.teamtreehouse.publicdata.model.Country;

public class App 
{

	//Session factory
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory()
	{
		final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		return new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	@SuppressWarnings("unchecked")
	private static List<Country> fetchAllCountries()
	{
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Country.class);
		List<Country> countries = criteria.list();
		session.close();
		return countries;
	
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//fetchAllCountries().stream().forEach(System.out::println);
		System.out.println(Formatter.formatData(fetchAllCountries()));
	}

}
