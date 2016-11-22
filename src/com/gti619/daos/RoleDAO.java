package com.gti619.daos;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gti619.model.Role;

@Repository
public class RoleDAO {
     
    @Autowired
    private SessionFactory sessionFactory;
     
    private Session getCurrentSession() {
        return  sessionFactory.getCurrentSession();
    }
 
    public Role getRole(int id) {
        Role role = (Role) getCurrentSession().load(Role.class, id);
        return role;
    }
 
}