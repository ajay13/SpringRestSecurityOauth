package com.beingjavaguys.services.cmsmenu.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beingjavaguys.bean.cmscooks.CMSCooksBean;
import com.beingjavaguys.bean.cmsmenu.CMSCatagoryBean;
import com.beingjavaguys.bean.generic.BeanList;
import com.beingjavaguys.dao.cmsmenu.CMSMenuCatagoryDao;
import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.models.cmsmenu.CMSMenuCatagoryData;
import com.beingjavaguys.services.cmsmenu.CMSCatagoryService;
import com.beingjavaguys.utility.cmsmenu.CMSCatagoryUtility;

@Service("cmsCatagoryService")
public class CMSCatagoryServiceImpl implements CMSCatagoryService{

	@Autowired
	CMSMenuCatagoryDao cmsMenuCatagoryDao; 
	
	@Autowired
	CMSCatagoryUtility cmsCatagoryUtility;
	
	@Override
	public void add(CMSCatagoryBean cmsCatagoryBean, HttpServletResponse response) {
		CMSMenuCatagoryData  cmsMenuCatagoryData = null;
		cmsMenuCatagoryData = cmsCatagoryUtility.populateCMSMenuCatagory(cmsCatagoryBean);
		cmsMenuCatagoryDao.add(cmsMenuCatagoryData, response);
	}

	@Override
	public void delete(int id, HttpServletResponse response) {
		CMSMenuCatagoryData cmsMenuCatagoryData = null;
		cmsMenuCatagoryData = cmsMenuCatagoryDao.get(id, response);
		cmsMenuCatagoryDao.delete(cmsMenuCatagoryData, response);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BeanList get(int limit, int pageno) {
		BeanList objectListBean = new BeanList();
		List<CMSCatagoryBean> cmsCatagoryBeanList = new ArrayList<CMSCatagoryBean>();
		List<CMSMenuCatagoryData> cmsMenuCatagoryDataList = null;
		List<Object> list  = cmsMenuCatagoryDao.get(limit, pageno);
		
		cmsMenuCatagoryDataList = (List<CMSMenuCatagoryData>) list.get(0);
		int count = (int) list.get(1);
		
		for (CMSMenuCatagoryData cmsMenuCatagoryData : cmsMenuCatagoryDataList) {
			CMSCatagoryBean cmsCatagoryBean =  cmsCatagoryUtility.populateCMSMenuCatagory(cmsMenuCatagoryData);
			cmsCatagoryBeanList.add(cmsCatagoryBean);
		}
		
		objectListBean.setCmsCatagoryBeanList(cmsCatagoryBeanList);
		objectListBean.setCount(count);

		return objectListBean;
	}

	@Override
	public void edit(CMSCatagoryBean cmsCatagoryBean,
			HttpServletResponse response) {
		CMSMenuCatagoryData cmsMenuCatagoryData = null;
		cmsMenuCatagoryData = cmsCatagoryUtility.populateCMSMenuCatagory(cmsCatagoryBean);
		cmsMenuCatagoryDao.edit(cmsMenuCatagoryData, response);
	}

	@Override
	public List<CMSCatagoryBean> get(String catagoryName,
			HttpServletResponse response) {
		List<CMSCatagoryBean> cmsCatagoryBeanList = new ArrayList<CMSCatagoryBean>();
		List<CMSMenuCatagoryData> cmsMenuCatagoryDataList = null;
		cmsMenuCatagoryDataList = cmsMenuCatagoryDao.get(catagoryName, response);
		for (CMSMenuCatagoryData cmsMenuCatagoryData : cmsMenuCatagoryDataList) {
			CMSCatagoryBean cmsCatagoryBean =  cmsCatagoryUtility.populateCMSMenuCatagory(cmsMenuCatagoryData);
			cmsCatagoryBeanList.add(cmsCatagoryBean);
		}
		return cmsCatagoryBeanList;
	}

	@Override
	public List<CMSCatagoryBean> get() {
		List<CMSCatagoryBean> cmsCatagoryBeanList = new ArrayList<CMSCatagoryBean>();
		List<CMSMenuCatagoryData> cmsMenuCatagoryDataList = null;
		cmsMenuCatagoryDataList = cmsMenuCatagoryDao.get();
		for (CMSMenuCatagoryData cmsMenuCatagoryData : cmsMenuCatagoryDataList) {
			CMSCatagoryBean cmsCatagoryBean =  cmsCatagoryUtility.populateCMSMenuCatagory(cmsMenuCatagoryData);
			cmsCatagoryBeanList.add(cmsCatagoryBean);
		}
		return cmsCatagoryBeanList;
	}
	
	@Override
	public void grouppingAndSeq(List<Integer> catagoryIds, String groupName) {
		cmsMenuCatagoryDao.grouppingAndSeq(catagoryIds, groupName);
	}

}
