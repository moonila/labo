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
 * RoleGroupManager.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.core.service;

/**
 * A manager of Role Group. Provides methods to create, retrieve, delete
 * or update role Group and to manage Role related to role group. 
 * Method arguments must be non null, unless the
 * contrary is explicitly specified.
 * 
 * @author Sandra Trino
 * 
 */

import java.io.IOException;
import java.util.List;

import org.moonila.common.crud.ICrudManager;
import org.moonila.common.user.core.to.BaseRoleGroupTO;
import org.moonila.common.user.core.utils.RoleGroupException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleGroupManager extends ICrudManager<BaseRoleGroupTO> {

    /**
     * Create default role groups if they don't exist in database
     * 
     */
    public void init() throws IOException, RoleGroupException;




    /**
     * Return the list of role group associate with user
     * 
     * @param idUser
     *            an {@link BaseUserTO} id
     * @return the {@link List} of role group associate with {@link user} ,
     *         couldn't be null, can be empty
     */
    @Transactional(readOnly = true)
    public List<BaseRoleGroupTO> getRoleGroupNotInUser(String idUser) throws RoleGroupException;

    /**
     * Add the user matching the given idUser to the roleGroup matching the
     * given idRoleGroup
     * 
     * @param idUser
     *            an {@link BaseUserTO} id
     * @param idRoleGroup
     *            a {@link RoleGroup} id
     * @throws RoleGroupException
     *             if at least one of the given id doesn't exist in database
     */
    public void addUser(String idUser, String idRoleGroup) throws RoleGroupException;

    /**
     * Remove the user matching the given idUser from the list of users of the
     * roleGroup matching the given idRoleGroup. The user isn't removed from the
     * registry and can be re-affected to any roles
     * 
     * @param idUser
     *            an {@link BaseUserTO} id
     * @param idRoleGroup
     *            a {@link RoleGroup} id
     * @throws RoleGroupException
     *             if no organization found for the given id
     */
    public void removeUser(String idUser, String idRoleGroup) throws RoleGroupException;
}
