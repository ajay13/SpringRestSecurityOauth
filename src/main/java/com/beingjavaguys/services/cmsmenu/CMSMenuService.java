package com.beingjavaguys.services.cmsmenu;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.bean.cmsmenu.CMSMenuBean;
import com.beingjavaguys.bean.generic.BeanList;

public interface CMSMenuService {
	int addMenu(CMSMenuBean cmsMenuBean, HttpServletResponse response);

	CMSMenuBean getMenu(int menuId);

	String uploadMenuImage(String imageName, int menuId,
			HttpServletResponse response);

	public BeanList get(int limit, int pageno, int cookId, int catagoryId,HttpServletResponse response);
}
