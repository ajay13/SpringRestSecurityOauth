package com.beingjavaguys.dao.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beingjavaguys.bean.user.CustomUser;
import com.beingjavaguys.bean.user.Role;
import com.beingjavaguys.dao.core.CoreDao;
import com.beingjavaguys.models.user.CustomUserData;
import com.beingjavaguys.models.user.RoleData;

@Repository("UserDAOImpl")
public class UserDAOImpl {

	@Autowired
	CoreDao coreDao;

	public CustomUser loadUserByUsername(final String username) {

		CustomUser user = new CustomUser();
		
		CustomUserData customUserData = null;
		RoleData roleData = null;
		Session session = null;
		String getSMSData = " select U from CustomUserData U where U.username=:username";
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getSMSData);
			query.setParameter("username", username);
			customUserData = (CustomUserData) query.uniqueResult();
			
			user.setFirstName(customUserData.getFirstName());
			user.setLastName(customUserData.getLastName());
			user.setUsername(customUserData.getUsername());
			user.setPassword(customUserData.getPassword());
			roleData = customUserData.getRoleData();
			
			Role r = new Role();
			
			r.setName(roleData.getRoleName());
			List<Role> roles = new ArrayList<Role>();
			roles.add(r);
			user.setAuthorities(roles);
		} catch (HibernateException e) {
		} finally {
			session.close();
		}

		return user;
	}

}