package com.teamtreehouse.blog;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import java.util.HashMap;
import java.util.Map;

import com.teamtreehouse.blog.dao.BlogDao;
import com.teamtreehouse.blog.dao.SimpleBlogDAO;

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
		if (req.session(false)==null)
		{
			return null;
		}
		if (req.session().attributes().contains(FLASH_MESSAGE_KEY))
		{
			return null;
		}
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
		BlogDao dao = new SimpleBlogDAO();
		
		before((req, res)->
		{
			// TODO: send message about redirect;
			if (req.cookie("username")!=null)
			{
				req.attribute("username", req.cookie("username"));
			}
		});
		
		before("/ideas", (req, res)->
		{
			// TODO: send message about redirect;
			if (req.attribute("username")==null)
			{
				setFlashMessage(req,"Whoops, please sign in first!");
				res.redirect("/");
				halt();
			}
		});
		
		get("/", (req, res) -> 
		{
			Map<String, String> model = new HashMap<String, String>();
			//model.put("username", req.attribute("username"));
			//model.put("flashMessage", captureFlashMessage(req));
			return new ModelAndView(model,"index.hbs");
		}, 
			new HandlebarsTemplateEngine());
		
		get("/index", (req, res) -> 
		{
			Map<String, String> model = new HashMap<String, String>();
			//model.put("username", req.attribute("username"));
			//model.put("flashMessage", captureFlashMessage(req));
			return new ModelAndView(model,"index.hbs");
		}, 
			new HandlebarsTemplateEngine());
		
		get("/edit", (req, res) -> 
		{
			Map<String, String> model = new HashMap<String, String>();
			//model.put("username", req.attribute("username"));
			//model.put("flashMessage", captureFlashMessage(req));
			return new ModelAndView(model,"edit.hbs");
		}, 
			new HandlebarsTemplateEngine());
		
		get("/detail/:slug", (req, res) -> 
		{
			Map<String, String> model = new HashMap<String, String>();
			//model.put("username", req.attribute("username"));
			//model.put("flashMessage", captureFlashMessage(req));
			return new ModelAndView(model,"detail.hbs");
		}, 
			new HandlebarsTemplateEngine());
		
		get("/new", (req, res) -> 
		{
			Map<String, String> model = new HashMap<String, String>();
			//model.put("username", req.attribute("username"));
			//model.put("flashMessage", captureFlashMessage(req));
			return new ModelAndView(model,"new.hbs");
		}, 
			new HandlebarsTemplateEngine());
		
		get("/password", (req, res) -> 
		{
			Map<String, String> model = new HashMap<String, String>();
			//model.put("username", req.attribute("username"));
			//model.put("flashMessage", captureFlashMessage(req));
			return new ModelAndView(model,"password.hbs");
		}, 
			new HandlebarsTemplateEngine());
		/*
		get("/",(req, res) ->
		{
		}
		}
		);
		get("/new", (req, res) ->);
		get("/detail", (req, res) ->);
		get("/index", (req, res) ->);
		*/
	}

}
