package org.moonila.common.user.core.to;

import java.util.List;

import org.moonila.common.crud.BaseCrudTO;

/**
 * 
 * @author Sandra Trino
 * 
 */
public class BaseUserTO extends BaseCrudTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1568533817835115042L;

	private String password;

	private String login;

	private List<BaseRoleGroupTO> listRoleGroup;

	public BaseUserTO() {
		super();
	}

	public BaseUserTO(String id, String password, String login) {
		super();
		this.id = id;
		this.password = password;
		this.login = login;
	}
	
	public BaseUserTO(String password, String login) {
		super();
		this.password = password;
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<BaseRoleGroupTO> getListRoleGroup() {
		return listRoleGroup;
	}

	public void setListRoleGroup(List<BaseRoleGroupTO> listRoleGroup) {
		this.listRoleGroup = listRoleGroup;
	}
	
}
