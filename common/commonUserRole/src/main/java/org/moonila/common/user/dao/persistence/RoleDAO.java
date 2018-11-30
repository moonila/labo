package org.moonila.common.user.dao.persistence;

import org.moonila.common.user.dao.bo.BaseRoleBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sandra Trino
 * 
 */

@Repository
public interface RoleDAO extends JpaRepository<BaseRoleBO, String> {

    public BaseRoleBO getRoleByName(String name);
}
