package org.moonila.planning.manager.core.utils;

import org.moonila.planning.manager.dao.persistence.admin.IProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class PlanningManagerDaoFactory {

	/*******************************
	 * ProjectDao
	 *******************************/
	@Autowired
	@Qualifier("projectDao")
	private IProjectDao projectDao;

	public IProjectDao getProjectDao() {
		return projectDao;
	}

}
