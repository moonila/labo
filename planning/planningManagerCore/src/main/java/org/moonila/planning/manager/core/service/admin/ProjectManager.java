package org.moonila.planning.manager.core.service.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.moonila.common.crud.CrudException;
import org.moonila.common.crud.ICrudManager;
import org.moonila.planning.manager.core.to.ProjectTO;
import org.moonila.planning.manager.core.utils.PlanningManagerDaoFactory;
import org.moonila.planning.manager.dao.bo.ProjectBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("projectManager")
public class ProjectManager extends PlanningManagerDaoFactory implements
		ICrudManager<ProjectTO> {

	private final static Logger logger = LoggerFactory
			.getLogger(ProjectManager.class);

	public List<ProjectTO> createOrUpdateObjects(
			List<ProjectTO> projectToBeInserted) {
		logger.debug("project list size " + projectToBeInserted.size());
		for (ProjectTO project : projectToBeInserted) {
			logger.debug("project name value " + project.getName());
			getProjectDao().saveOrUpdate(new ProjectBO(project.getName()));
		}

		return getAllObject();
	}

	public List<ProjectTO> deleteObjectsByIds(List<String> projectToBeDeleted) throws CrudException {
		logger.debug("project list size " + projectToBeDeleted.size());
		for (String projectId : projectToBeDeleted) {
			logger.debug("project id value " + projectId);
			deleteSingleObject(projectId);
			
		}

		return getAllObject();
	}

	public List<ProjectTO> getAllObject() {
		List<ProjectBO> allProjectInDataBase = getProjectDao().getAll();
		Collections.sort(allProjectInDataBase);

		List<ProjectTO> allProject = new ArrayList<ProjectTO>();
		for (ProjectBO projectBO : allProjectInDataBase) {
			allProject.add(new ProjectTO(projectBO.getId(), projectBO
					.getName()));
		}

		return allProject;
	}

	@Override
	public String createOrUpdateSingleObject(ProjectTO objectToBeInserted)
			throws CrudException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSingleObject(String id) throws CrudException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProjectTO getObject(String id) throws CrudException {
		// TODO Auto-generated method stub
		return null;
	}

}
