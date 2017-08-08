package com.teamtreehouse.instateam.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamtreehouse.instateam.model.Collaborator;

@Repository
public class CollaboratorDaoImp implements CollaboratorDao
{
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public List<Collaborator> findAll()
	{
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Collaborator> criteria = builder.createQuery(Collaborator.class);
        criteria.from(Collaborator.class);
        List<Collaborator> collaborators = session.createQuery(criteria).getResultList();
        session.close();

        return collaborators;
	}

	@Override
	public Collaborator findById(Long id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Collaborator role)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Collaborator role)
	{
		// TODO Auto-generated method stub
		
	}
	
	

}
