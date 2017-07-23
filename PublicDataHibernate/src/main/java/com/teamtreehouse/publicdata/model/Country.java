package com.teamtreehouse.publicdata.model;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.teamtreehouse.publicdata.annotations.FieldAnnotation;
import com.teamtreehouse.publicdata.annotations.MethodAnnotation;


@Entity
public class Country 
{
	@Id
	@Column
	@FieldAnnotation(sortOrder=0)
	private String code;
	@Column
	@FieldAnnotation(sortOrder=1)
	private String name;
	@Column
	@FieldAnnotation(sortOrder=2)
	private BigDecimal internetUsers;
	@Column
	@FieldAnnotation(sortOrder=3)
	private BigDecimal adultLiteracyRate;

	public Country(){}
	
	 public Country(CountryBuilder builder) 
	 {
	        this.code = builder.code;
	        this.name = builder.name;
	        this.internetUsers = builder.internetUsers;
	        this.adultLiteracyRate = builder.adultLiteracyRate;
	 }

	 @MethodAnnotation(sortOrder=0)
	public String getCode() 
	{
		return code;
	}

	public void setCode(String code) 
	{
		this.code = code;
	}

	@MethodAnnotation(sortOrder=1)
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	@MethodAnnotation(sortOrder=2)
	public BigDecimal getInternetUsers() 
	{
		return internetUsers;
	}

	public void setInternetUsers(BigDecimal internetUsers) 
	{
		this.internetUsers = internetUsers;
	}

	@MethodAnnotation(sortOrder=3)
	public BigDecimal getAdultLiteracyRate() 
	{
		return adultLiteracyRate;
	}

	public void setAdultLiteracyRate(BigDecimal adultLiteracyRate) 
	{
		this.adultLiteracyRate = adultLiteracyRate;
	}

	@Override
	public String toString() 
	{
		return "Country [code=" + code + ", name=" + name + ", internetUsers=" + internetUsers + ", adultLiteracyRate="
				+ adultLiteracyRate + "]";
	};
	
	 public static class CountryBuilder 
	 {
	        private String code;
	        private String name;
	        private BigDecimal internetUsers;
	        private BigDecimal adultLiteracyRate;

	        public CountryBuilder(String code) 
	        {
	            this.code = code;
	        }

	        public CountryBuilder withName(String name) 
	        {
	            this.name = name;
	            return this;
	        }

	        public CountryBuilder withInternetUsers(BigDecimal internetUsers) 
	        {
	            this.internetUsers = internetUsers;
	            return this;
	        }
	        
	        public CountryBuilder withLiteracy(BigDecimal adultLiteracyRate) 
	        {
	            this.adultLiteracyRate = adultLiteracyRate;
	            return this;
	        }

	        public Country build() 
	        {
	            return new Country(this);
	        }
	 }
}
