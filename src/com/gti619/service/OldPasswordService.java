package com.gti619.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gti619.daos.OldPasswordHome;
import com.gti619.model.OldPassword;

@Service("oldPasswordService")
public class OldPasswordService {

	
	@Autowired
	private OldPasswordHome oldPassDao;
	
	public int getNbOldpass(String login) {
		
		List<OldPassword> oldPasswrods = new ArrayList<OldPassword>();
		oldPasswrods = oldPassDao.getOldPasswords(login);
		return oldPasswrods.size();
		
	}

	public void persist(OldPassword oldPassword) {
		oldPassDao.persist(oldPassword);
	}

	public OldPassword getPlusvieux(String login) {
		return oldPassDao.getOldestPassword(login);
	}

}
