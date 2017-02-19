package com.beingjavaguys.services.login;

import javax.servlet.http.HttpServletResponse;

import com.beingjavaguys.bean.user.CustomUser;

public interface RegistrationService {
  void userRegistration(CustomUser customUser,HttpServletResponse response);
}
