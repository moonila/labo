package org.moonila.common.user.core.utils;

import org.moonila.common.user.dao.persistence.RoleDAO;
import org.moonila.common.user.dao.persistence.RoleGroupDAO;
import org.moonila.common.user.dao.persistence.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class UserRoleDaoFactory {

	/*******************************
	 * RoleDAO
	 *******************************/
	@Autowired
	@Qualifier("roleDao")
	private RoleDAO roleDao;

	public RoleDAO getRoleDao() {
		return roleDao;
	}
	
	/*******************************
	 * UserDAO
	 *******************************/
	@Autowired
	@Qualifier("userDao")
	private UserDAO userDao;

	public UserDAO getUserDao() {
		return userDao;
	}
	
	/*******************************
	 * RoleGroupDAO
	 *******************************/
	@Autowired
	@Qualifier("roleGroupDao")
	private RoleGroupDAO roleGroupDao;

	public RoleGroupDAO getRoleGroupDao() {
		return roleGroupDao;
	}

}
