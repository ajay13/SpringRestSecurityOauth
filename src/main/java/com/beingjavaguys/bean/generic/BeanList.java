package com.beingjavaguys.bean.generic;

import java.util.List;

import com.beingjavaguys.bean.cmscooks.CMSCooksBean;
import com.beingjavaguys.bean.cmsmenu.CMSCatagoryBean;
import com.beingjavaguys.bean.cmsmenu.CMSMenuBean;

public class BeanList {

	public List<CMSCooksBean> cmsCooksBeanList;

	public List<CMSCatagoryBean> cmsCatagoryBeanList;

	public List<CMSMenuBean> cmsMenuBeanList;

	int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<CMSCooksBean> getCmsCooksBeanList() {
		return cmsCooksBeanList;
	}

	public void setCmsCooksBeanList(List<CMSCooksBean> cmsCooksBeanList) {
		this.cmsCooksBeanList = cmsCooksBeanList;
	}

	public List<CMSCatagoryBean> getCmsCatagoryBeanList() {
		return cmsCatagoryBeanList;
	}

	public void setCmsCatagoryBeanList(List<CMSCatagoryBean> cmsCatagoryBeanList) {
		this.cmsCatagoryBeanList = cmsCatagoryBeanList;
	}

	public List<CMSMenuBean> getCmsMenuBeanList() {
		return cmsMenuBeanList;
	}

	public void setCmsMenuBeanList(List<CMSMenuBean> cmsMenuBeanList) {
		this.cmsMenuBeanList = cmsMenuBeanList;
	}

}
