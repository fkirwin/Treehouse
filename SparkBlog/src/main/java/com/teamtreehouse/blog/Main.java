package com.teamtreehouse.blog;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import java.util.HashMap;
import java.util.Map;

import com.teamtreehouse.blog.dao.BlogDao;
import com.teamtreehouse.blog.dao.SimpleBlogDao;
import com.teamtreehouse.blog.dao.StageData;
import com.teamtreehouse.blog.model.BlogEntry;
import com.teamtreehouse.blog.model.Comment;

import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.before;
import static spark.Spark.halt;
import static spark.Spark.exception;

public class Main 
{
	private static final String FLASH_MESSAGE_KEY = "flash_message";
	
	private static void setFlashMessage(Request req, String message) 
	{
		req.session().attribute(FLASH_MESSAGE_KEY, message);
	}
	
	private static String getFlashMessage(Request req)
	{
		return (String)  req.session().attribute(FLASH_MESSAGE_KEY);
	}
	
	private static String captureFlashMessage(Request req)
	{
		String message = getFlashMessage(req);
		if(message != null)
		{
			req.session().removeAttribute(FLASH_MESSAGE_KEY);
		}
		return message;
	}
	
	
	/*MAIN*/

	public static void main(String[] args) 
	{
		staticFileLocation("/resources/css");
		BlogDao database = new SimpleBlogDao();
		StageData.createBlogEntries();
		StageData.entries.forEach(e -> database.addEntry(e));
		
		before((req, res)->
		{
			// TODO: send message about redirect;
			if (req.cookie("username")!=null)
			{
				req.attribute("username", req.cookie("username"));
			}
		});
		
		before("/index", (req, res)->
		{
			if (req.attribute("username")==null)
			{
				setFlashMessage(req,"Whoops, please sign in first!");
				res.redirect("/password");
				halt();
			}
		});
		
		before("/", (req, res)->
		{
			// TODO: send message about redirect;
			if (req.attribute("username")==null)
			{
				setFlashMessage(req,"Whoops, please sign in first!");
				res.redirect("/password");
				halt();
			}
		});
		
		before("/detail/:slug/edit", (req, res)->
		{
			// TODO: send message about redirect;
			if (req.attribute("username")==null)
			{
				setFlashMessage(req,"Whoops, please sign in first!");
				res.redirect("/password");
				halt();
			}
		});
		
		before("/new", (req, res)->
		{
			// TODO: send message about redirect;
			if (req.attribute("username")==null)
			{
				setFlashMessage(req,"Whoops, please sign in first!");
				res.redirect("/password");
				halt();
			}
		});
		
		before("/detail", (req, res)->
		{
			// TODO: send message about redirect;
			if (req.attribute("username")==null)
			{
				setFlashMessage(req,"Whoops, please sign in first!");
				res.redirect("/password");
				halt();
			}
		});
		
		get("/", (req, res) -> 
		{
			res.redirect("/index");
			return null;
		});
		
		get("/index", (req, res) -> 
		{
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("flashMessage", captureFlashMessage(req));
            model.put("flashMessage", captureFlashMessage(req));
			model.put("username", req.attribute("username"));
			model.put("entries", database.findAllEntries());
			return new ModelAndView(model,"index.hbs");
		}, 
			new HandlebarsTemplateEngine());
		
		get("/detail/:slug/edit", (req, res) -> 
		{
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("entry", database.findEntryBySlug(req.params(":slug")));
			return new ModelAndView(model,"edit.hbs");
		}, 
			new HandlebarsTemplateEngine());
		
		post("/detail/:slug/edit", (req, res)->
		{
			Map <String, Object> model = new HashMap<>();
			String newContent = req.queryParams("newContent");
			BlogEntry blogEntry = database.findEntryBySlug(req.params(":slug"));
			database.editEntry(blogEntry, newContent);
			res.redirect("/detail/"+blogEntry.getSlug());
			return null;
		});
		
		
		get("/detail/:slug", (req, res) -> 
		{
			Map<String, Object> model = new HashMap<>();
			model.put("entry", database.findEntryBySlug(req.params(":slug")));
			model.put("comments", database.findAllComments(req.params(":slug")));
			return new ModelAndView(model,"detail.hbs");
		}, 
			new HandlebarsTemplateEngine());
		
		get("/detail", (req, res) -> 
		{
			Map<String, Object> model = new HashMap<>();
			return new ModelAndView(model,"detail.hbs");
		}, 
			new HandlebarsTemplateEngine());
		
		get("/new", (req, res) -> 
		{
			Map<String, String> model = new HashMap<String, String>();
			return new ModelAndView(model,"new.hbs");
		}, 
			new HandlebarsTemplateEngine());
		
		post("/new", (req, res)->
		{
			Map <String, Object> model = new HashMap<>();
			String newContent = req.queryParams("newContent");
			String title = req.queryParams("title");
			BlogEntry blogEntry = new BlogEntry(title, req.attribute("username"),newContent);
			database.addEntry(blogEntry);
			res.redirect("/index");
			return null;
		});
		
		get("/password", (req, res) -> 
		{
			Map<String, String> model = new HashMap<String, String>();
			return new ModelAndView(model,"password.hbs");
		}, 
			new HandlebarsTemplateEngine());
		
		post("/password", (req, res)->
		{
			Map <String, String> model = new HashMap<>();
			String username = req.queryParams("username");
			res.cookie("username", username);
			if(username.equals("admin"))
			{
				res.redirect("/index");
			}
			else
			{
				setFlashMessage(req, "Please provide valid password.");
				getFlashMessage(req);
				res.redirect("/password");
			}
			return null;
		});
		
		post("/detail/:slug/addcomment", (req, res)->
		{
			Map <String, Object> model = new HashMap<>();
			String newComment = req.queryParams("comment");
			String creator = req.queryParams("name");
			BlogEntry blogEntry = database.findEntryBySlug(req.params(":slug"));
			database.addComment(blogEntry, new Comment(newComment, creator));
			res.redirect("/detail/"+blogEntry.getSlug());
			return null;
		});
	}

}
