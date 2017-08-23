package com.teamtreehouse.instateam.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.RoleService;

@Controller
public class CollaboratorController
{
	@Autowired
	private CollaboratorService collaboratorService;
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping("/collaborators")
    public String listRoles(Model model) 
    {
		
		if(!model.containsAttribute("collaborator")) 
		{
            model.addAttribute("collaborator", new Collaborator());
        }
		List<Collaborator> collaborators = collaboratorService.findAll();
		model.addAttribute("collaborators", collaborators);
		model.addAttribute("action","/collaborators/update");
        model.addAttribute("heading","UpdateCollaborators");
        model.addAttribute("submit","Save");
        
        List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
        
        return "collaborator/collaborators";
    }
	
	// Form for editing an existing GIF
    @RequestMapping(value = "/collaborators/{collaboratorId}/edit")
    public String formEditCollaborator(@PathVariable Long collaboratorId, Model model) {
        model.addAttribute("collaborator",collaboratorService.findById(collaboratorId));
        model.addAttribute("action",String.format("/collaborators/%s",collaboratorId));
        model.addAttribute("heading","Edit Collaborator");
        model.addAttribute("submit","Update");

        return "collaborator/collaborators";
    }

    // Update an existing GIF
    @RequestMapping(value = "/collaborators/{collaboratorId}", method = RequestMethod.POST)
    public String updateGif(Collaborator collaborator, BindingResult result, RedirectAttributes redirectAttributes) {
        collaboratorService.save(collaborator);
       
        return String.format("redirect:collaborator/collaborators");
    }

    @RequestMapping(value = "/collaborators/add", method = RequestMethod.POST)
    public String addCollaborator(@Valid Collaborator collaborator, BindingResult result, RedirectAttributes redirectAttributes) 
    {
        // TODO: Add category if valid data was received
        if(result.hasErrors()) 
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);
            redirectAttributes.addFlashAttribute("collaborator",collaborator);

            return "collaborator/collaborators";
        }
        collaboratorService.save(collaborator);

        return "redirect:/collaborator/collaborators";
    }

}
