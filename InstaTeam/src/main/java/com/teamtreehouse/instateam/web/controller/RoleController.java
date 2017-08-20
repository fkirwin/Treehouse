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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.RoleService;

@Controller
public class RoleController
{
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/roles")
    public String listRoles(Model model) 
    {
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		
        return "role/roles";
    }
	
	@RequestMapping("roles/{roleId}/editrole")
    public String formEditRole(@PathVariable Long roleId, Model model) {
        // TODO: Add model attributes needed for new form
        if(!model.containsAttribute("role")) {
        	Role role = roleService.findById(roleId);
            model.addAttribute("role", role);
        }
        model.addAttribute("action",String.format("/roles/%s",roleId));
        model.addAttribute("heading","Edit Role");
        model.addAttribute("submit","Update");

        return "role/editrole";
    }

    // Update an existing category
    @RequestMapping(value = "/roles/{roleId}", method = RequestMethod.POST)
    public String updateRole(@Valid Role role, Model model) {
    	
        roleService.save(role);

        // TODO: Redirect browser to /categories
        return "redirect:/roles";
    }

    
    @RequestMapping("roles/addrole")
    public String formAddRole(Model model) {
        // TODO: Add model attributes needed for new form
        if(!model.containsAttribute("role")) {
            model.addAttribute("role", new Role());
        }

        return "role/addrole";
    }
	
	/*
	 * 
	 * 
	 * @RequestMapping("roles/{roleId}/editrole")
    public String formEditRole(@PathVariable Long roleId, Model model) {
        // TODO: Add model attributes needed for new form
        if(!model.containsAttribute("role")) {
            model.addAttribute("role", roleService.findById(roleId));
        }
        model.addAttribute("action",String.format("/roles/%s",roleId));
        model.addAttribute("heading","Edit Role");
        model.addAttribute("submit","Update");

        return "role/editrole";
    }

    // Update an existing category
    @RequestMapping(value = "/roles/{roleId}", method = RequestMethod.POST)
    public String updateRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes) {
        // TODO: Update category if valid data was received
        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);

            // Add  category if invalid was received
            redirectAttributes.addFlashAttribute("role",role);
            roleService.save(role);
            // Redirect back to the form
            return String.format("redirect:/roles",role.getId());
        }

        roleService.save(role);

        // TODO: Redirect browser to /categories
        return "redirect:/roles";
    }

	 * 
	 * 
	 * @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String addCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        // TODO: Add category if valid data was received
        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);

            // Add  category if invalid was received
            redirectAttributes.addFlashAttribute("category",category);

            // Redirect back to the form
            return "redirect:/categories/add";
        }

        categoryService.save(category);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Category successfully added!", FlashMessage.Status.SUCCESS));

        // TODO: Redirect browser to /categories
        return "redirect:/categories";
    }
	 * 
	 * 
	 * 
	 * 
	 *     @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.POST)
    public String updateCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        // TODO: Update category if valid data was received
        if(result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);

            // Add  category if invalid was received
            redirectAttributes.addFlashAttribute("category",category);

            // Redirect back to the form
            return String.format("redirect:/categories/%s/edit",category.getId());
        }

        categoryService.save(category);

        redirectAttributes.addFlashAttribute("flash",new FlashMessage("Category successfully updated!", FlashMessage.Status.SUCCESS));

        // TODO: Redirect browser to /categories
        return "redirect:/categories";
    }
	 * 
	 * 
	 *     @RequestMapping("roles/{roleId}/editrole")
    public String formEditRole(@PathVariable Long roleId, Model model) {
        // TODO: Add model attributes needed for new form
        if(!model.containsAttribute("role")) {
        	Role role = roleService.findById(roleId);
            model.addAttribute("role",role);
        }

        return "role/editrole";
    }
	 * 
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
