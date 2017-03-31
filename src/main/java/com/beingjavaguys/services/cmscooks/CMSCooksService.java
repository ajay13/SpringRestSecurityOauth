package com.beingjavaguys.services.cmscooks;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.bean.cmscooks.CMSCooksBean;
import com.beingjavaguys.bean.cmscooks.CMSCooksSpecialityBean;
import com.beingjavaguys.bean.generic.BeanList;

public interface CMSCooksService {
	int add(CMSCooksBean cmsCooksBean, HttpServletResponse response);

	void delete(int id, HttpServletResponse response);

	public BeanList get(int limit, int pageno,String url);

	int edit(CMSCooksBean cmsCooksBean, HttpServletResponse response);

	List<CMSCooksBean> get(String cookName, HttpServletResponse response);

	public String uploadImage(String imageName, int cookId,
			HttpServletResponse response);

	public List<CMSCooksSpecialityBean> getCooksSpeciality();
}
