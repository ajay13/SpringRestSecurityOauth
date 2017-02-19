package com.beingjavaguys.services.cmscooks;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.bean.cmscooks.CMSCooksBean;
import com.beingjavaguys.bean.generic.BeanList;

public interface CMSCooksService {
	void add(CMSCooksBean cmsCooksBean, HttpServletResponse response);

	void delete(int id, HttpServletResponse response);
	
	public BeanList get(int limit, int pageno);
	
	void  edit(CMSCooksBean cmsCooksBean, HttpServletResponse response);
	
	List<CMSCooksBean> get(String cookName, HttpServletResponse response);
}
