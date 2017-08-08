package com.teamtreehouse.instateam.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamtreehouse.instateam.model.Role;

@Repository
public class RoleDaoImp implements RoleDao
{
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public List<Role> findAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findById(Long id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Role role)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Role role)
	{
		// TODO Auto-generated method stub
		
	}
}
