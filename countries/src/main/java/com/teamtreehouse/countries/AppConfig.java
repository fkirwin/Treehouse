package com.teamtreehouse.countries;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@EnableAutoConfiguration
public class AppConfig 
{
	public static void main(String[] args)
	{
		SpringApplication.run(AppConfig.class, args);
	}
}
