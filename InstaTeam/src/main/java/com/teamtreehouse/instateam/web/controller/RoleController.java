package com.teamtreehouse.instateam.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.RoleService;

@Controller
public class RoleController
{
	private RoleService roleService;
	
	@RequestMapping("/roles")
    public String listRoles(Model model) 
    {
		List<Role> roles = roleService.findAll();
        return "role/roles";
    }
	
	/*
    @RequestMapping("categories/add")
    public String formNewCategory(Model model) {
        // TODO: Add model attributes needed for new form
        if(!model.containsAttribute("category")) {
            model.addAttribute("category",new Category());
        }
        model.addAttribute("colors", Color.values());
        model.addAttribute("action","/categories");
        model.addAttribute("heading","New Category");
        model.addAttribute("submit","Add");

        return "category/form";
    }
    */
}
