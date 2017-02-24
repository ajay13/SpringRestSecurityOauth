package com.beingjavaguys.dao.cmsmenu;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.models.cmsmenu.CMSMenuCatagoryData;

public interface CMSMenuCatagoryDao {

	public void add(CMSMenuCatagoryData cmsMenuCatagoryData, HttpServletResponse response);

	public void delete(CMSMenuCatagoryData cmsMenuCatagoryData, HttpServletResponse response);

	public CMSMenuCatagoryData get(int id, HttpServletResponse response);

	public List<Object> get(int limit, int pageno);

	public void edit(CMSMenuCatagoryData cmsMenuCatagoryData, HttpServletResponse response);

	public List<CMSMenuCatagoryData> get(String catagoryName, HttpServletResponse response);

	public List<CMSMenuCatagoryData> get();

	public void grouppingAndSeq(List<Integer> catagoryIds, String groupName);
}
