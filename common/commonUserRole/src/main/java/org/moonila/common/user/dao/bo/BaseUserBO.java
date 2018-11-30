package org.moonila.common.user.dao.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;


/**
 * 
 * @author Sandra Trino
 * 
 */
@Entity(name = "BASE_USER")
@Getter
@Setter
@ToString(exclude = "listRoleGroup")
@EqualsAndHashCode(exclude = "listRoleGroup")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	private String description;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	private String login;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<BaseRoleGroupBO> listRoleGroup;


	public void addRoleGroup(BaseRoleGroupBO roleGroup) {
		this.getListRoleGroup().add(roleGroup);
		roleGroup.getListUser().add(this);
	}

	public void removeRoleGroup(BaseRoleGroupBO roleGroup) {
		this.getListRoleGroup().remove(roleGroup);
		roleGroup.getListUser().remove(this);
	}

}
