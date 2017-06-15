package com.teamtreehouse.blog.model;

import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;

import com.github.slugify.Slugify;

public class BlogEntry 
{
	private String blogEntryID;
	private String title;
	private String creator;
	private String slug;
	private String blogEntryContent;
	private Set<Comment> comments;
	private Date dateTime;
	

	public BlogEntry(String title, String creator, String blogEntryContent) 
    {
		
		this.setBlogEntryID();
		this.setDateTime();
    	this.title = title;
		this.creator = creator;
		this.blogEntryContent = blogEntryContent;
		try 
		{
			Slugify slugify = new Slugify();
			slug = slugify.slugify(this.title);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public boolean addComment(Comment comment) 
    {
        comments.add(comment);
        return false;
    }

	public String getBlogEntryID() 
	{
		return blogEntryID;
	}

	public void setBlogEntryID() 
	{
		this.blogEntryID = new String(RandomStringUtils.random(10));
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getCreator() 
	{
		return creator;
	}

	public void setCreator(String creator) 
	{
		this.creator = creator;
	}

	public String getSlug() 
	{
		return slug;
	}

	public void setSlug(String slug) 
	{
		this.slug = slug;
	}
	
	public String getBlogEntryContent()
	{
		return blogEntryContent;
	}

	public void setBlogEntryContent(String blogEntryContent) 
	{
		this.blogEntryContent = blogEntryContent;
	}
	
    public Date getDateTime() 
    {
		return dateTime;
	}

	public void setDateTime() 
	{
		this.dateTime = new Date();
	}
	
    
    
}
