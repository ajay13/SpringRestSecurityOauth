package com.beingjavaguys.utility.other;

import org.springframework.stereotype.Component;

import com.beingjavaguys.bean.other.CastBean;
import com.beingjavaguys.models.other.CastData;

@Component("otherUtility")
public class OtherUtility {
	public CastBean populateCastBean(CastData castData) {
		CastBean castBean = new CastBean();
		castBean.setId(castData.getId());
		castBean.setCast(castData.getCast());
		return castBean;
	}
	
	public CastData populateCastData(CastBean castBean) {
		CastData castData = new CastData();
		castData.setId(castBean.getId());
		castData.setCast(castBean.getCast());
		return castData;
	}
}
