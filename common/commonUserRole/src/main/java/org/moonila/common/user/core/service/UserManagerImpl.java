/**
 * Licence
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * -------------------------------------------------------------------------
 * UserManagerImpl.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.core.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.moonila.common.crud.CrudException;
import org.moonila.common.user.core.to.BaseUserTO;
import org.moonila.common.user.core.utils.RoleGroupException;
import org.moonila.common.user.core.utils.RolesAndUsersTransfertObjectImpl;
import org.moonila.common.user.core.utils.UserException;
import org.moonila.common.user.core.utils.UserRoleDaoFactory;
import org.moonila.common.user.dao.bo.BaseRoleGroupBO;
import org.moonila.common.user.dao.bo.BaseUserBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

/**
 * @author Sandra Trino
 * 
 */
@Service("userManager")
public class UserManagerImpl extends UserRoleDaoFactory implements UserManager {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7429013639045300868L;

	private final Logger logger = LoggerFactory
			.getLogger(UserManagerImpl.class);

	private Resource usersResource;

	private Resource passwordsResource;

	public void nonTransactionalInit() throws IOException, RoleGroupException {
		logger.debug("###### Initialize usermanagerbean");
		this.init();
		logger.debug("######  usermanagerbean Initialized");
	}

	// create the user master if not exists in database
	public void init() throws IOException, RoleGroupException {

		// Create user to role group map
		Properties usersProps = PropertiesLoaderUtils
				.loadProperties(usersResource);
		Map<String, List<String>> usersMap = this
				.createUserToRoleGroupMap(usersProps);

		// Create user to password map
		Properties passwordsProps = PropertiesLoaderUtils
				.loadProperties(passwordsResource);

		// retrieve if the user already exist
		for (String userName : usersMap.keySet()) {
			logger.debug("Create user : " + userName);
			createUser(userName, usersMap.get(userName),
					passwordsProps.getProperty(userName));
		}

	}

	public List<BaseUserTO> createOrUpdateObjects(
			List<BaseUserTO> usersToBeInserted) throws CrudException {
		for (BaseUserTO userToBeInserted : usersToBeInserted) {
			createOrUpdateSingleObject(userToBeInserted);
		}
		return getAllObject();
	}

	public String createOrUpdateSingleObject(BaseUserTO userTO)
			throws CrudException {

		this.validateUserBeforSaveOrUpdate(userTO);

		BaseUserBO user = new BaseUserBO();
		RolesAndUsersTransfertObjectImpl.toUserBO(userTO, user);

		return getUserDao().save(user).getId();

	}

	public List<BaseUserTO> getAllObject() {

		List<BaseUserTO> listUserTO = new ArrayList<BaseUserTO>();

		// retrieve all users
		List<BaseUserBO> listUser = getUserDao().findAll();

		for (BaseUserBO us : listUser) {

			// transform user to BaseUserTO
			BaseUserTO userTO = RolesAndUsersTransfertObjectImpl.toUserTO(us);

			listUserTO.add(userTO);
		}

		return listUserTO;
	}

	public List<BaseUserTO> getUserNotInRoleGroup(String idRoleGroup)
			throws UserException {

		List<BaseUserTO> listUserTO = new ArrayList<BaseUserTO>();

		// retrieve role group with its id
		Optional<BaseRoleGroupBO> roleGroup = getRoleGroupDao().findById(idRoleGroup);

		List<BaseUserBO> listUserInRole;

		if (roleGroup != null) {
			listUserInRole = roleGroup.get().getListUser();
		} else {
			throw new UserException("A role with that name not exists ! ");

		}

		// retrieve all users
		List<BaseUserBO> allUser = getUserDao().findAll();

		if (allUser != null) {
			if (!listUserInRole.isEmpty()) {

				// remove user from list all users
				allUser.removeAll(listUserInRole);
			}

			for (BaseUserBO us : allUser) {

				// transform users
				listUserTO.add(RolesAndUsersTransfertObjectImpl.toUserTO(us));
			}
		}

		return listUserTO;
	}

	public BaseUserTO getObject(String userId) throws CrudException {

		// retrieve user with its id
		Optional<BaseUserBO> user = getUserDao().findById(userId);

		BaseUserTO userTO;

		if (user != null) {
			// transform BaseUserBO to BaseUserTO
			userTO = RolesAndUsersTransfertObjectImpl.toUserTO(user.get());
		} else {
			throw new CrudException("Can not find user in database ");
		}
		return userTO;
	}

	public BaseUserTO getUserByLogin(String login) throws UserException {

		// retrieve user with its name/login
		BaseUserBO user = getUserDao().getUserByLogin(login);
		BaseUserTO userTO;

		if (user != null) {

			// transform BaseUserBO to BaseUserTO
			userTO = RolesAndUsersTransfertObjectImpl.toUserTO(user);

		} else {
			throw new UserException("Authentication failed : user '" + login
					+ "' not found in database : ");
		}
		return userTO;
	}

	public List<BaseUserTO> deleteObjectsByIds(List<String> idsList)
			throws CrudException {
		for (String userId : idsList) {
			deleteSingleObject(userId);
		}
		return getAllObject();
	}

	public void deleteSingleObject(String userId) throws CrudException {
		Optional<BaseUserBO> user = getUserDao().findById(userId);

		if (user == null) {
			throw new CrudException("Can not find and remove user in database ");
		}
		// remove user by its id
		getUserDao().delete(user.get());
	}

	public void addRoleGroup(String idUser, String idRoleGroup)
			throws UserException {
		// retrieve user with its id
		Optional<BaseUserBO> user = getUserDao().findById(idUser);

		if (user == null) {
			throw new UserException(
					"You are trying to add a role to a non existing user with id: "
							+ idUser);
		}

		// retrieve role group with its id
		Optional<BaseRoleGroupBO> roleGroup = getRoleGroupDao().findById(idRoleGroup);

		if (roleGroup == null) {
			throw new UserException(
					"You are trying to add a non existing role to an user. Role id: "
							+ idRoleGroup);
		}

		// insert the new role group in the user's list<BaseRoleGroupBO>
		user.get().addRoleGroup(roleGroup.get());

		// saveOrUpdate user
		getUserDao().save(user.get());

	}

	public void removeRoleGroup(String idUser, String idRoleGroup)
			throws UserException {
		// retrieve user with its id
		Optional<BaseUserBO> user = getUserDao().findById(idUser);
		if (user == null) {
			throw new UserException(
					"You are trying to remove a role to a non existing user with id: "
							+ idUser);
		}

		// retrieve role group with its id
		Optional<BaseRoleGroupBO> roleGroup = getRoleGroupDao().findById(idRoleGroup);

		if (roleGroup == null) {
			throw new UserException(
					"You are trying to remove a non existing role to an user. Role id: "
							+ idRoleGroup);
		}

		// insert the role group in the user's list<BaseRoleGroupBO>
		user.get().removeRoleGroup(roleGroup.get());

		// saveOrUpdate user
		getUserDao().save(user.get());
	}

	private void validateUserBeforSaveOrUpdate(BaseUserTO userTO)
			throws CrudException {
		String nameUserTO = userTO.getName();
		if (StringUtils.isEmpty(nameUserTO)
				|| StringUtils.isEmpty(userTO.getPassword())) {
			throw new CrudException(
					"BaseUserBO Name and its password must be speficied.");
		}
		BaseUserBO user = getUserDao().getUserByLogin(nameUserTO);
		if (user != null) {
			throw new CrudException("A user with that name already exists : "
					+ nameUserTO);
		}

	}

	private Map<String, List<String>> createUserToRoleGroupMap(
			Properties usersProps) {
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		for (Object userProp : usersProps.keySet()) {
			List<String> roleGroupList = new ArrayList<String>();
			String roleGroups = usersProps.getProperty((String) userProp);
			StringTokenizer tokenizer = new StringTokenizer(roleGroups, ",");
			while (tokenizer.hasMoreElements()) {
				String roleGroupName = tokenizer.nextToken();
				roleGroupList.add(roleGroupName.trim());
			}
			result.put((String) userProp, roleGroupList);
		}
		return result;
	}

	private void createUser(String userName, List<String> roleGroupNames,
			String password) throws RoleGroupException {
		BaseUserBO userBO = getUserDao().getUserByLogin(userName);
		if (userBO == null) {

			BaseUserBO user = new BaseUserBO();
			user.setLogin(userName);
			user.setPassword(password);

			// retrieve the role group related to user
			List<BaseRoleGroupBO> listRoleGroup = this
					.retrieveRoleGroups(roleGroupNames);
			user.setListRoleGroup(listRoleGroup);

			// saveOrUpdate the user in database
			getUserDao().save(user);
		}
	}

	private List<BaseRoleGroupBO> retrieveRoleGroups(List<String> roleGroupNames)
			throws RoleGroupException {
		List<BaseRoleGroupBO> roleGroups = new ArrayList<BaseRoleGroupBO>();
		for (String string : roleGroupNames) {
			BaseRoleGroupBO roleGroup = getRoleGroupDao().getRoleGroupByName(
					string);
			if (roleGroup != null) {
				roleGroups.add(roleGroup);
			} else {
				throw new RoleGroupException(
						"Can't create user because role group '" + string
								+ "' doesn't exist");
			}
		}
		return roleGroups;
	}

	public void setUsersResource(Resource usersResource) {
		this.usersResource = usersResource;
	}

	public void setPasswordsResource(Resource passwordsResource) {
		this.passwordsResource = passwordsResource;
	}

}
