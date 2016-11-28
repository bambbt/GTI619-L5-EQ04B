package com.gti619.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gti619.daos.UserHome;
import com.gti619.model.User;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserHome userDao;

	public boolean validatePasswd(String username, String userPass) {
		User user = userDao.findByUserName(username);
		if(user.getMdp()==userPass)
			return true;
		else
			return false;
	}

	public void addUser(String role, String login, String completeName, String mail, String password) {
		System.out.println("Ajout de utilisateur");

	}
	
	

}
