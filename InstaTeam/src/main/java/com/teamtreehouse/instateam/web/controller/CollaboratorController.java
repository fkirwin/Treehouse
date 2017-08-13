package com.teamtreehouse.instateam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtreehouse.instateam.service.CollaboratorService;

@Controller
public class CollaboratorController
{
	private CollaboratorService collaboratorService;
	
	@RequestMapping("/collaborators")
    public String listRoles(Model model) 
    {
        
        return "collaborators";
    }

}
