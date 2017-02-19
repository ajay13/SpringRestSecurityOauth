package com.beingjavaguys.utility.cmscooks;

import org.springframework.stereotype.Component;

import com.beingjavaguys.bean.cmscooks.CMSCooksBean;
import com.beingjavaguys.models.cmscooks.CMSCooksData;

@Component("cmsCooksUtility")
public class CMSCooksUtility {
	public CMSCooksData populateCMSCooks(CMSCooksBean cmsCooksBean) {
		CMSCooksData cmsCooksData = new CMSCooksData();

		cmsCooksData.setName(cmsCooksBean.getName());
		cmsCooksData.setDescription(cmsCooksBean.getDescription());
		cmsCooksData.setAddress(cmsCooksBean.getAddress());
		cmsCooksData.setGender(cmsCooksBean.getGender());
		cmsCooksData.setMobileno(cmsCooksBean.getMobileno());
		cmsCooksData.setSpecility(cmsCooksBean.getSpecility());
		cmsCooksData.setId(cmsCooksBean.getId());
		return cmsCooksData;
	}

	public CMSCooksBean populateCMSCooks(CMSCooksData cmsCooksData) {
		CMSCooksBean cmsCooksBean = new CMSCooksBean();

		cmsCooksBean.setName(cmsCooksData.getName());
		cmsCooksBean.setDescription(cmsCooksData.getDescription());
		cmsCooksBean.setAddress(cmsCooksData.getAddress());
		cmsCooksBean.setGender(cmsCooksData.getGender());
		cmsCooksBean.setMobileno(cmsCooksData.getMobileno());
		cmsCooksBean.setSpecility(cmsCooksData.getSpecility());
		cmsCooksBean.setId(cmsCooksData.getId());
		return cmsCooksBean;
	}
}
