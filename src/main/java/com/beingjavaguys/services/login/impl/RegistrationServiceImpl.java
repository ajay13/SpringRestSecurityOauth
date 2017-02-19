package com.beingjavaguys.services.login.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beingjavaguys.bean.user.CustomUser;
import com.beingjavaguys.dao.login.RegistrationDao;
import com.beingjavaguys.models.user.CustomUserData;
import com.beingjavaguys.services.login.RegistrationService;
import com.beingjavaguys.utility.user.UserUtility;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	RegistrationDao registrationDao;
	
	@Autowired
	UserUtility userUtility;
	
	@Override
	public void userRegistration(CustomUser customUser,HttpServletResponse response) {
		
		CustomUserData customUserData = null;
		
		customUserData = userUtility.populateUser(customUser);
		
		registrationDao.userRegistration(customUserData,response);
	}

}
