package org.moonila.common.user.dao.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
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
@Table(name = "BASE_USER")
public class BaseUserBO extends BaseCrudBO {

	/**
     * 
     */
	private static final long serialVersionUID = -6979069718270125935L;

	private String password;

	private String login;

	private List<BaseRoleGroupBO> listRoleGroup;

	public BaseUserBO() {
		super();
		this.listRoleGroup = new ArrayList<BaseRoleGroupBO>();
	}

	@Column(unique = true, nullable = false)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	public List<BaseRoleGroupBO> getListRoleGroup() {
		return listRoleGroup;
	}

	public void setListRoleGroup(List<BaseRoleGroupBO> listRoleGroup) {
		this.listRoleGroup = listRoleGroup;
	}

	public void addRoleGroup(BaseRoleGroupBO roleGroup) {
		this.getListRoleGroup().add(roleGroup);
		roleGroup.getListUser().add(this);
	}

	public void removeRoleGroup(BaseRoleGroupBO roleGroup) {
		this.getListRoleGroup().remove(roleGroup);
		roleGroup.getListUser().remove(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BaseUserBO))
			return false;
		BaseUserBO castOther = (BaseUserBO) other;
		return new EqualsBuilder().append(login, castOther.login).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(login).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", this.login).toString();
	}
}
