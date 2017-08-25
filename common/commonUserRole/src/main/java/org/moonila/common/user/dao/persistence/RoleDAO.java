package org.moonila.common.user.dao.persistence;

import org.moonila.common.dao.persistence.GenericInterfaceDao;
import org.moonila.common.user.dao.bo.BaseRoleBO;

/**
 * @author Sandra Trino
 * 
 */

public interface RoleDAO extends GenericInterfaceDao<BaseRoleBO, String> {

    public BaseRoleBO getRoleByName(String name);
}
