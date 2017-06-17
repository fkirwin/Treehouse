package com.teamtreehouse.blog.model;

import java.util.Date;

public class Comment 
{
	
	private String text;
	private String creator;
	private Date dateTime;
	

	public Comment(String commentText, String creator) 
	{
		this.text = commentText;
		this.creator =creator;
		this.setDateTime();
	}

	public String getText() {
		return text;
	}

	public void setText(String commentText) 
	{
		this.text = commentText;
	}
	
	public String getCreator() 
	{
		return creator;
	}

	public void setCreator(String creator) 
	{
		this.creator = creator;
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return "Comment [commentText=" + text + "]";
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
