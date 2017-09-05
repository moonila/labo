/**
 * License
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
 * RoleManager.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.core.service;

/**
 * A manager of Role . Provides methods to retrieve role. 
 * Method arguments must be non null, unless the
 * contrary is explicitly specified.
 * 
 * @author Sandra Trino
 * 
 */

import java.io.IOException;

import org.moonila.common.crud.ICrudManager;
import org.moonila.common.user.core.to.BaseRoleTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleManager extends ICrudManager<BaseRoleTO>{

    /**
     * Add all roles in Registry, if not exist
     * 
     */
    public void init() throws IOException;

}
