package com.beingjavaguys.utility.user;

import org.springframework.stereotype.Component;

import com.beingjavaguys.bean.user.CustomUser;
import com.beingjavaguys.models.user.CustomUserData;

@Component("userUtility")
public class UserUtility {
	public CustomUserData populateUser(CustomUser customUser) {
		CustomUserData customUserData = new CustomUserData();

		customUserData.setFirstName(customUser.getFirstName());
		customUserData.setLastName(customUser.getLastName());
		customUserData.setMobileNo(customUser.getMobileNo());
		customUserData.setEmail(customUser.getEmail());
		customUserData.setUsername(customUser.getUsername());
		customUserData.setPassword(customUser.getPassword());

		return customUserData;
	}
}
