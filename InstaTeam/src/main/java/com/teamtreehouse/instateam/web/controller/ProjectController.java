package com.teamtreehouse.instateam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.ProjectService;
import com.teamtreehouse.instateam.service.RoleService;

@Controller
public class ProjectController
{
	private RoleService roleService;
	private CollaboratorService collaboratorService;
	private ProjectService projectService;
	
	@RequestMapping("/index")
    public String projectIndex(Model model) 
    {   
        return "index";
    }
	
	@RequestMapping("/edit_project")
    public String editProject(Model model) 
    {   
        return "edit_project";
    }
	
	@RequestMapping("/project_detail")
    public String getProjectDetails(Model model) 
    {   
        return "project_detail";
    }
	
	@RequestMapping("/project_collaborators")
    public String getProjectCollaborators(Model model) 
    {   
        return "project_collaborators";
    }
    
}
