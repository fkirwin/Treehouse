package com.teamtreehouse.instateam.dao;

import java.util.List;

import com.teamtreehouse.instateam.model.Collaborator;

public interface CollaboratorDao
{
    List<Collaborator> findAll();
    Collaborator findById(Long id);
    void save(Collaborator role);
    void delete(Collaborator role);
}
