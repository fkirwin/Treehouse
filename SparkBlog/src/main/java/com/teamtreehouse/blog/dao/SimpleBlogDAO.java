package com.teamtreehouse.blog.dao;

import java.util.ArrayList;
import java.util.List;

import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.NotFoundException;

public class SimpleBlogDAO implements BlogDao
{
	
	List<BlogEntry> blogEntries;

	public SimpleBlogDAO() 
	{
		blogEntries = new ArrayList<>();
	}

	@Override
	public boolean addEntry(BlogEntry blogEntry) 
	{
		return blogEntries.add(blogEntry);
	}

	@Override
	public List<BlogEntry> findAllEntries() 
	{
		// TODO Auto-generated method stub
		return new ArrayList<>(blogEntries);
	}

	@Override
	public BlogEntry findEntryBySlug(String slug) 
	{
		return blogEntries.stream()
				.filter(blogEntry -> blogEntry.getSlug().equals(slug))
				.findFirst()
				.orElseThrow(NotFoundException::new);
	}
	
	@Override
	public BlogEntry findEntryByID(String blogEntryID) 
	{
		return blogEntries.stream()
				.filter(blogEntry -> blogEntry.getBlogEntryID().equals(blogEntryID))
				.findFirst()
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean deleteEntry(BlogEntry blogEntry) 
	{
		// TODO Auto-generated method stub
		return false;
	}

}
