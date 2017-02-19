package com.beingjavaguys.dao.core.impl;

import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beingjavaguys.dao.core.CoreDao;


@Component("coreDao")
public class CoreDaoImpl implements CoreDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		Session session = null;

		try {
			session = sessionFactory.openSession();

			session.setCacheMode(CacheMode.IGNORE);
		}
		catch (HibernateException e) {
			
		}

		return session;
	}

}
