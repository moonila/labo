package org.moonila.planning.manager.dao.bo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.moonila.common.dao.bo.BaseCrudBO;

@Entity
@Table(name = "PROJECT")
public class ProjectBO extends BaseCrudBO implements Comparable<ProjectBO> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7164203567047390355L;

	private List<TaskBO> projectTasks;

	public ProjectBO() {
		super();
	}

	public ProjectBO(String id, String name) {
		super();
		this.name = name;
		this.id = id;
	}

	public ProjectBO(String name) {
		super();
		this.name = name;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	public List<TaskBO> getProjectTasks() {
		return projectTasks;
	}

	public void setProjectTasks(List<TaskBO> projectTasks) {
		this.projectTasks = projectTasks;
	}

	@Override
	public int compareTo(ProjectBO other) {
		return this.name.compareTo(other.getName());
	}

	@Override
	public String toString() {
		return "ProjectBO [name=" + name + ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ProjectBO other = (ProjectBO) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
