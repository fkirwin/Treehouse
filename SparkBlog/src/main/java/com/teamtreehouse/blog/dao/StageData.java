package com.teamtreehouse.blog.dao;

import java.util.ArrayList;
import java.util.List;

import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;

public class StageData 
{
	public static List<BlogEntry> entries = new ArrayList<BlogEntry>();
	
	public static void createBlogEntries()
	{
		BlogEntry be1 = new BlogEntry("Farting on the first date - Blame it on the Russians", "CNN", "blah");
		BlogEntry be2 = new BlogEntry("War on Christmas", "Fox", "blah");
		BlogEntry be3 = new BlogEntry("We don't care.", "American People", "blah");
		Comment com = new Comment("Mean things", "Mister Troll");
		be1.addComment(com);
		be2.addComment(com);
		be3.addComment(com);
		entries.add(be1);
		entries.add(be2);
		entries.add(be3);
		//entries.forEach(e -> System.out.println(e.addComment("farts")));
		//entries.forEach(e -> e.addComment("Placeholder Comment"));
		
	}
	
}
