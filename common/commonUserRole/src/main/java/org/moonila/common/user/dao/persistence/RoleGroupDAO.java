/**
 *
 * -------------------------------------------------------------------------
 * RoleGroupDAO.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.dao.persistence;

import org.moonila.common.dao.persistence.GenericInterfaceDao;
import org.moonila.common.user.dao.bo.BaseRoleGroupBO;

/**
 * @author strino 
 * 
 */

public interface RoleGroupDAO extends GenericInterfaceDao<BaseRoleGroupBO, String> {
    public BaseRoleGroupBO getRoleGroupByName(String name);
}
