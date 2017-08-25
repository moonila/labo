package org.moonila.common.user.gui.utils;

import org.moonila.common.spring.SpringApplicationContext;
import org.moonila.common.user.core.service.RoleGroupManager;
import org.moonila.common.user.core.service.RoleManager;
import org.moonila.common.user.core.service.UserManager;

public class UserRoleManagerFactory {

	/*******************************
	 * UserManager
	 *******************************/
	
	public static UserManager getUserManager() {
		return (UserManager) SpringApplicationContext.getContext().getBean("userManager");
	}
	
	/*******************************
	 * RoleManager
	 *******************************/
	
	public static RoleManager getRoleManager() {
		return (RoleManager) SpringApplicationContext.getContext().getBean("roleManager");
	}

	
	/*******************************
	 * RoleGroupManager
	 *******************************/
	
	public static RoleGroupManager getRoleGroupManager() {
		return (RoleGroupManager) SpringApplicationContext.getContext().getBean("roleGroupManager");
	}


}
