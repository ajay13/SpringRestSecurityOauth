package com.beingjavaguys.utility.cmsmenu;

import org.springframework.stereotype.Component;

import com.beingjavaguys.bean.cmsmenu.CMSMenuBean;
import com.beingjavaguys.bean.cmsmenu.CMSMenuUnitBean;
import com.beingjavaguys.models.cmsmenu.CMSMenuData;
import com.beingjavaguys.models.cmsmenu.CMSMenuUnitData;

@Component("cmsMenuUtility")
public class CMSMenuUtility {
	public CMSMenuData populateCMSMenuData(CMSMenuBean cmsMenuBean) {
		CMSMenuData cmsMenuData = new CMSMenuData();
		
		cmsMenuData.setItemName(cmsMenuBean.getItemName());
		cmsMenuData.setDescription(cmsMenuBean.getDescription());
		cmsMenuData.setId(cmsMenuBean.getId());
		cmsMenuData.setMenuImagePath(cmsMenuBean.getMenuImagePath());
		cmsMenuData.setUnit(cmsMenuBean.getUnit());
		
		return cmsMenuData;
	}
	
	public CMSMenuBean populateCMSMenuBean(CMSMenuData cmsMenuData) {
		CMSMenuBean cmsMenuBean = new CMSMenuBean();

		cmsMenuBean.setItemName(cmsMenuData.getItemName());
		cmsMenuBean.setDescription(cmsMenuData.getDescription());
		cmsMenuBean.setMenuImagePath(cmsMenuData.getMenuImagePath());
		cmsMenuBean.setId(cmsMenuData.getId());
		cmsMenuBean.setUnit(cmsMenuData.getUnit());
		return cmsMenuBean;
	}
	
	public CMSMenuUnitBean populateCMSMenuUnitBean(CMSMenuUnitData cmsMenuUnitData){
		CMSMenuUnitBean cmsMenuUnitBean = new CMSMenuUnitBean();
		
		cmsMenuUnitBean.setId(cmsMenuUnitData.getId());
		cmsMenuUnitBean.setUnit(cmsMenuUnitData.getUnit());
		
		return cmsMenuUnitBean;
	}
}
