package com.beingjavaguys.dao.cmsmenu.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beingjavaguys.dao.cmsmenu.CMSMenuCatagoryDao;
import com.beingjavaguys.dao.core.CoreDao;
import com.beingjavaguys.models.cmsmenu.CMSMenuCatagoryData;
import com.beingjavaguys.models.cmsmenu.CMSMenuData;

@Repository("cmsMenuCatagoryDao")
public class CMSMenuCatagoryDaoImpl implements CMSMenuCatagoryDao {

	@Autowired
	CoreDao coreDao;

	@Override
	public void add(CMSMenuCatagoryData cmsMenuCatagoryData,HttpServletResponse response) {

		String getCatagory = "select count(C) from CMSMenuCatagoryData C where C.name=:name";
		
		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCatagory);
			query.setParameter("name", cmsMenuCatagoryData.getName());
			
			Long count = (Long) query.uniqueResult();
			
			if(count==0){
				session.saveOrUpdate(cmsMenuCatagoryData);
				session.getTransaction().commit();
				response.setStatus(200);//for OK
			}else{
				response.setStatus(402);//for already exists
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}


	@Override
	public void delete(CMSMenuCatagoryData cmsMenuCatagoryData, HttpServletResponse response) {
			Session session = null;
			Query query = null;
			String getMenu = "select count(M) from CMSMenuData as M where M.cmsMenuCatagoryData.id=:catagoryId";
			try {
				session = coreDao.getSession();
				query = session.createQuery(getMenu);
				query.setParameter("catagoryId", cmsMenuCatagoryData.getId());
				long count = (long)query.uniqueResult();
				
				if(count<=0){
					session.beginTransaction();
					session.delete(cmsMenuCatagoryData);
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
	public CMSMenuCatagoryData get(int id,HttpServletResponse response) {
		CMSMenuCatagoryData cmsMenuCatagoryData = null;
		Session session = null;
		String getCMSMenuCatagory = "from CMSMenuCatagoryData C where C.id=:id";
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCMSMenuCatagory);
			query.setParameter("id", id);
			cmsMenuCatagoryData = (CMSMenuCatagoryData) query.uniqueResult();
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return cmsMenuCatagoryData;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> get(int limit, int pageno) {

		List<Object> list = new ArrayList<Object>();
		List<CMSMenuCatagoryData> cmsMenuCatagoryDataList = new ArrayList<CMSMenuCatagoryData>();

		String getCatagory = " select C from CMSMenuCatagoryData C";
		String getCatagoryCount = "select count(C) from CMSMenuCatagoryData C";

		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCatagory);
			query.setFirstResult((pageno * limit) - limit);
			query.setMaxResults(limit);
			cmsMenuCatagoryDataList = query.list();

			query = session.createQuery(getCatagoryCount);
			Long count = (Long) query.uniqueResult();

			int pagination = (int) (count / limit);

			if (pagination * limit < count) {
				pagination++;
			}

			list.add(cmsMenuCatagoryDataList);
			list.add(pagination);

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}


	@Override
	public void edit(CMSMenuCatagoryData cmsMenuCatagoryData,
			HttpServletResponse response) {
		Session session = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			session.saveOrUpdate(cmsMenuCatagoryData);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CMSMenuCatagoryData> get(String catagoryName,
			HttpServletResponse response) {

		String getCooks = "select C from CMSMenuCatagoryData C where C.name like :name";
		List<CMSMenuCatagoryData> cmsMenuCatagoryDataList = new ArrayList<CMSMenuCatagoryData>();
		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCooks);
			query.setParameter("name", "%"+catagoryName+"%");
			cmsMenuCatagoryDataList = query.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cmsMenuCatagoryDataList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<CMSMenuCatagoryData> get() {
		String getCatagory = "select C from CMSMenuCatagoryData C";
		List<CMSMenuCatagoryData> cmsMenuCatagoryDataList = new ArrayList<CMSMenuCatagoryData>();
		CMSMenuCatagoryData catagoryData = null;
		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCatagory);
			/*@SuppressWarnings("unchecked")
			List<Object[]> catagories= (List<Object[]>)query.list();
			for(Object[] catagory: catagories){
		         Integer id = (Integer)catagory[0];
		         String name = (String)catagory[1];
		         String group = (String)catagory[2];
		         int sequence = (Integer)catagory[3];
		         
		         catagoryData = new CMSMenuCatagoryData();
		         catagoryData.setId(id);
		         catagoryData.setName(name);
		         catagoryData.setGroup(group);
		         catagoryData.setSequence(sequence);
		         
		         cmsMenuCatagoryDataList.add(catagoryData);
		     }*/
			cmsMenuCatagoryDataList = query.list();
			session.getTransaction().commit();

			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cmsMenuCatagoryDataList;
	}
	
	@Override
	public void grouppingAndSeq(List<Integer> catagoryIds, String groupName){
		Session session = null;
		try {
			session = coreDao.getSession();
			int i = 1;
			for(Integer catagroryId : catagoryIds){
				session.beginTransaction();
				CMSMenuCatagoryData cmsMenuCatagoryData = (CMSMenuCatagoryData) session.get(CMSMenuCatagoryData.class, catagroryId);
				cmsMenuCatagoryData.setGroupName(groupName);
				cmsMenuCatagoryData.setSequence(i);
				session.getTransaction().commit();
				i++;
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();

		}
		
	}

}
