package com.beingjavaguys.services.other.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beingjavaguys.bean.other.CastBean;
import com.beingjavaguys.dao.other.OtherDao;
import com.beingjavaguys.models.other.CastData;
import com.beingjavaguys.services.other.OtherService;
import com.beingjavaguys.utility.other.OtherUtility;

@Service("otherService")
public class OtherServiceImpl implements OtherService{
	
	@Autowired
	OtherDao otherDao;
	
	@Autowired
	OtherUtility otherUtility;
	
	@Override
	public List<CastBean> getCast() {
		List<CastBean> castBeanList = new ArrayList<CastBean>();
		CastBean castBean = null;
		List<CastData> castDataList = otherDao.getCast();
		for(CastData castData : castDataList){
		    castBean = otherUtility.populateCastBean(castData);
		    castBeanList.add(castBean);
		}
		return castBeanList;
	}
}
