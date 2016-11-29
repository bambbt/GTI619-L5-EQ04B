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

	private static final Log log = LogFactory.getLog(SecurityConfigHome.class);

	public void persist(SecurityConfig transientInstance) {
		log.debug("persisting SecurityConfig instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(SecurityConfig instance) {
		log.debug("attaching dirty SecurityConfig instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecurityConfig instance) {
		log.debug("attaching clean SecurityConfig instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(SecurityConfig persistentInstance) {
		log.debug("deleting SecurityConfig instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecurityConfig merge(SecurityConfig detachedInstance) {
		log.debug("merging SecurityConfig instance");
		try {
			SecurityConfig result = (SecurityConfig) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SecurityConfig findById(java.lang.Integer id) {
		log.debug("getting SecurityConfig instance with id: " + id);
		try {
			SecurityConfig instance = (SecurityConfig) getSession().get("SecurityConfig", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecurityConfig instance) {
		log.debug("finding SecurityConfig instance by example");
		try {
			List results = getSession().createCriteria("SecurityConfig")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public SecurityConfig getConfig() {
		
		
		return (SecurityConfig) getSession().createQuery("from SecurityConfig").list().get(0);
	}

}
