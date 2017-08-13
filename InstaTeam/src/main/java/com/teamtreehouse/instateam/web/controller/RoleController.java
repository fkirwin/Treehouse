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
        
        return "role/roles";
    }
}
