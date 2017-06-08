package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;

import java.util.List;

public interface BlogDao 
{
    boolean addEntry(BlogEntry blogEntry);
    boolean deleteEntry(BlogEntry blogEntry);
    List<BlogEntry> findAllEntries();
    BlogEntry findEntryBySlug(String slug);
    BlogEntry findEntryByID(String blogEntryID);
    
}
