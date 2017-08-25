package org.moonila.common.user.core.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.moonila.common.crud.CrudException;
import org.moonila.common.user.core.to.BaseRoleTO;
import org.moonila.common.user.core.utils.RoleGroupException;
import org.moonila.common.user.core.utils.RolesAndUsersTransfertObjectImpl;
import org.moonila.common.user.core.utils.UserRoleDaoFactory;
import org.moonila.common.user.dao.bo.BaseRoleBO;
import org.moonila.common.utils.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 
 * @author Sandra Trino
 * 
 */
public class RoleManagerImpl extends UserRoleDaoFactory implements RoleManager {

	// private final Logger logger = Logger.getLogger(this.getClass());
	private final static Logger logger = LoggerFactory
			.getLogger(RoleManagerImpl.class);

	private Resource rolesResource;

	public void nonTransactionalInit() throws IOException, RoleGroupException {
		logger.debug("###### Initialize RoleManager bean");
		this.init();
		logger.debug("###### RoleManager bean initialized");
	}

	public void init() throws IOException {
		// Retrieve role list
		Properties rolesProps = PropertiesLoaderUtils
				.loadProperties(rolesResource);
		Object[] roles = rolesProps.keySet().toArray(new Object[0]);

		// Register roles in database
		// for (Object enumRole : roles) {
		// logger.debug("Create role : " + (String) enumRole);
		// createRole((String) enumRole);
		// }
	}

	public void setRolesResource(Resource resource) {
		this.rolesResource = resource;
	}

	public List<BaseRoleTO> createOrUpdateObjects(
			List<BaseRoleTO> objectsToBeInserted) throws CrudException {
		for (BaseRoleTO roleToBeInserted : objectsToBeInserted) {
			createOrUpdateSingleObject(roleToBeInserted);
		}
		return getAllObject();
	}

	public String createOrUpdateSingleObject(BaseRoleTO objectToBeInserted)
			throws CrudException {

		validateRoleBeforSaveOrUpdate(objectToBeInserted);
		BaseRoleBO role = RolesAndUsersTransfertObjectImpl
				.toRoleBO(objectToBeInserted);

		return getRoleDao().saveOrUpdate(role).getId();
	}

	public List<BaseRoleTO> deleteObjectsByIds(List<String> idsList)
			throws CrudException {
		for (String roleId : idsList) {
			deleteSingleObject(roleId);
		}
		return getAllObject();
	}

	public void deleteSingleObject(String id) throws CrudException {
		if (StringHelper.isNullOrEmpty(id)) {
			throw new CrudException("Role id must be specified");
		}
		BaseRoleBO role = getRoleDao().get(id);
		if (role == null) {
			throw new CrudException("Can not find and remove role in database ");
		}

		// remove role by its id
		getRoleDao().remove(role);

	}

	public List<BaseRoleTO> getAllObject() {

		List<BaseRoleTO> listRoleTO = new ArrayList<BaseRoleTO>();

		// retrieve all roles
		List<BaseRoleBO> listRole = getRoleDao().getAll();

		for (BaseRoleBO role : listRole) {
			BaseRoleTO roleTO = RolesAndUsersTransfertObjectImpl.toRoleTO(role);
			listRoleTO.add(roleTO);
		}
		return listRoleTO;
	}

	public BaseRoleTO getObject(String id) throws CrudException {
		if (StringHelper.isNullOrEmpty(id)) {
			throw new CrudException("Role id must be specified");
		}
		BaseRoleBO role = getRoleDao().get(id);
		BaseRoleTO roleTO;
		
		if(role !=null){
			//transform BaseRoleBO to BaseRoleTO
			roleTO = RolesAndUsersTransfertObjectImpl.toRoleTO(role);
		}else {
			throw new CrudException("Can not find role in database ");
		}
		return roleTO;
	}

	private void validateRoleBeforSaveOrUpdate(BaseRoleTO roleTO)
			throws CrudException {
		String nameRoleTO = roleTO.getName();
		if (StringHelper.isNullOrEmpty(nameRoleTO)) {
			throw new CrudException("BaseRoleBO Name must be speficied.");
		}
		BaseRoleBO role = getRoleDao().getRoleByName(nameRoleTO);
		if (role != null) {
			throw new CrudException("A role with that name already exists : "
					+ nameRoleTO);
		}

	}
}
