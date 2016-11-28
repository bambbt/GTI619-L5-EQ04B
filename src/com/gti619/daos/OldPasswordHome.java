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

import com.gti619.model.OldPassword;

/**
 * Home object for domain model class OldPassword.
 * @see .OldPassword
 * @author Hibernate Tools
 */
@Repository("OldPasswordHome")
@Transactional
public class OldPasswordHome extends SessionFactoryHibernateDAOSupport{

	private static final Log log = LogFactory.getLog(OldPasswordHome.class);



	public void persist(OldPassword transientInstance) {
		log.debug("persisting OldPassword instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(OldPassword instance) {
		log.debug("attaching dirty OldPassword instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OldPassword instance) {
		log.debug("attaching clean OldPassword instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(OldPassword persistentInstance) {
		log.debug("deleting OldPassword instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OldPassword merge(OldPassword detachedInstance) {
		log.debug("merging OldPassword instance");
		try {
			OldPassword result = (OldPassword) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OldPassword findById(java.lang.Integer id) {
		log.debug("getting OldPassword instance with id: " + id);
		try {
			OldPassword instance = (OldPassword) getSession().get("OldPassword", id);
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

	public List findByExample(OldPassword instance) {
		log.debug("finding OldPassword instance by example");
		try {
			List results = getSession().createCriteria("OldPassword")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
