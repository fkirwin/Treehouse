package com.teamtreehouse.instateam.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
public class Project
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    
    @Null
    private String description;
    
    @Null
    private String status;
    
    @ManyToMany()
    private List<Role> rolesNeeded = new ArrayList<>();
    
    @ManyToMany()
    private List<Collaborator> collaborators = new ArrayList<>();

    Project(){};
    
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public List<Role> getRolesNeeded()
	{
		return rolesNeeded;
	}

	public void setRolesNeeded(List<Role> rolesNeeded)
	{
		this.rolesNeeded = rolesNeeded;
	}

	public List<Collaborator> getCollaborators()
	{
		return collaborators;
	}

	public void setCollaborators(List<Collaborator> collaborators)
	{
		this.collaborators = collaborators;
	}
    
    
}