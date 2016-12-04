package com.gti619.daos;
// default package
// Generated Nov 28, 2016 1:39:03 PM by Hibernate Tools 5.1.0.Beta1

import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import com.gti619.model.Role;
import com.gti619.model.User;

/**
 * Home object for domain model class Role.
 * @see .Role
 * @author Hibernate Tools
 */
@Repository("RoleHome")
@Transactional
public class RoleHome extends SessionFactoryHibernateDAOSupport{

	

	public void persist(Role transientInstance) {
	
		try {
			getSession().persist(transientInstance);
	
		} catch (RuntimeException re) {
	
			throw re;
		}
	}

	public void attachDirty(Role instance) {
	
		try {
			getSession().saveOrUpdate(instance);
	
		} catch (RuntimeException re) {
	
			throw re;
		}
	}

	public void attachClean(Role instance) {
	
		try {
			getSession().lock(instance, LockMode.NONE);
	
		} catch (RuntimeException re) {
	
			throw re;
		}
	}

	public void delete(Role persistentInstance) {
	
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
	
			throw re;
		}
	}

	public Role merge(Role detachedInstance) {

		try {
			Role result = (Role) getSession().merge(detachedInstance);

			return result;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	public Role findById(java.lang.Integer id) {

		try {
			Role instance = (Role) getSession().get("Role", id);
			if (instance == null) {

			} else {

			}
			return instance;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	public List findByExample(Role instance) {

		try {
			List results = getSession().createCriteria("Role").add(Example.create(instance))
					.list();

			return results;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	public Role findByName(String strRole) {

		Role role;

		role = (Role) getSession().createQuery("from Role where nom=?")
				.setParameter(0, strRole).list().get(0);


		if (role !=null) {
			return role;
		} else {
			return null;
		}

	}
}
