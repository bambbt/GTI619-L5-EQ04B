package com.gti619.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gti619.daos.OldPasswordHome;
import com.gti619.model.OldPassword;
import com.gti619.model.User;

@Service("oldPasswordService")
public class OldPasswordService {

	
	@Autowired
	private OldPasswordHome oldPassDao;
	
	@Autowired
	@Qualifier("securityConfigService")
	private SecurityConfigService configService;
	
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

	public void attachDirty(OldPassword oldPassword2) {
		oldPassDao.attachDirty(oldPassword2);
		
	}

	public void saveOldPass( User user) {
		
		int nbPassMax = configService.getNboldPassMax();
		if(this.getNbOldpass(user.getLogin()) < nbPassMax){
			OldPassword oldPassword = new OldPassword();
			oldPassword.setOldPassword(user.getMdp());
			oldPassword.setUser(user);
			oldPassword.setDate(new Date());
			this.persist(oldPassword);
		}
		else{
			OldPassword oldPassword2 = this.getPlusvieux(user.getLogin());
			oldPassword2.setDate(new Date());
			oldPassword2.setOldPassword(user.getMdp());
			this.attachDirty(oldPassword2);
		}		
	}

}
