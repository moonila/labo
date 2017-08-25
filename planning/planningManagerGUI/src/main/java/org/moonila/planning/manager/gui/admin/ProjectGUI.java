package org.moonila.planning.manager.gui.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.moonila.planning.manager.core.to.ProjectTO;
import org.moonila.planning.manager.gui.utils.PlanningManagerServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ManagedBean
@SessionScoped
public class ProjectGUI extends PlanningManagerServiceFactory implements
		Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 553400438316253329L;

	private List<ProjectTO> allProjects;
	private String projectName;
	private Map<Long, Boolean> checked;
	

	private final static Logger logger = LoggerFactory
			.getLogger(ProjectGUI.class);

	public ProjectGUI() {
		allProjects = getProjectManager().getAllObject();
		checked = new HashMap<Long, Boolean>();
		createFakeProjects();
	}
	
	private void createFakeProjects() {
		logger.debug("This is a list of fake project");
		for(int i=0; i<10; i++){
			projectName = "projet"+i;
			createOrUpdateProject("pr"+i);
		}
	}

	public String createOrUpdateProject(String id) {
		ProjectTO project = new ProjectTO();
		project.setName(projectName);
		project.setId(id);
		allProjects.add(project);
		
		projectName = null;
		return "success";
	}

	public String removeProjects() {
		List<ProjectTO> projectTmpList = new ArrayList<ProjectTO>();
		projectTmpList.addAll(allProjects);
		for (ProjectTO project : projectTmpList) {
            if (checked.get(project.getId())) {
            	allProjects.remove(project);
            }
        }

        checked.clear(); // If necessary.


		return "success";
	}

	/**
	 * 
	 * Getters And Setters
	 * 
	 **/

	public List<ProjectTO> getAllProjects() {
		return allProjects;
	}

	public void setAllProjects(List<ProjectTO> allProjects) {
		this.allProjects = allProjects;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Map<Long, Boolean> getChecked() {
		return checked;
	}

	public void setChecked(Map<Long, Boolean> checked) {
		this.checked = checked;
	}
}