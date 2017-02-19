package com.beingjavaguys.services.cmscooks.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beingjavaguys.bean.cmscooks.CMSCooksBean;
import com.beingjavaguys.bean.generic.BeanList;
import com.beingjavaguys.dao.cmscooks.CMSCooksDao;
import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.services.cmscooks.CMSCooksService;
import com.beingjavaguys.utility.cmscooks.CMSCooksUtility;


@Service("cmsCooksService")
public class CMSCooksServiceImpl implements CMSCooksService {

	@Autowired
	CMSCooksDao cmsCooksDao;

	@Autowired
	CMSCooksUtility cmsCooksUtility;

	@Override
	public void add(CMSCooksBean cmsCooksBean, HttpServletResponse response) {
		CMSCooksData cmsCooksData = null;
		cmsCooksData = cmsCooksUtility.populateCMSCooks(cmsCooksBean);
		cmsCooksDao.add(cmsCooksData, response);
	}

	@Override
	public void delete(int id, HttpServletResponse response) {
		CMSCooksData cmsCooksData = null;
		cmsCooksData = cmsCooksDao.get(id, response);
		cmsCooksDao.delete(cmsCooksData, response);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BeanList get(int limit, int pageno) {
		BeanList objectListBean = new BeanList();
		List<CMSCooksBean> cmsCooksBeanList = new ArrayList<CMSCooksBean>();
		List<CMSCooksData> cmsCooksDataList = null;
		List<Object> list  = cmsCooksDao.get(limit, pageno);
		
		cmsCooksDataList = (List<CMSCooksData>) list.get(0);
		int count = (int) list.get(1);
		
		for (CMSCooksData cmsCooksData : cmsCooksDataList) {
			CMSCooksBean cmsCooksBean =  cmsCooksUtility.populateCMSCooks(cmsCooksData);
			cmsCooksBeanList.add(cmsCooksBean);
		}
		
		objectListBean.setCmsCooksBeanList(cmsCooksBeanList);
		objectListBean.setCount(count);

		return objectListBean;
	}

	@Override
	public void edit(CMSCooksBean cmsCooksBean,
			HttpServletResponse response) {
		CMSCooksData cmsCooksData = null;
		cmsCooksData = cmsCooksUtility.populateCMSCooks(cmsCooksBean);
		cmsCooksDao.edit(cmsCooksData, response);
	}

	@Override
	public List<CMSCooksBean> get(String cookName, HttpServletResponse response) {
		List<CMSCooksBean> cmsCooksBeanList = new ArrayList<CMSCooksBean>();
		List<CMSCooksData> cmsCooksDataList = null;
		cmsCooksDataList = cmsCooksDao.get(cookName, response);
		for (CMSCooksData cmsCooksData : cmsCooksDataList) {
			CMSCooksBean cmsCooksBean =  cmsCooksUtility.populateCMSCooks(cmsCooksData);
			cmsCooksBeanList.add(cmsCooksBean);
		}
		return cmsCooksBeanList;
	}

}
