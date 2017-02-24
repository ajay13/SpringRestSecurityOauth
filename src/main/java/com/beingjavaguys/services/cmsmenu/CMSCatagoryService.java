package com.beingjavaguys.services.cmsmenu;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.bean.cmscooks.CMSCooksBean;
import com.beingjavaguys.bean.cmsmenu.CMSCatagoryBean;
import com.beingjavaguys.bean.generic.BeanList;

public interface CMSCatagoryService {

	void add(CMSCatagoryBean cmsCatagoryBean, HttpServletResponse response);

	void delete(int id, HttpServletResponse response);

	public BeanList get(int limit, int pageno);

	void edit(CMSCatagoryBean cmsCatagoryBean, HttpServletResponse response);
	
	List<CMSCatagoryBean> get(String catagoryName, HttpServletResponse response);

	List<CMSCatagoryBean> get();
	
	void grouppingAndSeq(List<Integer> catagoryIds, String groupName);
}
