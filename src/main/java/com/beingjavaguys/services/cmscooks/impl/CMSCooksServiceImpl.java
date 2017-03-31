package com.beingjavaguys.services.cmscooks.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beingjavaguys.bean.cmscooks.CMSCooksBean;
import com.beingjavaguys.bean.cmscooks.CMSCooksSpecialityBean;
import com.beingjavaguys.bean.generic.BeanList;
import com.beingjavaguys.dao.cmscooks.CMSCooksDao;
import com.beingjavaguys.dao.other.OtherDao;
import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.models.cmscooks.CMSCooksSpecialityData;
import com.beingjavaguys.models.other.CastData;
import com.beingjavaguys.services.cmscooks.CMSCooksService;
import com.beingjavaguys.utility.cmscooks.CMSCooksUtility;

@Service("cmsCooksService")
public class CMSCooksServiceImpl implements CMSCooksService {

	@Autowired
	CMSCooksDao cmsCooksDao;

	@Autowired
	CMSCooksUtility cmsCooksUtility;

	@Autowired
	OtherDao otherDao;

	@Autowired
	ServletContext servletContext;

	@Override
	public int add(CMSCooksBean cmsCooksBean, HttpServletResponse response) {
		CMSCooksData cmsCooksData = null;
		CastData castData = null;
		cmsCooksData = cmsCooksUtility.populateCMSCooks(cmsCooksBean);
		castData = otherDao.getCast(cmsCooksBean.getCast());
		cmsCooksData.setCastData(castData);
		return cmsCooksDao.add(cmsCooksData, response);
	}

	@Override
	public void delete(int id, HttpServletResponse response) {
		CMSCooksData cmsCooksData = null;
		cmsCooksData = cmsCooksDao.get(id, response);
		cmsCooksDao.delete(cmsCooksData, response);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BeanList get(int limit, int pageno,String url) {
		String contextPath = servletContext.getContextPath();
		String mainURL = (url.substring(0, url.indexOf(contextPath)))
				+ (contextPath);

		BeanList objectListBean = new BeanList();
		List<CMSCooksBean> cmsCooksBeanList = new ArrayList<CMSCooksBean>();
		List<CMSCooksData> cmsCooksDataList = null;
		List<Object> list = cmsCooksDao.get(limit, pageno);

		cmsCooksDataList = (List<CMSCooksData>) list.get(0);
		int count = (int) list.get(1);

		for (CMSCooksData cmsCooksData : cmsCooksDataList) {
			CMSCooksBean cmsCooksBean = cmsCooksUtility
					.populateCMSCooks(cmsCooksData);

			String rootPath = servletContext.getRealPath("/");
			String folderPath = File.separator + "image" + File.separator
					+ "cooks" + File.separator;
			String fileName = cmsCooksBean.getImagePath();

			if (fileName != null) {
				File image = new File(rootPath + folderPath + fileName);
				InputStream in = null;
				try {
					in = new FileInputStream(image);
					// cmsCooksBean.setImage(IOUtils.toByteArray(in));
					cmsCooksBean.setImageURL(mainURL + folderPath + fileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			cmsCooksBeanList.add(cmsCooksBean);
		}

		objectListBean.setCmsCooksBeanList(cmsCooksBeanList);
		objectListBean.setCount(count);

		return objectListBean;
	}

	@Override
	public int edit(CMSCooksBean cmsCooksBean, HttpServletResponse response) {
		CMSCooksData cmsCooksData = null;
		CastData castData = null;
		cmsCooksData = cmsCooksUtility.populateCMSCooks(cmsCooksBean);
		castData = otherDao.getCast(cmsCooksBean.getCast());
		cmsCooksData.setCastData(castData);
		return cmsCooksDao.edit(cmsCooksData, response);
	}

	@Override
	public List<CMSCooksBean> get(String cookName, HttpServletResponse response) {
		List<CMSCooksBean> cmsCooksBeanList = new ArrayList<CMSCooksBean>();
		List<CMSCooksData> cmsCooksDataList = null;
		cmsCooksDataList = cmsCooksDao.get(cookName, response);
		for (CMSCooksData cmsCooksData : cmsCooksDataList) {
			CMSCooksBean cmsCooksBean = cmsCooksUtility
					.populateCMSCooks(cmsCooksData);
			cmsCooksBeanList.add(cmsCooksBean);
		}
		return cmsCooksBeanList;
	}

	@Override
	public String uploadImage(String imageName, int cookId,
			HttpServletResponse response) {
		CMSCooksData cmsCooksData = cmsCooksDao.get(cookId, response);
		String previousFilePath = cmsCooksData.getImagePath();
		cmsCooksData.setImagePath(imageName);
		cmsCooksDao.updateMenu(cmsCooksData, response);
		return previousFilePath;
	}

	@Override
	public List<CMSCooksSpecialityBean> getCooksSpeciality() {
		List<CMSCooksSpecialityBean> cmsCooksSpecialityBeanlist = new ArrayList<CMSCooksSpecialityBean>();
		CMSCooksSpecialityBean cmsCooksSpecialityBean = null;
		List<CMSCooksSpecialityData> cmsCooksSpecialityDataList = cmsCooksDao
				.getCooksSpeciality();
		for (CMSCooksSpecialityData cmsCooksSpecialityData : cmsCooksSpecialityDataList) {
			cmsCooksSpecialityBean = cmsCooksUtility
					.populateCMSCooksSpeciality(cmsCooksSpecialityData);
			cmsCooksSpecialityBeanlist.add(cmsCooksSpecialityBean);
		}
		return cmsCooksSpecialityBeanlist;
	}

}
