package com.beingjavaguys.dao.login;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.models.user.CustomUserData;

public interface RegistrationDao {
	public void userRegistration(CustomUserData customUser,HttpServletResponse response);
}
