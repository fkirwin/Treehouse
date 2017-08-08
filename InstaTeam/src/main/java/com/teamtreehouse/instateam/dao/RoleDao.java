package com.teamtreehouse.instateam.dao;

import java.util.List;

import com.teamtreehouse.instateam.model.Role;

public interface RoleDao
{
    List<Role> findAll();
    Role findById(Long id);
    void save(Role role);
    void delete(Role role);
}
