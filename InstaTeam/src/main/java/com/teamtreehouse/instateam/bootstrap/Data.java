package com.teamtreehouse.instateam.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.teamtreehouse.instateam.model.Collaborator;
import com.teamtreehouse.instateam.model.Project;
import com.teamtreehouse.instateam.model.Role;
import com.teamtreehouse.instateam.service.CollaboratorService;
import com.teamtreehouse.instateam.service.CollaboratorServiceImp;
import com.teamtreehouse.instateam.service.ProjectService;
import com.teamtreehouse.instateam.service.ProjectServiceImp;
import com.teamtreehouse.instateam.service.RoleService;
import com.teamtreehouse.instateam.service.RoleServiceImp;

public class Data implements ApplicationRunner
{
	@Autowired
	private CollaboratorService cs;
	@Autowired
	private ProjectService ps;
	@Autowired
	private RoleService rs;
	
	
	public void populateCollaborator()
	{
		Collaborator collaborator = new Collaborator.Builder().withName("Tom").withRole(Rolie).build();
		cs.save(collaborator);
	}
	
	public void populateRole(Role role)
	{
		rs.save(role);
	}
	
	public void populateProject(Project project)
	{
		ps.save(project);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		//Precedence role before collaborator before project
		
	}
	
}
