package org.moonila.common.user.core.to;

import java.util.List;

import org.moonila.common.crud.BaseCrudTO;

/**
 * 
 * @author Sandra Trino
 * 
 */

public class BaseRoleGroupTO extends BaseCrudTO {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8817662686687650925L;

	private List<BaseRoleTO> listRoles;

	private List<BaseUserTO> listUser;

	public BaseRoleGroupTO() {
		super();
	}


	public List<BaseRoleTO> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<BaseRoleTO> listRoles) {
		this.listRoles = listRoles;
	}

	public List<BaseUserTO> getListUser() {
		return listUser;
	}

	public void setListUser(List<BaseUserTO> listUser) {
		this.listUser = listUser;
	}
}
