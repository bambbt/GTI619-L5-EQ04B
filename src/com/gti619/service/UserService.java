package com.gti619.service;	

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gti619.config.PasswordEncoder;
import com.gti619.daos.RoleHome;
import com.gti619.daos.UserHome;
import com.gti619.model.OldPassword;
import com.gti619.model.Role;
import com.gti619.model.User;

@Service("userService")
public class UserService {

	@Autowired
	private UserHome userDao;
	@Autowired
	private RoleHome roleDao;
	@Autowired
	@Qualifier("oldPasswordService")
	private OldPasswordService oldPassService;
	

	public boolean validatePasswd(String username, String userPass) {

		String strSalt = userDao.findByUserName(username).getSalt();

		//ajout du sel au le mot de pass
		String mixedPass = strSalt+userPass;



		//encodage du password
		String passHash = null;
		try {
			passHash = PasswordEncoder.MD5encrypt(mixedPass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		User user = userDao.findByUserName(username);
		if(user.getMdp().contentEquals(passHash))
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



		//encodage du password
		String passHash = null;
		try {
			passHash = PasswordEncoder.MD5encrypt(mixedPass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		User user = new User();

		user.setEmail(mail);
		user.setLogin(login);
		user.setMdp(passHash);
		user.setSalt(strSalt);
		user.setName(completeName);
		Role role= roleDao.findByName(strRole);
		user.setRole(role);
		userDao.persist(user);


		oldPassService.saveOldPass(user);
	}

	public boolean oldPasswordCheckUsed(String principal, String password) throws Exception {

		String strSalt = userDao.findByUserName(principal).getSalt();

		String mixedPass = strSalt+password;


		//encodage du password
		String passHash = PasswordEncoder.MD5encrypt(mixedPass);


		return (userDao.lookForSamePass(principal,passHash) > 0);
	}

	public void changePassword(String login, String password) throws Exception {

		//
		String strSalt = userDao.findByUserName(login).getSalt();

		//ajout du sel au le mot de pass
		String mixedPass = strSalt+password;

		//encodage du password
		String passHash = PasswordEncoder.MD5encrypt(mixedPass);

		User user = userDao.findByUserName(login);
		
		
		
		oldPassService.saveOldPass(user);		
		user.setMdp(passHash);
		
		oldPassService.saveOldPass(user);

		userDao.attachDirty(user);
	}



	public User findBylogin (String login){
		return userDao.findByUserName(login);
	}



}
