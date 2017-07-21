package com.teamtreehouse.publicdata.data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.service.ServiceRegistry;

import com.teamtreehouse.publicdata.model.Country;

public class CountryDAO 
{
	//Session factory
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	public CountryDAO(){}
	
		private static SessionFactory buildSessionFactory()
		{
			final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			return new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		
		@SuppressWarnings("unchecked")
		public static List<Country> fetchAllCountries()
		{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Country.class);
			List<Country> countries = criteria.list();
			session.close();
			return countries;
		}
		
		@SuppressWarnings("unchecked")
		public static BigDecimal fetchMaxInternetUsers()
		{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Country.class).setProjection(Projections.max("internetUsers"));
			BigDecimal users = (BigDecimal) criteria.uniqueResult();
			return users;
		}
		
		@SuppressWarnings("unchecked")
		public static BigDecimal fetchMinInternetUsers()
		{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Country.class).setProjection(Projections.min("internetUsers"));
			BigDecimal users = (BigDecimal) criteria.uniqueResult();
			return users;
		}
		
		@SuppressWarnings("unchecked")
		public static BigDecimal fetchMaxAdultLiteracyRate()
		{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Country.class).setProjection(Projections.max("adultLiteracyRate"));
			BigDecimal users = (BigDecimal) criteria.uniqueResult();
			return users;
		}
		
		@SuppressWarnings("unchecked")
		public static BigDecimal fetchMinAdultLiteracyRate()
		{
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Country.class).setProjection(Projections.min("adultLiteracyRate"));
			BigDecimal users = (BigDecimal) criteria.uniqueResult();
			return users;
		}
		
		
		@SuppressWarnings("unchecked")
		public static double fetchCorrelationCoefficient()
		{
			Session session = sessionFactory.openSession();
			List<BigDecimal> internetUsers = session.createCriteria(Country.class).setProjection(Projections.property("internetUsers")).list();
			List<BigDecimal> literacyRate = session.createCriteria(Country.class).setProjection(Projections.property("adultLiteracyRate")).list();
			double[] iUsers = new double[internetUsers.size()];
			double[] lRate = new double[literacyRate.size()];
			int userSpot = 0;//iUsers.length-1;
			int rateSpot = 0;//lRate.length-1;
			
			for (BigDecimal bd: internetUsers)
			{
				double dVal = CountryDAO.handleDouble(bd);
				iUsers[userSpot]= dVal;
				userSpot++;
			}
			
			for (BigDecimal bd: literacyRate)
			{
				
				double dVal = CountryDAO.handleDouble(bd);
				lRate[rateSpot]= dVal;
				rateSpot++;
			}
			
			double correlation = new PearsonsCorrelation().correlation(iUsers, lRate);
			
			return correlation;
		}
		

		public static Double handleDouble(BigDecimal val) throws NullPointerException
		{
			Double newVal = 0.0;
			if (val==null)
				;
			else
				newVal = val.doubleValue();
			
			return newVal;
		}
		
		public static void editCountryData(Country c)
		{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(c);
			session.getTransaction().commit();
			session.close();
		}
		
		public static void deleteCountryData(Country c)
		{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(c);
			session.getTransaction().commit();
			session.close();
		}
		
		@SuppressWarnings("unused")
		public static void addCountryData(Country c)
		{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(c);
			session.getTransaction().commit();
			session.close();
		}
		
		public static Country findCountryByCode(String code)
		{
			Session session = sessionFactory.openSession();
			Country country = session.get(Country.class, code);
			session.close();
			return country;
		}
}
