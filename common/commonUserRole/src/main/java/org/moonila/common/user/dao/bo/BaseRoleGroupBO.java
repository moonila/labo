package org.moonila.common.user.dao.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.moonila.common.dao.bo.BaseCrudBO;

/**
 * 
 * @author Sandra Trino
 * 
 */
@Entity
@Table(name = "BASE_ROLEGROUP")
public class BaseRoleGroupBO extends BaseCrudBO {
	private static final long serialVersionUID = 9115922129178651641L;

	private List<BaseRoleBO> listRoles;

	private List<BaseUserBO> listUser;

	public BaseRoleGroupBO() {
		super();
		this.listUser = new ArrayList<BaseUserBO>();
	}

	@ManyToMany(fetch = FetchType.LAZY)
	public List<BaseRoleBO> getListRoles() {
		return listRoles;
	}

	public void setListRoles(List<BaseRoleBO> listRoles) {
		this.listRoles = listRoles;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "listRoleGroup")
	public List<BaseUserBO> getListUser() {
		return listUser;
	}

	public void setListUser(List<BaseUserBO> listUser) {
		this.listUser = listUser;
	}

	public void addRole(BaseRoleBO role) {
		this.getListRoles().add(role);
	}

	public void removeRole(BaseRoleBO role) {
		this.getListRoles().remove(role);
	}

	public void addUser(BaseUserBO user) {
		this.getListUser().add(user);
		user.getListRoleGroup().add(this);
	}

	public void removeUser(BaseUserBO user) {
		this.getListUser().remove(user);
		user.getListRoleGroup().remove(this);
	}

	public void clearUserList() {
		List<BaseUserBO> listUser = new ArrayList<BaseUserBO>();
		listUser.addAll(getListUser());
		for (BaseUserBO user : listUser) {
			removeUser(user);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BaseRoleGroupBO))
			return false;
		BaseRoleGroupBO castOther = (BaseRoleGroupBO) other;
		return new EqualsBuilder().append(name, castOther.name).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(name).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", this.name).toString();
	}
}
