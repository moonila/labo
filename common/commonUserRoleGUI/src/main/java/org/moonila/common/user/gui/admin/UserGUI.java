package org.moonila.common.user.gui.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.moonila.common.crud.CrudException;
import org.moonila.common.user.core.service.UserManager;
import org.moonila.common.user.core.to.BaseRoleTO;
import org.moonila.common.user.core.to.BaseUserTO;
import org.moonila.common.user.gui.utils.UserRoleManagerFactory;
import org.moonila.common.utils.UtilsConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SessionScoped
public class UserGUI implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 8041652024118618550L;

	private final static Logger logger = LoggerFactory.getLogger(UserGUI.class);

	private List<BaseUserTO> allUserList;
	private List<BaseRoleTO> allRoleList;
	private String newUserName;
	private String newUserPassword;
	private String newUserLogin;
	private String roleId;

	private UserManager userManager = UserRoleManagerFactory.getUserManager();

	public UserGUI() {

	}

	@PostConstruct
	public void init() {
		allUserList = userManager.getAllObject();
		resetValues();
	}

	public void resetValues() {
		newUserName = null;
		newUserPassword = null;
		newUserLogin = null;
	}

	public String createOrUpdateUser() {

		BaseUserTO newUserToBeInserted = new BaseUserTO(newUserLogin,
				newUserPassword);
		newUserToBeInserted.setName(newUserName);
		try {
			userManager.createOrUpdateSingleObject(newUserToBeInserted);
			allUserList = userManager.getAllObject();
			resetValues();
			return UtilsConstant.SUCCESS_RETURN;
		} catch (CrudException e) {
			logger.error(e.getMessage());
			return UtilsConstant.ERROR_RETURN;
		}
	}

	public String removeUsers() {
		List<String> idsList = new ArrayList<String>();

		for (BaseUserTO userToBeDeleted : allUserList) {
			if (userToBeDeleted.isSelected()) {
				idsList.add(userToBeDeleted.getId());
			}
		}
		try {
			userManager.deleteObjectsByIds(idsList);
			allUserList = userManager.getAllObject();
			return UtilsConstant.SUCCESS_RETURN;
		} catch (CrudException e) {
			logger.error(e.getMessage());
			return UtilsConstant.ERROR_RETURN;
		}

	}

	public List<BaseUserTO> getAllUserList() {
		return allUserList;
	}

	public void setAllUserList(List<BaseUserTO> allUserList) {

		this.allUserList = allUserList;
	}

	public List<BaseRoleTO> getAllRoleList() {
		return allRoleList;
	}

	public void setAllRoleList(List<BaseRoleTO> allRoleList) {
		this.allRoleList = allRoleList;
	}


	public String getNewUserName() {
		return newUserName;
	}

	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}

	public String getNewUserLogin() {
		return newUserLogin;
	}

	public void setNewUserLogin(String newUserLogin) {
		this.newUserLogin = newUserLogin;
	}

	public String getNewUserPassword() {
		return newUserPassword;
	}

	public void setNewUserPassword(String newUserPassword) {
		this.newUserPassword = newUserPassword;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
