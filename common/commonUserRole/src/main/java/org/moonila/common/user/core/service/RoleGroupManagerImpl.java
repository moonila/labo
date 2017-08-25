/**
 * Web commons : user service.
 * Copyright (c) 2010 EBM Websourcing, http://www.ebmwebsourcing.com/
 *
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
 * RoleGroupManagerImpl.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.core.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.moonila.common.crud.CrudException;
import org.moonila.common.user.core.to.BaseRoleGroupTO;
import org.moonila.common.user.core.utils.RoleGroupException;
import org.moonila.common.user.core.utils.RolesAndUsersTransfertObjectImpl;
import org.moonila.common.user.core.utils.UserRoleDaoFactory;
import org.moonila.common.user.dao.bo.BaseRoleBO;
import org.moonila.common.user.dao.bo.BaseRoleGroupBO;
import org.moonila.common.user.dao.bo.BaseUserBO;
import org.moonila.common.utils.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;




/**
 * @author strino - eBM WebSourcing
 * 
 */
public class RoleGroupManagerImpl extends UserRoleDaoFactory implements RoleGroupManager {

//    private final Logger logger = Logger.getLogger(this.getClass());
	private final static Logger logger = LoggerFactory
			.getLogger(RoleGroupManager.class);


    private Resource roleGroupsResource;

    private RoleGroupManager roleGroupManager;

    public void nonTransactionalInit() throws IOException, RoleGroupException {
        logger.debug("###### Initialize RoleGroupManager bean");
        roleGroupManager.init();
        logger.debug("###### RoleGroupManager bean initialized");
    }

    public void setRoleGroupManager(RoleGroupManager roleGroupManager) {
        this.roleGroupManager = roleGroupManager;
    }

    public void setRoleGroupsResource(Resource roleGroupsResource) {
        this.roleGroupsResource = roleGroupsResource;
    }

    // create the roles group admin if not exists in database
    public void init() throws IOException, RoleGroupException {

        // Create role group to role map
        Properties rolesGroupProps = PropertiesLoaderUtils.loadProperties(roleGroupsResource);
        Map<String, List<String>> roleGroupsMap = this
                .createRoleGroupToRoleListMap(rolesGroupProps);

        // retrieve if the role group already exist
        for (String roleGroup : roleGroupsMap.keySet()) {
            logger.debug("Create Role group : " + roleGroup);
            createRoleGroup(roleGroup, roleGroupsMap.get(roleGroup));
        }

    }

    public  List<BaseRoleGroupTO> createOrUpdateObjects(List<BaseRoleGroupTO> objectsToBeInserted) throws CrudException {
    	for(BaseRoleGroupTO roleGroupToBeInserted : objectsToBeInserted){
    		createOrUpdateSingleObject(roleGroupToBeInserted);
    	}
    	
    	return getAllObject();
    }

    public String createOrUpdateSingleObject(BaseRoleGroupTO roleGroupTO) throws CrudException {

        this.validateBeforeSaveOrUpdate(roleGroupTO);

        BaseRoleGroupBO roleGroup = new BaseRoleGroupBO();
        RolesAndUsersTransfertObjectImpl.toRoleGroupBO(roleGroupTO, roleGroup);

        List<BaseRoleBO> listR = new ArrayList<BaseRoleBO>();
        List<BaseRoleBO> listRole = roleGroup.getListRoles();

        for (BaseRoleBO ro : listRole) {

            // retrieve the id role
            ro = getRoleDao().getRoleByName(ro.getName());
            listR.add(ro);
        }

        roleGroup.setListRoles(listR);

        // create role group
        return getRoleGroupDao().saveOrUpdate(roleGroup).getId();

    }

    public List<BaseRoleGroupTO> getAllObject() {

        // retrieve all role group
        List<BaseRoleGroupBO> listAllGroup = getRoleGroupDao().getAll();

        // transform role groups
        List<BaseRoleGroupTO> listRole = RolesAndUsersTransfertObjectImpl.toAllGroups(listAllGroup);

        return listRole;
    }

    public List<BaseRoleGroupTO> getRoleGroupNotInUser(String idUser) throws RoleGroupException {

        List<BaseRoleGroupTO> listRoleGroupTO = new ArrayList<BaseRoleGroupTO>();

        // retrieve user with its id
        BaseUserBO user = getUserDao().get(idUser);

        List<BaseRoleGroupBO> listRoleGroupInUser = new ArrayList<BaseRoleGroupBO>();

        if (user != null) {
            listRoleGroupInUser = user.getListRoleGroup();
        } else {
            throw new RoleGroupException("A user with that name doesn't exist ! ");
        }

        // retrieve all role groups
        List<BaseRoleGroupBO> allRoleGroup = getRoleGroupDao().getAll();

        if (allRoleGroup != null) {

            if (!listRoleGroupInUser.isEmpty()) {

                // remove user role's groups from list all role groups
                allRoleGroup.removeAll(listRoleGroupInUser);

            }
            for (BaseRoleGroupBO rG : allRoleGroup) {

                // transform role groups
                listRoleGroupTO.add(RolesAndUsersTransfertObjectImpl.toRoleGroupTO(rG));
            }
        } else {
            throw new RoleGroupException("no role groups in database ! ");
        }

        return listRoleGroupTO;
    }

    public BaseRoleGroupTO getObject(String id) throws CrudException {
    	
        // retrieve role group
        BaseRoleGroupBO roleGroup = getRoleGroupDao().get(id);

        if (roleGroup == null) {
            throw new CrudException("no role groups whith this id in database ! ");
        }

        // transform role group
        BaseRoleGroupTO role = RolesAndUsersTransfertObjectImpl.toRoleGroupTO(roleGroup);

        return role;
    }
    
    public List<BaseRoleGroupTO> deleteObjectsByIds( List<String> idsList) throws CrudException {
    	
    	for(String roleGroupID : idsList){
    		deleteSingleObject(roleGroupID);
    	}
    	
    	return getAllObject();
    }

    public void deleteSingleObject( String id) throws CrudException {
        // retrieve role group by its id
        BaseRoleGroupBO roleGroup = getRoleGroupDao().get(id);

        if (roleGroup == null) {
            throw new CrudException("no role groups whith this id in database ! ");
        }
        
        roleGroup.clearUserList();
        getRoleGroupDao().saveOrUpdate(roleGroup);
        
        // remove role group
        getRoleGroupDao().remove(roleGroup);
    }

     public void removeUser(String idUser,String idRoleGroup) throws RoleGroupException {
        // retrieve roleGroup by its id
        BaseRoleGroupBO roleGroup = getRoleGroupDao().get(idRoleGroup);
        if (roleGroup == null) {
            throw new RoleGroupException(
                    "You are trying to remove a user to a non existing role whit id : "
                            + idRoleGroup);
        }

        // retrieve user by its id
        BaseUserBO user = getUserDao().get(idUser);
        if (user == null) {
            throw new RoleGroupException(
                    "You are trying to remove a non existing user to a role whith id : "
                            + idRoleGroup);
        }

        roleGroup.removeUser(user);

        getRoleGroupDao().saveOrUpdate(roleGroup);
    }

    public void addUser(String idUser, String idRoleGroup) throws RoleGroupException {

        // retrieve role group with its id
        BaseRoleGroupBO roleGroup = getRoleGroupDao().get(idRoleGroup);

        if (roleGroup == null) {
            throw new RoleGroupException(
                    "You are trying to add a user to a non existing role whit id : " + idRoleGroup);
        }

        // retrieve user with its id
        BaseUserBO user = getUserDao().get(idUser);

        if (user == null) {
            throw new RoleGroupException(
                    "You are trying to add a non existing user to a role whith id : " + idRoleGroup);
        }
        // insert the new user in the RoleGroup list<User>
        roleGroup.addUser(user);

        // save role Group
        getRoleGroupDao().saveOrUpdate(roleGroup);

    }

    private void validateBeforeSaveOrUpdate(BaseRoleGroupTO roleGroup)
            throws CrudException {
    	if(roleGroup == null){
    		 throw new CrudException("A role must be speficied.");
    	}
        String roleGroupNameTO = roleGroup.getName();
        if (StringHelper.isNullOrEmpty(roleGroupNameTO)) {
            throw new CrudException("Role Name must be speficied.");
        }
            BaseRoleGroupBO group = getRoleGroupDao().getRoleGroupByName(roleGroupNameTO);
            if (group != null) {
                throw new CrudException("A role with that name already exists : "
                        + roleGroupNameTO);
            }       
    }

    private void createRoleGroup(String groupName, List<String> roleNames)
            throws RoleGroupException {
        BaseRoleGroupBO roleGroupBO = getRoleGroupDao().getRoleGroupByName(groupName);

        if (roleGroupBO == null) {
        	BaseRoleGroupBO roleGroup = new BaseRoleGroupBO();
            roleGroup.setName(groupName);

            // retrieve role related to role group
            List<BaseRoleBO> listRoles = this.retrieveRole(roleNames);
            roleGroup.setListRoles(listRoles);

            // save the role group in database
            getRoleGroupDao().saveOrUpdate(roleGroup);
        }
    }

    private List<BaseRoleBO> retrieveRole(List<String> roleNames) throws RoleGroupException {
        List<BaseRoleBO> roles = new ArrayList<BaseRoleBO>();
        for (String string : roleNames) {
        	BaseRoleBO role = getRoleDao().getRoleByName(string);
            if (role != null) {
                roles.add(role);
            } else {
                throw new RoleGroupException("Can't create role group because role '" + string
                        + "' doesn't exist");
            }
        }
        return roles;
    }

    private Map<String, List<String>> createRoleGroupToRoleListMap(Properties rolesGroupProps) {
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        for (Object roleGroupProp : rolesGroupProps.keySet()) {
            List<String> roleList = new ArrayList<String>();
            String roles = rolesGroupProps.getProperty((String) roleGroupProp);
            StringTokenizer tokenizer = new StringTokenizer(roles, ",");
            while (tokenizer.hasMoreElements()) {
                String roleName = tokenizer.nextToken();
                roleList.add(roleName.trim());
            }
            result.put((String) roleGroupProp, roleList);
        }
        return result;
    }
}
