package com.gti619.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gti619.daos.RoleHome;
import com.gti619.daos.UserHome;
import com.gti619.model.Role;
import com.gti619.model.User;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserHome userDao;
	@Autowired
	private RoleHome roleDao;

	public boolean validatePasswd(String username, String userPass) {
		User user = userDao.findByUserName(username);
		if(user.getMdp().contentEquals(userPass))
			return true;
		else
			return false;
	}

	public void addUser(String strRole, String login, String completeName, String mail, String password) {
		
		//Creation du salt
		 SecureRandom random = new SecureRandom();
	     int salt = random.nextInt();
	     
	     String strSalt = Integer.toString(salt);
	     
	     //ajout du sel au le mot de pass
	     String mixedPass = strSalt+password;
	     
	     BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
	     
	     //encodage du password
	     String passHash = passEncoder.encode(mixedPass);
	     
	     
	     User user = new User();
	     
	     user.setEmail(mail);
	     user.setLogin(login);
	     user.setMdp(passHash);
	     user.setSalt(strSalt);
	     user.setName(completeName);
	     Role role= roleDao.findByName(strRole);
	     user.setRole(role);
	     userDao.persist(user);
	    }
	
	

}
