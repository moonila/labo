/**
 *
 * -------------------------------------------------------------------------
 * RoleGroupDAO.java
 * -------------------------------------------------------------------------
 */

package org.moonila.common.user.dao.persistence;

import org.moonila.common.user.dao.bo.BaseRoleGroupBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author strino 
 * 
 */

@Repository
public interface RoleGroupDAO extends JpaRepository<BaseRoleGroupBO, String> {

    BaseRoleGroupBO getRoleGroupByName(String name);
}
