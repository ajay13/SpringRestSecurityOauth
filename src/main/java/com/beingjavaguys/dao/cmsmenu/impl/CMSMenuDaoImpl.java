package com.beingjavaguys.dao.cmsmenu.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beingjavaguys.dao.cmscooks.CMSCooksDao;
import com.beingjavaguys.dao.cmsmenu.CMSMenuDao;
import com.beingjavaguys.dao.core.CoreDao;
import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.models.cmscooks.CMSCooksSpecialityData;
import com.beingjavaguys.models.cmscooks.CookSpecialityMenuData;
import com.beingjavaguys.models.cmsmenu.CMSMenuCatagoryData;
import com.beingjavaguys.models.cmsmenu.CMSMenuData;
import com.beingjavaguys.models.cmsmenu.CMSMenuPriceData;

@Repository("cmsMenuDao")
public class CMSMenuDaoImpl implements CMSMenuDao {

	@Autowired
	CoreDao coreDao;

	@Autowired
	CMSCooksDao cmsCooksDao;

	@Override
	public int add(CMSMenuData cmsMenuData, List<CMSMenuPriceData> cmsMenuPriceDataList,HttpServletResponse response) {

		String getMenu = "select count(M) from CMSMenuData M where M.itemName=:itemName AND M.cmsCooksData.id=:cookId";
		int menuId = 0;
		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getMenu);
			query.setParameter("itemName", cmsMenuData.getItemName());
			query.setParameter("cookId", cmsMenuData.getCmsCooksData().getId());

			Long count = (Long) query.uniqueResult();

			if (count == 0) {
				session.saveOrUpdate(cmsMenuData);
				session.getTransaction().commit();
				menuId = cmsMenuData.getId();
				for(CMSMenuPriceData cmsMenuPriceData : cmsMenuPriceDataList){
					  session.beginTransaction();
					  cmsMenuPriceData.setCmsMenuData(cmsMenuData);
					  session.save(cmsMenuPriceData);
					  session.getTransaction().commit();
				}
				response.setStatus(200);// for OK
			} else {
				response.setStatus(402);// for already exists
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return menuId;
	}

	@Override
	public CMSMenuData get(int menuId) {
		String getMenu = "select M from CMSMenuData M where M.id=:menuId";
		Session session = null;
		Query query = null;
		CMSMenuData cmsMenuData = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getMenu);
			query.setParameter("menuId", menuId);

			cmsMenuData = (CMSMenuData) query.uniqueResult();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return cmsMenuData;
	}

	@Override
	public void updateMenu(CMSMenuData cmsMenuData, HttpServletResponse response) {
		Session session = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			session.update(cmsMenuData);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> get(int limit, int pageno, CMSCooksData cmsCooksData,
			CMSMenuCatagoryData cmsMenuCatagoryData) {

		List<Object> list = new ArrayList<Object>();
		List<CMSMenuData> cmsMenuDataList = new ArrayList<CMSMenuData>();

		String getMenuCount = "";
		String getMenu = null;

		if (cmsCooksData == null && cmsMenuCatagoryData != null) {
			getMenu = " select M from CMSMenuData M where  M.cmsMenuCatagoryData.id=:catagoryId";
			getMenuCount = "select count(M) from CMSMenuData M where  M.cmsMenuCatagoryData.id=:catagoryId";
		}

		if (cmsCooksData != null && cmsMenuCatagoryData == null) {
			getMenu = " select M from CMSMenuData M where M.cmsCooksData.id=:cookId ";
			getMenuCount = "select count(M) from CMSMenuData M where M.cmsCooksData.id=:cookId ";
		}

		if (cmsCooksData != null && cmsMenuCatagoryData != null) {
			getMenu = " select M from CMSMenuData M where M.cmsCooksData.id=:cookId AND M.cmsMenuCatagoryData.id=:catagoryId";
			getMenuCount = "select count(M) from CMSMenuData M where M.cmsCooksData.id=:cookId AND M.cmsMenuCatagoryData.id=:catagoryId";
		}

		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();

			if (getMenu != null) {
				query = session.createQuery(getMenu);

				if (cmsCooksData != null && cmsMenuCatagoryData != null) {
					query.setParameter("cookId", cmsCooksData.getId());
					query.setParameter("catagoryId",
							cmsMenuCatagoryData.getId());
				}

				if (cmsCooksData != null && cmsMenuCatagoryData == null) {
					query.setParameter("cookId", cmsCooksData.getId());
				}

				if (cmsCooksData == null && cmsMenuCatagoryData != null) {
					query.setParameter("catagoryId",
							cmsMenuCatagoryData.getId());
				}

				query.setFirstResult((pageno * limit) - limit);
				query.setMaxResults(limit);
				cmsMenuDataList = query.list();
				for(CMSMenuData cmsMenuData : cmsMenuDataList){
					Hibernate.initialize(cmsMenuData.getCmsMenuPriceDataList());
				}

				query = session.createQuery(getMenuCount);

				if (cmsCooksData != null && cmsMenuCatagoryData != null) {
					query.setParameter("cookId", cmsCooksData.getId());
					query.setParameter("catagoryId",
							cmsMenuCatagoryData.getId());
				}

				if (cmsCooksData != null && cmsMenuCatagoryData == null) {
					query.setParameter("cookId", cmsCooksData.getId());
				}

				if (cmsCooksData == null && cmsMenuCatagoryData != null) {
					query.setParameter("catagoryId",
							cmsMenuCatagoryData.getId());
				}
				Long count = (Long) query.uniqueResult();

				int pagination = (int) (count / limit);

				if (pagination * limit < count) {
					pagination++;
				}

				list.add(cmsMenuDataList);
				list.add(pagination);

			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public int edit(CMSMenuData cmsMenuData, List<CMSMenuPriceData> cmsMenuPriceDataList,HttpServletResponse response) {
		Session session = null;
		int menuId = 0;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			session.saveOrUpdate(cmsMenuData);
			session.getTransaction().commit();
			menuId = cmsMenuData.getId();
			
			for(CMSMenuPriceData cmsMenuPriceData : cmsMenuPriceDataList){
				  session.beginTransaction();
				  cmsMenuPriceData.setCmsMenuData(cmsMenuData);
				  session.save(cmsMenuPriceData);
				  session.getTransaction().commit();
			}
			response.setStatus(200);// for OK	
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return menuId;
	}

	@Override
	public void delete(CMSMenuData cmsMenuData, HttpServletResponse response) {
		boolean trigger = true;
		
		Query query = null;
		String getCook = "select C from CMSCooksData as C where C.id=:cookId";

		CMSCooksData cmsCooksData = null;

		List<CookSpecialityMenuData> cookSpecialityMenuDataList = null;

		Session session = null;
		try {
			session = coreDao.getSession();
			query = session.createQuery(getCook);
			query.setParameter("cookId", cmsMenuData.getCmsCooksData().getId());
			cmsCooksData = (CMSCooksData) query.uniqueResult();
			Hibernate.initialize(cmsCooksData.getCookSpecialityMenuDataList());
			Hibernate.initialize(cmsCooksData.getCmsMenuDataList());
			cookSpecialityMenuDataList = cmsCooksData
					.getCookSpecialityMenuDataList();
			
			for(CookSpecialityMenuData cookSpecialityMenuData : cookSpecialityMenuDataList){
				List<CMSMenuData> menuDataList  = cookSpecialityMenuData.getCmsMenuDataList();
				for(CMSMenuData menuData : menuDataList){
					 if(menuData.getId() == cmsMenuData.getId()){
						 trigger = false;
					 }
				}
			}
			
			if(trigger){
				  deleteMenuPriceDataOnly(cmsMenuData.getId());
				  session.beginTransaction();
				  cmsMenuData = (CMSMenuData) session.merge(cmsMenuData);
				  cmsMenuData.getCmsMenuPriceDataList().clear();
				  session.delete(cmsMenuData);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CMSMenuData> get(CMSCooksData cmsCooksData,
			CMSCooksSpecialityData cmsCooksSpecialityData) {

		List<CMSMenuData> cmsMenuDataList = new ArrayList<CMSMenuData>();

		String getMenu = " select M from CMSMenuData M where M.cmsCooksData.id=:cookId ";

		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			if (getMenu != null) {
				query = session.createQuery(getMenu);
				query.setParameter("cookId", cmsCooksData.getId());
				cmsMenuDataList = query.list();
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cmsMenuDataList;
	}

	@Override
	public void addSpeciality(CookSpecialityMenuData cookSpecialityMenuData) {
		Session session = null;
		try {
			session = coreDao.getSession();
			session.flush();
			session.clear();
			session.beginTransaction();
			session.saveOrUpdate(cookSpecialityMenuData);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public CookSpecialityMenuData getCookSpeciality(CMSCooksData cmsCooksData,
			CMSCooksSpecialityData cmsCooksSpecialityData) {

		CookSpecialityMenuData cookSpecialityMenuData = null;

		String getCookSpeciality = " select S from CookSpecialityMenuData S where S.cmsCooksSpecialityData.id=:specialityId AND S.cmsCooksData.id=:cookId";

		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			if (getCookSpeciality != null) {
				query = session.createQuery(getCookSpeciality);
				query.setParameter("cookId", cmsCooksData.getId());
				query.setParameter("specialityId",
						cmsCooksSpecialityData.getId());
				cookSpecialityMenuData = (CookSpecialityMenuData) query
						.uniqueResult();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cookSpecialityMenuData;
	}

	@Override
	public void deleteCookSpecialityMenu(
			CookSpecialityMenuData cmsCooksSpecialityData) {
		Session session = null;
		try {
			session = coreDao.getSession();
			/* session.beginTransaction(); */
			cmsCooksSpecialityData.getCmsMenuDataList().clear();
			session.delete(cmsCooksSpecialityData);
			/* session.getTransaction().commit(); */
			session.flush();
			session.clear();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getCooksOfItem(int limit, int pageno, String itemName) {

		List<Object> list = new ArrayList<Object>();
		List<CMSMenuData> cmsMenuDataList = new ArrayList<CMSMenuData>();

		String getMenuCount = "";
		String getMenu = null;

		if (itemName != null && itemName != null) {
			getMenu = " select M from CMSMenuData M where M.itemName like :itemName";
			getMenuCount = "select count(M) from CMSMenuData M where M.itemName like :itemName";
		}

		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();

			if (getMenu != null) {
				query = session.createQuery(getMenu);

				query.setParameter("itemName", "%" + itemName + "%");

				query.setFirstResult((pageno * limit) - limit);
				query.setMaxResults(limit);
				cmsMenuDataList = query.list();

				query = session.createQuery(getMenuCount);

				query.setParameter("itemName", "%" + itemName + "%");

				Long count = (Long) query.uniqueResult();

				int pagination = (int) (count / limit);

				if (pagination * limit < count) {
					pagination++;
				}

				list.add(cmsMenuDataList);
				list.add(pagination);

			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	@Override
	public List<CMSMenuPriceData> getMenuPriceData(int menuId) {
		String getMenuPriceData = "select M from CMSMenuPriceData M where M.cmsMenuData.id=:menuId";
		Session session = null;
		Query query = null;
		List<CMSMenuPriceData> cmsMenuPriceDataList = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getMenuPriceData);
			query.setParameter("menuId", menuId);

			cmsMenuPriceDataList = query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return cmsMenuPriceDataList;
	}
	
	
	@Override
	public void deleteMenuPriceDataOnly(
			int  menuId) {
		Session session = null;
		try {
			session = coreDao.getSession();
			List<CMSMenuPriceData> cmsMenuPriceDataList = getMenuPriceData(menuId);
			for(CMSMenuPriceData cmsMenuPriceData : cmsMenuPriceDataList){
				session.beginTransaction();
				session.delete(cmsMenuPriceData); 
				session.getTransaction().commit();	
			}			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();

		}
	}

}
