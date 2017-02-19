package com.beingjavaguys.services.cmsmenu.impl;

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

import com.beingjavaguys.bean.cmsmenu.CMSMenuBean;
import com.beingjavaguys.bean.generic.BeanList;
import com.beingjavaguys.dao.cmscooks.CMSCooksDao;
import com.beingjavaguys.dao.cmsmenu.CMSMenuCatagoryDao;
import com.beingjavaguys.dao.cmsmenu.CMSMenuDao;
import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.models.cmsmenu.CMSMenuCatagoryData;
import com.beingjavaguys.models.cmsmenu.CMSMenuData;
import com.beingjavaguys.services.cmsmenu.CMSMenuService;
import com.beingjavaguys.utility.cmsmenu.CMSMenuUtility;

@Service("cmsMenuService")
public class CMSMenuServiceImpl implements CMSMenuService {

	@Autowired
	CMSMenuUtility cmsMenuUtility;

	@Autowired
	CMSMenuDao cmsMenuDao;

	@Autowired
	CMSMenuCatagoryDao cmsMenuCatagoryDao;

	@Autowired
	CMSCooksDao cmsCooksDao;

	@Autowired
	ServletContext servletContext;

	@Override
	public int addMenu(CMSMenuBean cmsMenuBean, HttpServletResponse response) {
		CMSMenuData cmsMenuData = null;
		cmsMenuData = cmsMenuUtility.populateCMSMenuData(cmsMenuBean);

		CMSMenuCatagoryData cmsMenuCatagoryData = cmsMenuCatagoryDao.get(
				cmsMenuBean.getMenuCatagoryId(), response);
		cmsMenuData.setCmsMenuCatagoryData(cmsMenuCatagoryData);

		CMSCooksData cmsCooksData = cmsCooksDao.get(cmsMenuBean.getCooksId(),
				response);
		cmsMenuData.setCmsCooksData(cmsCooksData);

		return cmsMenuDao.addMenu(cmsMenuData, response);
	}

	@Override
	public CMSMenuBean getMenu(int menuId) {
		CMSMenuData cmsMenuData = cmsMenuDao.getMenu(menuId);
		CMSMenuBean cmsMenuBean = cmsMenuUtility
				.populateCMSMenuBean(cmsMenuData);
		return cmsMenuBean;
	}

	@Override
	public String uploadMenuImage(String imageName, int menuId,
			HttpServletResponse response) {
		CMSMenuData cmsMenuData = cmsMenuDao.getMenu(menuId);
		String previousFilePath = cmsMenuData.getMenuImagePath();
		cmsMenuData.setMenuImagePath(imageName);
		cmsMenuDao.updateMenu(cmsMenuData, response);
		return previousFilePath;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BeanList get(int limit, int pageno, int cookId, int catagoryId,
			HttpServletResponse response) {
		BeanList objectListBean = new BeanList();
		List<CMSMenuBean> cmsMenuBeanList = new ArrayList<CMSMenuBean>();
		List<CMSMenuData> cmsMenuDataList = null;

		CMSCooksData cmsCooksData = cmsCooksDao.get(cookId, response);
		CMSMenuCatagoryData cmsMenuCatagoryData = cmsMenuCatagoryDao.get(
				catagoryId, response);

		List<Object> list = cmsMenuDao.get(limit, pageno, cmsCooksData,
				cmsMenuCatagoryData);

		cmsMenuDataList = (List<CMSMenuData>) list.get(0);
		int count = (int) list.get(1);

		for (CMSMenuData cmsMenuData : cmsMenuDataList) {
			CMSMenuBean cmsMenuBean = cmsMenuUtility
					.populateCMSMenuBean(cmsMenuData);
			String rootPath = servletContext.getRealPath("/");
			String folderPath = rootPath + File.separator + "image"
					+ File.separator + "menu_item" + File.separator;
			String fileName = cmsMenuBean.getMenuImagePath();
			
			if(fileName!=null){
				File image = new File(folderPath + fileName);
				InputStream in = null;
				try {
					in = new FileInputStream(image);
					cmsMenuBean.setImage(IOUtils.toByteArray(in));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			cmsMenuBeanList.add(cmsMenuBean);
		}

		objectListBean.setCmsMenuBeanList(cmsMenuBeanList);
		objectListBean.setCount(count);

		return objectListBean;
	}

}
