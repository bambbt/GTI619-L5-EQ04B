package com.gti619.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gti619.daos.SecurityConfigHome;
import com.gti619.model.SecurityConfig;

@Service("securityConfigService")
public class SecurityConfigService {
	@Autowired
	private SecurityConfigHome configDao;

	public int getNboldPassMax() {
		return configDao.getConfig().getNbOldPasswordToSave();
	}

	public int getNbTentativeCoMax() {
		return configDao.getConfig().getNbTentativesMax();
		
	}

	public void setPolitiquePassword(String regex) {
		SecurityConfig config = configDao.getConfig();
		config.setPassRegex(regex);
		configDao.attachDirty(config);
	}

	public void setTentativeCoMax(int nbtentative) {
		SecurityConfig config = configDao.getConfig();
		config.setNbTentativesMax(nbtentative);
		configDao.attachDirty(config);		
	}

	public String getPasswordPolitique() {
		
		return configDao.getConfig().getPassRegex();
	}

	public int getTimeoutInactiveSession() {
		return configDao.getConfig().getAuthenticationTimeout();
	}
		
	

}
