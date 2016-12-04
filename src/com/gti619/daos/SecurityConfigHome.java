package com.gti619.daos;
// default package
// Generated Nov 28, 2016 1:39:03 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import com.gti619.model.SecurityConfig;

/**
 * Home object for domain model class SecurityConfig.
 * @see .SecurityConfig
 * @author Hibernate Tools
 */

@Repository("SecurityConfigHome")
@Transactional
public class SecurityConfigHome extends SessionFactoryHibernateDAOSupport{

	

	public void persist(SecurityConfig transientInstance) {
	
		try {
			getSession().persist(transientInstance);
	
		} catch (RuntimeException re) {
	
			throw re;
		}
	}

	public void attachDirty(SecurityConfig instance) {
		
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(SecurityConfig instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public void delete(SecurityConfig persistentInstance) {
		
		try {
			getSession().delete(persistentInstance);
		
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public SecurityConfig merge(SecurityConfig detachedInstance) {
		
		try {
			SecurityConfig result = (SecurityConfig) getSession().merge(detachedInstance);
		
			return result;
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public SecurityConfig findById(java.lang.Integer id) {
		
		try {
			SecurityConfig instance = (SecurityConfig) getSession().get("SecurityConfig", id);
			if (instance == null) {
		
			} else {
		
			}
			return instance;
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public List findByExample(SecurityConfig instance) {
		
		try {
			List results = getSession().createCriteria("SecurityConfig")
					.add(Example.create(instance)).list();
		
			return results;
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public SecurityConfig getConfig() {
		
		
		SecurityConfig securConfig = (SecurityConfig) getSession().createQuery(" from SecurityConfig ").list().get(0);
		return securConfig;
	}

}
