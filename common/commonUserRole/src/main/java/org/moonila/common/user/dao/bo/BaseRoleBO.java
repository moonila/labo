package org.moonila.common.user.dao.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Sandra Trino
 * 
 */
@Entity(name = "BASE_ROLE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRoleBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	private String description;


}
