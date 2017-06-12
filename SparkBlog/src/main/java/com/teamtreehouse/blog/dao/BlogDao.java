package com.teamtreehouse.blog.dao;

import com.teamtreehouse.blog.model.BlogEntry;

import java.util.List;

public interface BlogDao 
{
    boolean addEntry(BlogEntry blogEntry);
    boolean deleteEntry(BlogEntry blogEntry);
    boolean editEntry(BlogEntry blogEntry, String newContent);
    List<BlogEntry> findAllEntries();
    BlogEntry findEntryBySlug(String slug);
    BlogEntry findEntryByID(String blogEntryID);
    
}
