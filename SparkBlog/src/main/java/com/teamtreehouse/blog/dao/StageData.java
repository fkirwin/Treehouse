package com.teamtreehouse.blog.dao;

import java.util.ArrayList;
import java.util.List;

import com.teamtreehouse.blog.model.BlogEntry;

public class StageData 
{
	public static List<BlogEntry> entries = new ArrayList<BlogEntry>();
	
	public static void createBlogEntries()
	{
		BlogEntry be1 = new BlogEntry("Farting on the first date - Blame it on the Russians", "CNN", "blah", null);
		BlogEntry be2 = new BlogEntry("War on Christmas", "Fox", "blah", null);
		BlogEntry be3 = new BlogEntry("We don't care.", "American People", "blah", null);
		entries.add(be1);
		entries.add(be2);
		entries.add(be3);
	}
	
}
