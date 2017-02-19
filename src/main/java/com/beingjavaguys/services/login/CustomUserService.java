package com.beingjavaguys.services.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.beingjavaguys.bean.user.CustomUser;
import com.beingjavaguys.dao.user.UserDAOImpl;

@Service("userService")
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserDAOImpl UserDAOImpl;

	public CustomUser loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return UserDAOImpl.loadUserByUsername(username);
	}

}