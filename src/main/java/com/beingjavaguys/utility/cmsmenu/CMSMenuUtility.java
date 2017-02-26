package com.beingjavaguys.utility.cmsmenu;

import org.springframework.stereotype.Component;

import com.beingjavaguys.bean.cmsmenu.CMSMenuBean;
import com.beingjavaguys.bean.cmsmenu.CMSMenuPriceBean;
import com.beingjavaguys.models.cmsmenu.CMSMenuData;
import com.beingjavaguys.models.cmsmenu.CMSMenuPriceData;

@Component("cmsMenuUtility")
public class CMSMenuUtility {
	public CMSMenuData populateCMSMenuData(CMSMenuBean cmsMenuBean) {
		CMSMenuData cmsMenuData = new CMSMenuData();
		
		cmsMenuData.setItemName(cmsMenuBean.getItemName());
		cmsMenuData.setDescription(cmsMenuBean.getDescription());
		cmsMenuData.setId(cmsMenuBean.getId());
		cmsMenuData.setMenuImagePath(cmsMenuBean.getMenuImagePath());
		cmsMenuData.setUnit(cmsMenuBean.isUnit());
		
		return cmsMenuData;
	}
	
	public CMSMenuBean populateCMSMenuBean(CMSMenuData cmsMenuData) {
		CMSMenuBean cmsMenuBean = new CMSMenuBean();

		cmsMenuBean.setItemName(cmsMenuData.getItemName());
		cmsMenuBean.setDescription(cmsMenuData.getDescription());
		cmsMenuBean.setMenuImagePath(cmsMenuData.getMenuImagePath());
		cmsMenuBean.setId(cmsMenuData.getId());
		return cmsMenuBean;
	}
}
