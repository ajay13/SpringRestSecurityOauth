package com.beingjavaguys.dao.cmsmenu.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beingjavaguys.dao.cmsmenu.CMSMenuUnitDao;
import com.beingjavaguys.dao.core.CoreDao;
import com.beingjavaguys.models.cmsmenu.CMSMenuUnitData;

@Repository("cmsMenuUnitDao")
public class CMSMenuUnitDaoImpl implements CMSMenuUnitDao {

	@Autowired
	CoreDao coreDao;

	@Override
	public CMSMenuUnitData get(String unitName) {
		CMSMenuUnitData cmsMenuUnitData = null;
		Session session = null;
		String getCMSUnit = "select U from CMSMenuUnitData U where U.unit=:unit";
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCMSUnit);
			query.setParameter("unit", unitName);
			cmsMenuUnitData = (CMSMenuUnitData) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cmsMenuUnitData;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CMSMenuUnitData> getMenuUnit() {
		List<CMSMenuUnitData> cmsMenuUnitDataList = null;
		Session session = null;
		String getCMSUnit = "select U from CMSMenuUnitData U";
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getCMSUnit);
			cmsMenuUnitDataList = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cmsMenuUnitDataList;
	}
}
