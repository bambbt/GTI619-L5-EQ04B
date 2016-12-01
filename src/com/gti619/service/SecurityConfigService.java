package com.gti619.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gti619.daos.SecurityConfigHome;

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
	
	
	

}
