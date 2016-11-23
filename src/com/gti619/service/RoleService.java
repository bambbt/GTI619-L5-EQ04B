package com.gti619.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gti619.daos.RoleDAO;
import com.gti619.model.Role;

@Service
@Transactional
public class RoleService{
     
    @Autowired
    private RoleDAO roleDAO;
 
    public Role getRole(int id) {
        return roleDAO.getRole(id);
    }
 
}