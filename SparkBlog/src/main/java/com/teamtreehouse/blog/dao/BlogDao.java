package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;

import java.util.List;
import java.util.Set;

public interface BlogDao 
{
    boolean addEntry(BlogEntry blogEntry);
    boolean editEntry(BlogEntry blogEntry, String newContent);
    boolean addComment(BlogEntry blogEntry, Comment comment);
    List<BlogEntry> findAllEntries();
    BlogEntry findEntryBySlug(String slug);
    BlogEntry findEntryByID(String blogEntryID);
	Set<Comment> findAllComments(String blogEntryID);
    
}
