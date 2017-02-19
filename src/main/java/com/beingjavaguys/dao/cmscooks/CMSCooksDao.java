package com.beingjavaguys.dao.cmscooks;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.models.cmscooks.CMSCooksData;

public interface CMSCooksDao {
	public void add(CMSCooksData cmsCooksData, HttpServletResponse response);

	public void delete(CMSCooksData cmsCooksData, HttpServletResponse response);

	public CMSCooksData get(int id, HttpServletResponse response);

	public List<Object> get(int limit, int pageno);

	public void edit(CMSCooksData cmsCooksData, HttpServletResponse response);
	
	public List<CMSCooksData> get(String cookName, HttpServletResponse response);
}
