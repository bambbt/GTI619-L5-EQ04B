package com.gti619.daos;
// default package
// Generated Dec 4, 2016 7:41:08 PM by Hibernate Tools 5.1.0.Beta1

import java.util.List;
import javax.naming.InitialContext;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import com.gti619.model.MatriceUser;

/**
 * Home object for domain model class MatriceUser.
 * @see .MatriceUser
 * @author Hibernate Tools
 */
@Repository("MatriceUserHome")
@Transactional
public class MatriceUserHome extends SessionFactoryHibernateDAOSupport{

	

	public void persist(MatriceUser transientInstance) {
		
		try {
			getSession().persist(transientInstance);
			
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public void attachDirty(MatriceUser instance) {
		
		try {
			getSession().saveOrUpdate(instance);
			
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public void attachClean(MatriceUser instance) {
		
		try {
			getSession().lock(instance, LockMode.NONE);
		
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public void delete(MatriceUser persistentInstance) {
		
		try {
			getSession().delete(persistentInstance);
		
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public MatriceUser merge(MatriceUser detachedInstance) {
		
		try {
			MatriceUser result = (MatriceUser) getSession().merge(detachedInstance);
			
			return result;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}

	public MatriceUser findById(java.lang.Integer id) {
		
		try {
			MatriceUser instance = (MatriceUser) getSession().get("MatriceUser", id);
			if (instance == null) {
		
			} else {
		
			}
			return instance;
		} catch (RuntimeException re) {
		
			throw re;
		}
	}

	public List findByExample(MatriceUser instance) {
		
		try {
			List results = getSession().createCriteria("MatriceUser")
					.add(Example.create(instance)).list();
			
			return results;
		} catch (RuntimeException re) {
			
			throw re;
		}
	}
}
