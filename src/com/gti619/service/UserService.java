package com.gti619.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gti619.daos.UserDAO;
import com.gti619.model.User;

@Service
@Transactional
public class UserService {
     
    @Autowired
    private UserDAO userDAO;
 
    public User getUser(String login) {
        return userDAO.getUser(login);
    }
 
}