package com.teamtreehouse.instateam.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Collaborator
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    
    @Null
    @ManyToOne
    private Role role;
    
    
    Collaborator(){}

	public Collaborator(Builder builder)
	{
		this.name=builder.name;
		this.role=builder.role;
	}

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

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	};
	
	public static class Builder
	{
        private Role role;
        private String name;

        public Builder() 
        {
            ;
        }

        public Builder withName(String name) 
        {
            this.name = name;
            return this;
        }
        
        public Builder withRole(Role role)
        {
        	this.role = role;
            return this;
        }
        
        public Collaborator build() 
        {
            return new Collaborator(this);
        }
	}
    
    
}
