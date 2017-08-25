package org.moonila.planning.manager.core.to;

import java.io.Serializable;
import java.util.List;

import org.moonila.common.crud.BaseCrudTO;

public class ProjectTO extends BaseCrudTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8591432549815513734L;

	private List<TaskTO> projectTasks;

	public ProjectTO() {
		super();
	}
	
	public ProjectTO(String projectId, String projectName) {
		super();
		this.name = projectName;
		this.id = projectId;
	}

	public List<TaskTO> getProjectTasks() {
		return projectTasks;
	}

	public void setProjectTasks(List<TaskTO> projectTasks) {
		this.projectTasks = projectTasks;
	}
}
