package com.teamtreehouse.instateam.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamtreehouse.instateam.model.Project;

@Repository
public class ProjectDaoImp implements ProjectDao
{
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public List<Project> findAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project findById(Long id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Project project)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Project role)
	{
		// TODO Auto-generated method stub
		
	}
}
