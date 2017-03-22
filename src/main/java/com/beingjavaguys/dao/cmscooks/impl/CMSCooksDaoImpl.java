package com.beingjavaguys.dao.cmscooks.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beingjavaguys.dao.cmscooks.CMSCooksDao;
import com.beingjavaguys.dao.core.CoreDao;
import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.models.cmscooks.CMSCooksSpecialityData;
import com.beingjavaguys.models.cmsmenu.CMSMenuData;

@Repository("cmsCooksDao")
public class CMSCooksDaoImpl implements CMSCooksDao {

	@Autowired
	CoreDao coreDao;

	@Override
	public int add(CMSCooksData cmsCooksData, HttpServletResponse response) {

		String getCooks = "select count(C) from CMSCooksData C where C.name=:name";

		Session session = null;
		Query query = null;
		
		int id = 0;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCooks);
			query.setParameter("name", cmsCooksData.getName());

			Long count = (Long) query.uniqueResult();

			if (count == 0) {
				session.saveOrUpdate(cmsCooksData);
				session.getTransaction().commit();
				response.setStatus(200);// for OK
				id = cmsCooksData.getId();
			} else {
				response.setStatus(402);// for already exists
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return id;
	}

	@Override
	public void delete(CMSCooksData cmsCooksData, HttpServletResponse response) {
			Session session = null;
			Query query = null;
			String getMenu = "select count(M) from CMSMenuData as M where M.cmsCooksData.id=:cookId";
			try {
				session = coreDao.getSession();
				query = session.createQuery(getMenu);
				query.setParameter("cookId", cmsCooksData.getId());
				long count = (long)query.uniqueResult();
				if(count<=0){
					session.beginTransaction();
					session.delete(cmsCooksData);
					session.getTransaction().commit();
				}else{
					response.setStatus(206);
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
	}

	@Override
	public CMSCooksData get(int id, HttpServletResponse response) {
		CMSCooksData cmsCooksData = null;
		Session session = null;
		String getCooks = "from CMSCooksData C where C.id=:id";
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCooks);
			query.setParameter("id", id);
			cmsCooksData = (CMSCooksData) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cmsCooksData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> get(int limit, int pageno) {

		List<Object> list = new ArrayList<Object>();
		List<CMSCooksData> cmsCooksDataList = new ArrayList<CMSCooksData>();

		String getUser = " select C from CMSCooksData C";
		String getUserCount = "select count(C) from CMSCooksData C";

		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getUser);
			query.setFirstResult((pageno * limit) - limit);
			query.setMaxResults(limit);
			cmsCooksDataList = query.list();

			query = session.createQuery(getUserCount);
			Long count = (Long) query.uniqueResult();

			int pagination = (int) (count / limit);

			if (pagination * limit < count) {
				pagination++;
			}

			list.add(cmsCooksDataList);
			list.add(pagination);

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public int edit(CMSCooksData cmsCooksData, HttpServletResponse response) {
		Session session = null;
		int cookId = 0;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			session.saveOrUpdate(cmsCooksData);
			session.getTransaction().commit();
			cookId = cmsCooksData.getId();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cookId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CMSCooksData> get(String cookName, HttpServletResponse response) {

		String getCooks = "select C from CMSCooksData C where C.name like :name";
		List<CMSCooksData> cmsCooksDataList = new ArrayList<CMSCooksData>();
		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCooks);
			query.setParameter("name", "%"+cookName+"%");
			cmsCooksDataList = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cmsCooksDataList;
	}
	
	@Override
	public void updateMenu(CMSCooksData cmsCooksData, HttpServletResponse response) {
		Session session = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			session.update(cmsCooksData);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
    @SuppressWarnings("unchecked")
	@Override	
	public List<CMSCooksSpecialityData> getCooksSpeciality() {
		List<CMSCooksSpecialityData> cmsCooksSpecialityDataList = null;
		Session session = null;
		String getCooksSpeciality = "select C from CMSCooksSpecialityData C";
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCooksSpeciality);
			cmsCooksSpecialityDataList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cmsCooksSpecialityDataList;
	}

    @Override
	public CMSCooksSpecialityData getCooksSpeciality(String speciality) {
    	CMSCooksSpecialityData cmsCooksSpecialityData = null;
		Session session = null;
		String getCast = "select C from CMSCooksSpecialityData C where C.speciality=:speciality";
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCast);
			query.setParameter("speciality", speciality);
			cmsCooksSpecialityData = (CMSCooksSpecialityData) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cmsCooksSpecialityData;
	}
}
