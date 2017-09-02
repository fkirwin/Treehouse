package com.teamtreehouse.instateam.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.ProjectService;
import com.teamtreehouse.instateam.service.RoleService;

@Controller
public class ProjectController
{
	@Autowired
	private RoleService roleService;
	@Autowired
	private CollaboratorService collaboratorService;
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/index")
    public String projectIndex(Model model) 
    {   
		if(!model.containsAttribute("project")) 
		{
            model.addAttribute("project", new Project());
        }

		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);

		List<Collaborator> collaborators = collaboratorService.findAll();
		model.addAttribute("collaborators", collaborators);
		
		List<Project> projects = projectService.findAll();
		model.addAttribute("projects",projects);
		
        return "project/index";
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
