package org.moonila.common.user.dao.bo;

import javax.persistence.Entity;
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
@Table(name = "BASE_ROLE")
public class BaseRoleBO extends BaseCrudBO {

	/**
     * 
     */
	private static final long serialVersionUID = 1873275931471992209L;

	public BaseRoleBO() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof BaseRoleBO))
			return false;
		BaseRoleBO castOther = (BaseRoleBO) other;
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
