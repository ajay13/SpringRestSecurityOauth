package com.beingjavaguys.dao.cmscooks;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.models.cmscooks.CMSCooksData;
import com.beingjavaguys.models.cmscooks.CMSCooksSpecialityData;

public interface CMSCooksDao {
	public int add(CMSCooksData cmsCooksData, HttpServletResponse response);

	public void delete(CMSCooksData cmsCooksData, HttpServletResponse response);

	public CMSCooksData get(int id, HttpServletResponse response);

	public List<Object> get(int limit, int pageno);

	public int edit(CMSCooksData cmsCooksData, HttpServletResponse response);

	public List<CMSCooksData> get(String cookName, HttpServletResponse response);

	public void updateMenu(CMSCooksData cmsCooksData,
			HttpServletResponse response);

	public List<CMSCooksSpecialityData> getCooksSpeciality();

	public CMSCooksSpecialityData getCooksSpeciality(String speciality);
}
