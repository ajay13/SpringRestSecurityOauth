package com.beingjavaguys.services.cmsmenu;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.bean.cmsmenu.CMSMenuBean;
import com.beingjavaguys.bean.cmsmenu.CMSMenuUnitBean;
import com.beingjavaguys.bean.generic.BeanList;
import com.beingjavaguys.bean.other.CookSpecialityBean;

public interface CMSMenuService {
	int add(CMSMenuBean cmsMenuBean, HttpServletResponse response);

	CMSMenuBean get(int menuId);

	String uploadMenuImage(String imageName, int menuId,
			HttpServletResponse response);

	public BeanList get(int limit, int pageno, int cookId, int catagoryId,
			HttpServletResponse response);

	void delete(int id, HttpServletResponse response);

	int edit(CMSMenuBean cmsMenuBean, HttpServletResponse response);

	List<CMSMenuUnitBean> getMenuUnit();

	public List<CMSMenuBean> get(int catagoryId, int specialityId,
			HttpServletResponse response);

	public void addSpeciality(CookSpecialityBean cookSpecialityBean,
			HttpServletResponse response);

	public CookSpecialityBean getCookSpeciality(int CookId, String speciality,
			HttpServletResponse response);

	public void deleteCookSpeciality(int cookId, String speciality,
			HttpServletResponse response);
}
