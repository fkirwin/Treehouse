package com.teamtreehouse.blog.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;
import com.teamtreehouse.blog.model.NotFoundException;

public class SimpleBlogDao implements BlogDao
{
	
	List<BlogEntry> blogEntries;

	public SimpleBlogDao() 
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
	public boolean editEntry(BlogEntry blogEntry, String newContent) {
		boolean editStatus = false;
		
		for(BlogEntry be : blogEntries)
		{
			if(blogEntry.equals(be))
			{
				blogEntry.setBlogEntryContent(newContent);
				editStatus = true;
			}
		}
		
		return editStatus;
	}

	@Override
	public boolean addComment(BlogEntry blogEntry, Comment comment) {
		boolean editStatus = false;
		
		for(BlogEntry be : blogEntries)
		{
			if(blogEntry.equals(be))
			{
				blogEntry.addComment(comment);
				editStatus = true;
			}
		}
		
		return editStatus;
	}

	@Override
	public Set<Comment> findAllComments(String slug) {
		
		BlogEntry blogEntryForComments = (BlogEntry) blogEntries.stream()
				.filter(blogEntry -> blogEntry.getSlug().equals(slug))
				.findFirst()
				.orElseThrow(NotFoundException::new);
		return blogEntryForComments.getComments();
	}




}
