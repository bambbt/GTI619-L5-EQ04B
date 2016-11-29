	package com.gti619.daos;

	
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;

import com.gti619.model.OldPassword;
import com.gti619.model.Role;
import com.gti619.model.User;

/**
 * Home object for domain model class User.
 * @see .User
 * @author Hibernate Tools
 */
@Repository("UserHome")
@Transactional
public class UserHome extends SessionFactoryHibernateDAOSupport{

	private static final Log log = LogFactory.getLog(UserHome.class);


	public void persist(User transientInstance) {
		log.debug("persisting User instance");
		try {
			getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getSession().get("User", id);
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

	public List<?> findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			@SuppressWarnings("deprecation")
			List<?> results = getSession().createCriteria("User").add(Example.create(instance))
			.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<Object[]> users = new ArrayList<Object[]>();

		users = getSession().createQuery("from User u, Role r  where login=? and  u.role=r")
				.setParameter(0, username).list();
		log.debug("find by Username successful, result size: " + users.size());

		if (users.size() > 0) {
			
			User user = (User) users.get(0)[0];
			Role r = (Role) users.get(0)[1];
			user.setRole(r);
			return user;
		} else {
			return null;
		}
		
		

	}

	public String getSalt(String login) {
		String salt;

		salt = (String) getSession().createQuery("from User u where login= ? ")
				.setParameter(0, login).list().get(0);
		log.debug("find salt by Username successful, result size: " + salt);

		if (salt !=null) {
			
			return salt;
		} else {
			return null;
		}
	}

	public int lookForSamePass(String login, String passHash) {
		List<OldPassword> oldpass = new ArrayList<OldPassword>();

		oldpass = getSession().createQuery("from OldPassword o where oldPassword like ? and  o.user.login = ?")
				.setParameter(0, passHash).setParameter(1,login).list();
		log.debug("find by Username successful, result size: " + oldpass.size());

		return oldpass.size();
	}

	
}
