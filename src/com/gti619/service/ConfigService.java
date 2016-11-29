package com.gti619.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gti619.daos.SecurityConfigHome;

@Service("configService")
public class ConfigService {
	@Autowired
	private SecurityConfigHome configDao;

	public int getNboldPassMax() {
		return configDao.getNbOldPassMax();
	}

}
