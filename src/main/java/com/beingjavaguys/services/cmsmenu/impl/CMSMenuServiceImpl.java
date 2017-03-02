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
import com.beingjavaguys.bean.cmsmenu.CMSMenuPriceBean;
import com.beingjavaguys.bean.cmsmenu.CMSMenuUnitBean;
import com.beingjavaguys.bean.generic.BeanList;
import com.beingjavaguys.dao.cmscooks.CMSCooksDao;
import com.beingjavaguys.dao.cmsmenu.CMSMenuCatagoryDao;
import com.beingjavaguys.dao.cmsmenu.CMSMenuDao;
import com.beingjavaguys.dao.cmsmenu.CMSMenuUnitDao;
import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.models.cmsmenu.CMSMenuCatagoryData;
import com.beingjavaguys.models.cmsmenu.CMSMenuData;
import com.beingjavaguys.models.cmsmenu.CMSMenuPriceData;
import com.beingjavaguys.models.cmsmenu.CMSMenuUnitData;
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
	
	@Autowired
	CMSMenuUnitDao cmsMenuUnitDao;

	@SuppressWarnings("null")
	@Override
	public int add(CMSMenuBean cmsMenuBean, HttpServletResponse response) {
		CMSMenuData cmsMenuData = null;
		List<CMSMenuPriceData> cmsMenuPriceDataList = new ArrayList<CMSMenuPriceData>();
		CMSMenuPriceData cmsMenuPriceData = null;
		CMSMenuUnitData cmsMenuUnitData = null;
		
		cmsMenuData = cmsMenuUtility.populateCMSMenuData(cmsMenuBean);

		CMSMenuCatagoryData cmsMenuCatagoryData = cmsMenuCatagoryDao.get(
				cmsMenuBean.getMenuCatagoryId(), response);
		cmsMenuData.setCmsMenuCatagoryData(cmsMenuCatagoryData);

		CMSCooksData cmsCooksData = cmsCooksDao.get(cmsMenuBean.getCooksId(),
				response);
		cmsMenuData.setCmsCooksData(cmsCooksData);
		
		for(CMSMenuPriceBean cmsMenuPriceBean : cmsMenuBean.getCmsMenuPriceBeanList()){
			cmsMenuUnitData = cmsMenuUnitDao.get(cmsMenuPriceBean.getUnitName());
			cmsMenuPriceData = new CMSMenuPriceData();
			cmsMenuPriceData.setCmsMenuUnitData(cmsMenuUnitData);
			cmsMenuPriceData.setPrice(cmsMenuPriceBean.getPrice());
			cmsMenuPriceDataList.add(cmsMenuPriceData);
		}
		cmsMenuData.setCmsMenuPriceDataList(cmsMenuPriceDataList);

		return cmsMenuDao.add(cmsMenuData, response);
	}

	@Override
	public CMSMenuBean get(int menuId) {
		CMSMenuData cmsMenuData = cmsMenuDao.get(menuId);
		CMSMenuBean cmsMenuBean = cmsMenuUtility
				.populateCMSMenuBean(cmsMenuData);
		return cmsMenuBean;
	}

	@Override
	public String uploadMenuImage(String imageName, int menuId,
			HttpServletResponse response) {
		CMSMenuData cmsMenuData = cmsMenuDao.get(menuId);
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
		List<CMSMenuPriceBean> cmsMenuPriceBeanList =null;
		List<CMSMenuBean> cmsMenuBeanList = new ArrayList<CMSMenuBean>();
		List<CMSMenuData> cmsMenuDataList = null;
		CMSMenuPriceBean cmsMenuPriceBean = null;
		
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
			cmsMenuPriceBeanList = new ArrayList<CMSMenuPriceBean>();
			for(CMSMenuPriceData cmsMenuPriceData : cmsMenuData.getCmsMenuPriceDataList()){
				cmsMenuPriceBean = new CMSMenuPriceBean();
				cmsMenuPriceBean.setId(cmsMenuPriceData.getId());
				cmsMenuPriceBean.setPrice(cmsMenuPriceData.getPrice());
				cmsMenuPriceBean.setUnitName(cmsMenuPriceData.getCmsMenuUnitData().getUnit());
				cmsMenuPriceBeanList.add(cmsMenuPriceBean);
			}
			cmsMenuBean.setCmsMenuPriceBeanList(cmsMenuPriceBeanList);
			
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

	@Override
	public void delete(int id, HttpServletResponse response) {
		CMSMenuData cmsMenuData = null;
		cmsMenuData = cmsMenuDao.get(id);
		cmsMenuDao.delete(cmsMenuData, response);
	}

	@Override
	public int edit(CMSMenuBean cmsMenuBean, HttpServletResponse response) {
		CMSMenuData cmsMenuData = null;
		cmsMenuData = cmsMenuUtility.populateCMSMenuData(cmsMenuBean);
		
		CMSMenuCatagoryData cmsMenuCatagoryData = cmsMenuCatagoryDao.get(
				cmsMenuBean.getMenuCatagoryId(), response);
		cmsMenuData.setCmsMenuCatagoryData(cmsMenuCatagoryData);

		CMSCooksData cmsCooksData = cmsCooksDao.get(cmsMenuBean.getCooksId(),
				response);
		cmsMenuData.setCmsCooksData(cmsCooksData);
		
		return cmsMenuDao.edit(cmsMenuData, response);
	}

	@Override
	public List<CMSMenuUnitBean> getMenuUnit() {
		List<CMSMenuUnitBean> cmsMenuUnitBeanList = new ArrayList<CMSMenuUnitBean>();
		CMSMenuUnitBean cmsMenuUnitBean = null;
		List<CMSMenuUnitData> cmsMenuUnitDataList = cmsMenuUnitDao.getMenuUnit();
		for(CMSMenuUnitData cmsMenuUnitData : cmsMenuUnitDataList){
		    cmsMenuUnitBean = cmsMenuUtility.populateCMSMenuUnitBean(cmsMenuUnitData);
		    cmsMenuUnitBeanList.add(cmsMenuUnitBean);
		}
		return cmsMenuUnitBeanList;
	}

}
