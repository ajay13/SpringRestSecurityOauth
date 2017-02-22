package com.beingjavaguys.services.cmsmenu;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.bean.cmsmenu.CMSMenuBean;
import com.beingjavaguys.bean.generic.BeanList;

public interface CMSMenuService {
	int add(CMSMenuBean cmsMenuBean, HttpServletResponse response);

	CMSMenuBean get(int menuId);

	String uploadMenuImage(String imageName, int menuId, HttpServletResponse response);

	public BeanList get(int limit, int pageno, int cookId, int catagoryId, HttpServletResponse response);
	
	void delete(int id, HttpServletResponse response);
	
	int edit(CMSMenuBean cmsMenuBean, HttpServletResponse response);
}
