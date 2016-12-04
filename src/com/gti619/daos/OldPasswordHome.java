package com.gti619.daos;
// default package
// Generated Nov 28, 2016 1:39:03 PM by Hibernate Tools 5.1.0.Beta1

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gti619.model.OldPassword;
import com.gti619.model.User;

/**
 * Home object for domain model class OldPassword.
 * @see .OldPassword
 * @author Hibernate Tools
 */
@Repository("OldPasswordHome")
@Transactional
public class OldPasswordHome extends SessionFactoryHibernateDAOSupport{

	

	@Autowired
	private UserHome userDao;


	public void persist(OldPassword transientInstance) {
		
		try {
			getSession().persist(transientInstance);
		
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public void attachDirty(OldPassword instance) {
		
		try {
			getSession().saveOrUpdate(instance);
		
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public void attachClean(OldPassword instance) {
		
		try {
			getSession().lock(instance, LockMode.NONE);
		
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public void delete(OldPassword persistentInstance) {
		
		try {
			getSession().delete(persistentInstance);
		
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public OldPassword merge(OldPassword detachedInstance) {
		
		try {
			OldPassword result = (OldPassword) getSession().merge(detachedInstance);
		
			return result;
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public OldPassword findById(java.lang.Integer id) {
		
		try {
			OldPassword instance = (OldPassword) getSession().get("OldPassword", id);
			if (instance == null) {
				
			} else {
				
			}
			return instance;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public List findByExample(OldPassword instance) {
		
		try {
			List results = getSession().createCriteria("OldPassword")
					.add(Example.create(instance)).list();
		
			return results;
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public List<OldPassword> getOldPasswords(String login) {
		List<OldPassword> oldPasswords = userDao.getOldPasswords(login);
		
		return oldPasswords;
	}

	public OldPassword getOldestPassword(String login) {
		List<OldPassword> oldPasswords = new ArrayList<OldPassword>();
		oldPasswords = getOldPasswords(login);
		
		Date d =  oldPasswords.get(0).getDate();
		int j=0;
		for (int i = 1; i < oldPasswords.size(); i++) {
			if(oldPasswords.get(i).getDate().compareTo(oldPasswords.get(j).getDate()) < 0 )
					j=i;						
		}
		
		return oldPasswords.get(j);		
	}
}
