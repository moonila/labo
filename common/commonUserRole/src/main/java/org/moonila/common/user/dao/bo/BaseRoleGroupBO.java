package org.moonila.common.user.dao.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Sandra Trino
 * 
 */
@Entity (name = "BASE_ROLEGROUP")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRoleGroupBO  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<BaseRoleBO> listRoles;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "listRoleGroup")
	private List<BaseUserBO> listUser;

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

}
