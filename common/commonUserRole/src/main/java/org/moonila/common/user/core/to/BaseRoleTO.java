package org.moonila.common.user.core.to;

import org.moonila.common.crud.BaseCrudTO;

/**
 * 
 * @author Sandra Trino
 * 
 */

public class BaseRoleTO extends BaseCrudTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1936482092854382171L;

	/**
     * 
     */

	public BaseRoleTO() {

	}

	public BaseRoleTO(String id, String roleName) {
		this.name = roleName;
		this.id = id;
	}

}
