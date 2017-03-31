package com.beingjavaguys.dao.cmsmenu;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.models.cmscooks.CMSCooksSpecialityData;
import com.beingjavaguys.models.cmscooks.CookSpecialityMenuData;
import com.beingjavaguys.models.cmsmenu.CMSMenuCatagoryData;
import com.beingjavaguys.models.cmsmenu.CMSMenuData;
import com.beingjavaguys.models.cmsmenu.CMSMenuPriceData;

public interface CMSMenuDao {
	public int add(CMSMenuData cmsMenuData,
			List<CMSMenuPriceData> cmsMenuPriceDataList,
			HttpServletResponse response);

	CMSMenuData get(int menuId);

	public void updateMenu(CMSMenuData cmsMenuData, HttpServletResponse response);

	public List<Object> get(int limit, int pageno, CMSCooksData cmsCooksData,
			CMSMenuCatagoryData cmsMenuCatagoryData);

	public int edit(CMSMenuData cmsMenuData,
			List<CMSMenuPriceData> cmsMenuPriceDataList,
			HttpServletResponse response);

	public void delete(CMSMenuData cmsMenuData, HttpServletResponse response);

	public List<CMSMenuData> get(CMSCooksData cmsCooksData,
			CMSCooksSpecialityData cmsCooksSpecialityData);

	public void addSpeciality(CookSpecialityMenuData cookSpecialityMenuData);

	public CookSpecialityMenuData getCookSpeciality(CMSCooksData cmsCooksData,
			CMSCooksSpecialityData cmsCooksSpecialityData);

	public void deleteCookSpecialityMenu(
			CookSpecialityMenuData cookSpecialityMenuData);

	public List<Object> getCooksOfItem(int limit, int pageno, String itemName);

	public void deleteMenuPriceDataOnly(int menuId);
	
	public List<CMSMenuPriceData> getMenuPriceData(int menuId);
}
