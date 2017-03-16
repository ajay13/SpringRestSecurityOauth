package com.beingjavaguys.dao.other.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beingjavaguys.dao.core.CoreDao;
import com.beingjavaguys.dao.other.OtherDao;
import com.beingjavaguys.models.other.CastData;

@Repository("otherDao")
public class OtherDaoImpl implements OtherDao {

	@Autowired
	CoreDao coreDao;

    @SuppressWarnings("unchecked")
	@Override	
	public List<CastData> getCast() {
		List<CastData> castDataList = null;
		Session session = null;
		String getCast = "select C from CastData C";
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCast);
			castDataList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return castDataList;
	}
    
	@Override
	public CastData getCast(String cast) {
		CastData castData = null;
		Session session = null;
		String getCast = "select C from CastData C where C.cast=:cast";
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCast);
			query.setParameter("cast", cast);
			castData = (CastData) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return castData;
	}

}
