package com.beingjavaguys.dao.cmsmenu;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.models.cmscooks.CMSCooksSpecialityData;
import com.beingjavaguys.models.cmscooks.CookSpecialityMenuData;
import com.beingjavaguys.models.cmsmenu.CMSMenuCatagoryData;
import com.beingjavaguys.models.cmsmenu.CMSMenuData;

public interface CMSMenuDao {
	public int add(CMSMenuData cmsMenuData, HttpServletResponse response);

	CMSMenuData get(int menuId);

	public void updateMenu(CMSMenuData cmsMenuData, HttpServletResponse response);

	public List<Object> get(int limit, int pageno, CMSCooksData cmsCooksData,
			CMSMenuCatagoryData cmsMenuCatagoryData);

	public int edit(CMSMenuData cmsMenuData, HttpServletResponse response);

	public void delete(CMSMenuData cmsMenuData, HttpServletResponse response);

	public List<CMSMenuData> get(CMSCooksData cmsCooksData,
			CMSCooksSpecialityData cmsCooksSpecialityData);

	public void addSpeciality(CookSpecialityMenuData cookSpecialityMenuData);

	public CookSpecialityMenuData getCookSpeciality(CMSCooksData cmsCooksData,
			CMSCooksSpecialityData cmsCooksSpecialityData);

	public void deleteCookSpecialityMenu(
			CookSpecialityMenuData cookSpecialityMenuData);
}
