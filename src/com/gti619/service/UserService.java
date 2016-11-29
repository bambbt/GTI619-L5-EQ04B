package com.gti619.service;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
	@Autowired
	@Qualifier("securityConfigService")
	private SecurityConfigService configService;

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

	public boolean oldPasswordCheckUsed(String principal, String password) {

		String strSalt = userDao.getSalt(principal);

		String mixedPass = strSalt+password;

		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

		//encodage du password
		String passHash = passEncoder.encode(mixedPass);


		return (userDao.lookForSamePass(principal,passHash) > 1);
	}

	public void changePassword(String login, String password) {

		//Creation du salt
		SecureRandom random = new SecureRandom();
		int salt = random.nextInt();

		String strSalt = Integer.toString(salt);

		//ajout du sel au le mot de pass
		String mixedPass = strSalt+password;

		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

		//encodage du password
		String passHash = passEncoder.encode(mixedPass);



		User user = userDao.findByUserName(login);



		int nbPassMax = configService.getNboldPassMax();

		if(oldPassService.getNbOldpass(login) < nbPassMax){
			OldPassword oldPassword = new OldPassword();
			oldPassword.setOldPassword(user.getMdp());
			oldPassword.setUser(user);
			oldPassword.setDate(new Date());
			oldPassService.persist(oldPassword);
		}
		else{
			OldPassword oldPassword2 = oldPassService.getPlusvieux(login);
			oldPassword2.setDate(new Date());
			oldPassword2.setOldPassword(user.getMdp());
			oldPassService.persist(oldPassword2);
		}



		user.setMdp(passHash);
		user.setSalt(strSalt);
		
		userDao.persist(user);
	}



}
