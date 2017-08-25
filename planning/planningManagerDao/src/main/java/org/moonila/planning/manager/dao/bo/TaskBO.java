package org.moonila.planning.manager.dao.bo;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.moonila.common.dao.bo.BaseCrudBO;

@Entity
@Table(name = "TASK")
public class TaskBO extends BaseCrudBO implements Comparable<TaskBO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6979527660450319101L;

	public TaskBO() {
		super();
	}

	public TaskBO(String id, String name) {
		super();
		this.name = name;
		this.id = id;
	}

	public TaskBO(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "TaskBO [name=" + name + ", description=" + description + "]";
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
		TaskBO other = (TaskBO) obj;
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

	@Override
	public int compareTo(TaskBO other) {
		return this.name.compareTo(other.getName());
	}
}
