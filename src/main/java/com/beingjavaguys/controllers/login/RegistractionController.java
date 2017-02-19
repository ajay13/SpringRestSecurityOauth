package com.beingjavaguys.controllers.login;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beingjavaguys.bean.user.CustomUser;
import com.beingjavaguys.services.login.RegistrationService;

@Controller
@RequestMapping("/registration")
public class RegistractionController {

	@Autowired
	RegistrationService registrationService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody
	void userRegistration(HttpServletResponse response,
			@RequestBody CustomUser customUser) {
		try {
			registrationService.userRegistration(customUser,response);
		} catch (Exception e) {
			response.setStatus(400);
		}
	}
	
}
