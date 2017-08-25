/**
 * Web commons : user service.
 * Copyright (c) 2008 EBM Websourcing, http://www.ebmwebsourcing.com/
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
 * UserManager.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.core.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.moonila.common.crud.ICrudManager;
import org.moonila.common.user.core.to.BaseUserTO;
import org.moonila.common.user.core.utils.RoleGroupException;
import org.moonila.common.user.core.utils.UserException;
import org.springframework.transaction.annotation.Transactional;



/**
 * A manager of User. Provides methods to create, retrieve, delete or update
 * User and to manage role group related to User. Method arguments must be non
 * null, unless the contrary is explicitly specified.
 * 
 * @author Sandra Trino
 * 
 */

@Transactional
public interface UserManager extends ICrudManager<BaseUserTO>, Serializable{

    /**
     * Create default users if they don't exist in database
     * 
     */
    public void init() throws IOException, RoleGroupException;


    /**
     * Return the list of users associate with role group
     * 
     * @param idRoleGroup
     *            an {@link RoleGroup} id
     * @return the {@link List} of {@link BaseUserTO} associate with
     *         {@link RoleGroup}, couldn't be null, can be empty
     */
    @Transactional(readOnly = true)
    public List<BaseUserTO> getUserNotInRoleGroup(String idRoleGroup) throws UserException;


    /**
     * Retrieve the user matching the given login
     * 
     * @param login
     *            an {@link BaseUserTO} login
     * @return the {@link BaseUserTO} handling data of the {@link BaseUserTO} that matches
     *         the given id
     * @throws UserException
     *             if no role group found for the given name
     */
    @Transactional(readOnly = true)
    public BaseUserTO getUserByLogin(String login) throws UserException;


    /**
     * Add the role group matching the given idRoleGroup to the user matching
     * the given idUser
     * 
     * @param idUser
     *            an {@link BaseUserTO} id
     * @param idRoleGroup
     *            a {@link RoleGroup} id
     * @throws UserException
     *             if at least one of the given id doesn't exist in database
     */
    public void addRoleGroup(String idUser, String idRoleGroup) throws UserException;

    /**
     * Remove the role group matching the given idRoleGroup from the list of
     * role groups of the user matching the given idUser. The role group isn't
     * removed from the registry and can be re-affected to any organization
     * 
     * @param idUser
     *            an {@link BaseUserTO} id
     * @param idRoleGroup
     *            a {@link RoleGroup} id
     * @throws UserException
     *             if no organization found for the given id
     */
    public void removeRoleGroup(String idUser, String idRoleGroup) throws UserException;

}
