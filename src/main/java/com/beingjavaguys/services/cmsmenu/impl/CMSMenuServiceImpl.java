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
import com.beingjavaguys.bean.other.CookSpecialityBean;
import com.beingjavaguys.dao.cmscooks.CMSCooksDao;
import com.beingjavaguys.dao.cmsmenu.CMSMenuCatagoryDao;
import com.beingjavaguys.dao.cmsmenu.CMSMenuDao;
import com.beingjavaguys.dao.cmsmenu.CMSMenuUnitDao;
import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.models.cmscooks.CMSCooksSpecialityData;
import com.beingjavaguys.models.cmscooks.CookSpecialityMenuData;
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

		for (CMSMenuPriceBean cmsMenuPriceBean : cmsMenuBean
				.getCmsMenuPriceBeanList()) {
			cmsMenuUnitData = cmsMenuUnitDao
					.get(cmsMenuPriceBean.getUnitName());
			cmsMenuPriceData = new CMSMenuPriceData();
			cmsMenuPriceData.setCmsMenuUnitData(cmsMenuUnitData);
			cmsMenuPriceData.setPrice(cmsMenuPriceBean.getPrice());
			cmsMenuPriceDataList.add(cmsMenuPriceData);
		}
		//cmsMenuData.setCmsMenuPriceDataList(cmsMenuPriceDataList);

		return cmsMenuDao.add(cmsMenuData,cmsMenuPriceDataList,response);
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
			HttpServletResponse response,String url) {
		String contextPath = servletContext.getContextPath();
	    String mainURL = (url.substring(0, url.indexOf(contextPath)))+(contextPath);
	    
		BeanList objectListBean = new BeanList();
		List<CMSMenuPriceBean> cmsMenuPriceBeanList = null;
		List<CMSMenuBean> cmsMenuBeanList = new ArrayList<CMSMenuBean>();
		List<CMSMenuData> cmsMenuDataList = null;
		CMSMenuPriceBean cmsMenuPriceBean = null;

		CMSCooksData cmsCooksData = cmsCooksDao.get(cookId, response);
		CMSMenuCatagoryData cmsMenuCatagoryData = cmsMenuCatagoryDao.get(
				catagoryId, response);

		List<Object> list = cmsMenuDao.get(limit, pageno, cmsCooksData,
				cmsMenuCatagoryData);

		if (list != null && list.size() > 0) {
			cmsMenuDataList = (List<CMSMenuData>) list.get(0);
			int count = (int) list.get(1);

			for (CMSMenuData cmsMenuData : cmsMenuDataList) {
				CMSMenuBean cmsMenuBean = cmsMenuUtility
						.populateCMSMenuBean(cmsMenuData);
				
				cmsMenuBean.setCooksId(cmsMenuData.getCmsCooksData().getId());
				cmsMenuBean.setMenuCatagoryId(cmsMenuData.getCmsMenuCatagoryData().getId());
				cmsMenuPriceBeanList = new ArrayList<CMSMenuPriceBean>();
				for (CMSMenuPriceData cmsMenuPriceData : cmsMenuData
						.getCmsMenuPriceDataList()) {
					cmsMenuPriceBean = new CMSMenuPriceBean();
					cmsMenuPriceBean.setId(cmsMenuPriceData.getId());
					cmsMenuPriceBean.setPrice(cmsMenuPriceData.getPrice());
					cmsMenuPriceBean.setUnitName(cmsMenuPriceData
							.getCmsMenuUnitData().getUnit());
					cmsMenuPriceBeanList.add(cmsMenuPriceBean);
				}
				cmsMenuBean.setCmsMenuPriceBeanList(cmsMenuPriceBeanList);

				String rootPath = servletContext.getRealPath("/");
				String folderPath =   File.separator + "image"
						+ File.separator + "menu_item" + File.separator;
				String fileName = cmsMenuBean.getMenuImagePath();

				if (fileName != null) {
					File image = new File(rootPath + folderPath + fileName);
					InputStream in = null;
					try {
						in = new FileInputStream(image);
						//cmsMenuBean.setImage(IOUtils.toByteArray(in));
						cmsMenuBean.setImageURL(mainURL + folderPath + fileName);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				cmsMenuBeanList.add(cmsMenuBean);
			}

			objectListBean.setCmsMenuBeanList(cmsMenuBeanList);
			objectListBean.setCount(count);
		}

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
		//CMSMenuData cmsMenuData = null;
		List<CMSMenuPriceData> cmsMenuPriceDataList = new ArrayList<CMSMenuPriceData>();
		CMSMenuPriceData cmsMenuPriceData = null;
		CMSMenuUnitData cmsMenuUnitData = null;
		
		CMSMenuData cmsMenuData = cmsMenuDao.get(cmsMenuBean.getId());
		
		cmsMenuDao.deleteMenuPriceDataOnly(cmsMenuBean.getId());
		
		cmsMenuData = cmsMenuUtility.populateCMSMenuData(cmsMenuBean);

		CMSMenuCatagoryData cmsMenuCatagoryData = cmsMenuCatagoryDao.get(
				cmsMenuBean.getMenuCatagoryId(), response);
		cmsMenuData.setCmsMenuCatagoryData(cmsMenuCatagoryData);

		CMSCooksData cmsCooksData = cmsCooksDao.get(cmsMenuBean.getCooksId(),
				response);
		cmsMenuData.setCmsCooksData(cmsCooksData);
		
		for (CMSMenuPriceBean cmsMenuPriceBean : cmsMenuBean
				.getCmsMenuPriceBeanList()) {
			cmsMenuUnitData = cmsMenuUnitDao
					.get(cmsMenuPriceBean.getUnitName());
			cmsMenuPriceData = new CMSMenuPriceData();
			cmsMenuPriceData.setCmsMenuUnitData(cmsMenuUnitData);
			cmsMenuPriceData.setPrice(cmsMenuPriceBean.getPrice());
			cmsMenuPriceDataList.add(cmsMenuPriceData);
		}
		//cmsMenuData.setCmsMenuPriceDataList(cmsMenuPriceDataList);

		return cmsMenuDao.edit(cmsMenuData,cmsMenuPriceDataList,response);
	}

	@Override
	public List<CMSMenuUnitBean> getMenuUnit() {
		List<CMSMenuUnitBean> cmsMenuUnitBeanList = new ArrayList<CMSMenuUnitBean>();
		CMSMenuUnitBean cmsMenuUnitBean = null;
		List<CMSMenuUnitData> cmsMenuUnitDataList = cmsMenuUnitDao
				.getMenuUnit();
		for (CMSMenuUnitData cmsMenuUnitData : cmsMenuUnitDataList) {
			cmsMenuUnitBean = cmsMenuUtility
					.populateCMSMenuUnitBean(cmsMenuUnitData);
			cmsMenuUnitBeanList.add(cmsMenuUnitBean);
		}
		return cmsMenuUnitBeanList;
	}

	@Override
	public List<CMSMenuBean> get(int cookId, int specialityId,
			HttpServletResponse response) {
		List<CMSMenuBean> cmsMenuBeanList = new ArrayList<CMSMenuBean>();
		CMSCooksData cmsCooksData = cmsCooksDao.get(cookId, response);
		CMSCooksSpecialityData cmsMenuCatagoryData = new CMSCooksSpecialityData();
		List<CMSMenuData> cmsMenuDataList = cmsMenuDao.get(cmsCooksData,
				cmsMenuCatagoryData);
		for (CMSMenuData cmsMenuData : cmsMenuDataList) {
			CMSMenuBean cmsMenuBean = cmsMenuUtility
					.populateCMSMenuBean(cmsMenuData);
			cmsMenuBeanList.add(cmsMenuBean);
		}
		return cmsMenuBeanList;
	}

	@SuppressWarnings("null")
	@Override
	public void addSpeciality(CookSpecialityBean cookSpecialityBean,
			HttpServletResponse response) {

		CMSCooksSpecialityData cmsCooksSpecialityData = cmsCooksDao
				.getCooksSpeciality(cookSpecialityBean.getSpeciality());
		CMSCooksData cmsCooksData = cmsCooksDao.get(
				cookSpecialityBean.getCookId(), response);
		CookSpecialityMenuData cookSpecialityMenuData = cmsMenuDao
				.getCookSpeciality(cmsCooksData, cmsCooksSpecialityData);

		if (cookSpecialityMenuData == null) {
			cookSpecialityMenuData = new CookSpecialityMenuData();
			cookSpecialityMenuData.setCmsCooksData(cmsCooksData);
			cookSpecialityMenuData
					.setCmsCooksSpecialityData(cmsCooksSpecialityData);
		}
		
		CMSMenuData cmsMenuData = null;
		List<CMSMenuData> cmsMenuDataList = new ArrayList<CMSMenuData>();
		for (Integer menuId : cookSpecialityBean.getMenuIds()) {
			cmsMenuData = cmsMenuDao.get(menuId);
			cmsMenuDataList.add(cmsMenuData);
		}

		cookSpecialityMenuData.setCmsMenuDataList(cmsMenuDataList);

		cmsMenuDao.addSpeciality(cookSpecialityMenuData);
	}

	@Override
	public CookSpecialityBean getCookSpeciality(int cookId, String speciality,
			HttpServletResponse response) {
		CookSpecialityBean cookSpecialityBean = new CookSpecialityBean();
		List<Integer> menuIdList = new ArrayList<Integer>();
		CMSCooksData cmsCooksData = cmsCooksDao.get(cookId, response);
		CMSCooksSpecialityData cmsCooksSpecialityData = cmsCooksDao
				.getCooksSpeciality(speciality);
		CookSpecialityMenuData cookSpecialityMenuData = cmsMenuDao
				.getCookSpeciality(cmsCooksData, cmsCooksSpecialityData);
		if (cookSpecialityMenuData != null) {
			cookSpecialityBean.setCookId(cookSpecialityMenuData
					.getCmsCooksData().getId());
			cookSpecialityBean.setSpeciality(cookSpecialityMenuData
					.getCmsCooksSpecialityData().getSpeciality());
			for (CMSMenuData cmsMenuData : cookSpecialityMenuData
					.getCmsMenuDataList()) {
				menuIdList.add(cmsMenuData.getId());
			}
		}
		cookSpecialityBean.setMenuIds(menuIdList);
		return cookSpecialityBean;
	}

	@Override
	public void deleteCookSpeciality(int cookId, String speciality,
			HttpServletResponse response) {
		CMSCooksData cmsCooksData = cmsCooksDao.get(cookId, response);
		CMSCooksSpecialityData cmsCooksSpecialityData = cmsCooksDao
				.getCooksSpeciality(speciality);
		CookSpecialityMenuData cookSpecialityMenuData = cmsMenuDao
				.getCookSpeciality(cmsCooksData, cmsCooksSpecialityData);
		if (cookSpecialityMenuData != null) {
			cmsMenuDao.deleteCookSpecialityMenu(cookSpecialityMenuData);
		}
	}

	@Override
	public BeanList getCooksOfItem(int limit, int pageno,
			String itemName, HttpServletResponse response) {
		BeanList objectListBean = new BeanList();
		List<CMSMenuPriceBean> cmsMenuPriceBeanList = null;
		List<CMSMenuBean> cmsMenuBeanList = new ArrayList<CMSMenuBean>();
		List<CMSMenuData> cmsMenuDataList = null;
		CMSMenuPriceBean cmsMenuPriceBean = null;

		List<Object> list = cmsMenuDao.getCooksOfItem(limit, pageno, itemName);

		if (list != null && list.size() > 0) {
			cmsMenuDataList = (List<CMSMenuData>) list.get(0);
			int count = (int) list.get(1);

			for (CMSMenuData cmsMenuData : cmsMenuDataList) {
				CMSMenuBean cmsMenuBean = cmsMenuUtility
						.populateCMSMenuBean(cmsMenuData);
				
				cmsMenuBean.setCooksId(cmsMenuData.getCmsCooksData().getId());
				cmsMenuBean.setMenuCatagoryId(cmsMenuData.getCmsMenuCatagoryData().getId());
				cmsMenuPriceBeanList = new ArrayList<CMSMenuPriceBean>();
				for (CMSMenuPriceData cmsMenuPriceData : cmsMenuData
						.getCmsMenuPriceDataList()) {
					cmsMenuPriceBean = new CMSMenuPriceBean();
					cmsMenuPriceBean.setId(cmsMenuPriceData.getId());
					cmsMenuPriceBean.setPrice(cmsMenuPriceData.getPrice());
					cmsMenuPriceBean.setUnitName(cmsMenuPriceData
							.getCmsMenuUnitData().getUnit());
					cmsMenuPriceBeanList.add(cmsMenuPriceBean);
				}
				cmsMenuBean.setCmsMenuPriceBeanList(cmsMenuPriceBeanList);

				String rootPath = servletContext.getRealPath("/");
				String folderPath = rootPath + File.separator + "image"
						+ File.separator + "menu_item" + File.separator;
				String fileName = cmsMenuBean.getMenuImagePath();

				if (fileName != null) {
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
		}

		return objectListBean;
	}
}
