package com.beingjavaguys.utility.cmsmenu;

import org.springframework.stereotype.Component;

import com.beingjavaguys.bean.cmsmenu.CMSCatagoryBean;
import com.beingjavaguys.models.cmsmenu.CMSMenuCatagoryData;

@Component("cmsCatagoryUtility")
public class CMSCatagoryUtility {
	public CMSMenuCatagoryData populateCMSMenuCatagory(
			CMSCatagoryBean cmsCatagoryBean) {
		CMSMenuCatagoryData cmsMenuCatagoryData = new CMSMenuCatagoryData();
		cmsMenuCatagoryData.setId(cmsCatagoryBean.getId());
		cmsMenuCatagoryData.setName(cmsCatagoryBean.getName());
		cmsMenuCatagoryData.setDescription(cmsCatagoryBean.getDescription());
		cmsMenuCatagoryData.setGroupName(cmsCatagoryBean.getGroupName());
		cmsMenuCatagoryData.setSequence(cmsCatagoryBean.getSequence());
		return cmsMenuCatagoryData;
	}

	public CMSCatagoryBean populateCMSMenuCatagory(
			CMSMenuCatagoryData cmsMenuCatagoryData) {
		CMSCatagoryBean cmsCatagoryBean = new CMSCatagoryBean();
		cmsCatagoryBean.setId(cmsMenuCatagoryData.getId());
		cmsCatagoryBean.setName(cmsMenuCatagoryData.getName());
		cmsCatagoryBean.setDescription(cmsMenuCatagoryData.getDescription());
		cmsCatagoryBean.setGroupName(cmsMenuCatagoryData.getGroupName());
		cmsCatagoryBean.setSequence(cmsMenuCatagoryData.getSequence());
		return cmsCatagoryBean;
	}
}
