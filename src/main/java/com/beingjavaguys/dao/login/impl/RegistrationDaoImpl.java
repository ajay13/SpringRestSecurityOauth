package com.beingjavaguys.dao.login.impl;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beingjavaguys.dao.core.CoreDao;
import com.beingjavaguys.dao.login.RegistrationDao;
import com.beingjavaguys.models.user.CustomUserData;
import com.beingjavaguys.models.user.RoleData;

@Repository("registrationDao")
public class RegistrationDaoImpl implements RegistrationDao {

	@Autowired
	CoreDao coreDao;

	@Override
	public void userRegistration(CustomUserData customUserData,HttpServletResponse response) {
		
		RoleData roleData = null;
		
		String getUser = "select count(U) from CustomUserData U where U.username=:userName";
		String getRole = "select R from RoleData R where R.roleName=:roleName";
		
		Session session = null;
		Query query = null;
		try {
			session = coreDao.getSession();
			session.beginTransaction();
			query = session.createQuery(getUser);
			query.setParameter("userName", customUserData.getUsername());
			
			Long count = (Long) query.uniqueResult();
			
			if(count==0){
				query = session.createQuery(getRole);
				query.setParameter("roleName","ROLE_APP");
				roleData = (RoleData) query.uniqueResult();
				customUserData.setRoleData(roleData);
				session.saveOrUpdate(customUserData);
				session.getTransaction().commit();
				response.setStatus(200);//for OK
			}else{
				response.setStatus(402);//for already exists
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
