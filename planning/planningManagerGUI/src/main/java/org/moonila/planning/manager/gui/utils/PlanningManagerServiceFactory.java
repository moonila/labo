package org.moonila.planning.manager.gui.utils;

import org.moonila.common.crud.ICrudManager;
import org.moonila.common.spring.SpringApplicationContext;
import org.moonila.planning.manager.core.to.ProjectTO;


public class PlanningManagerServiceFactory {
	
	
	@SuppressWarnings("unchecked")
	public ICrudManager<ProjectTO> getProjectManager() {
		return (ICrudManager<ProjectTO>) SpringApplicationContext
				.getContext().getBean("projectManager");
	}

}
