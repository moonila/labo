package org.moonila.planning.manager.dao.persistence.admin;

import org.moonila.common.dao.persistence.GenericHibernateDAOImpl;
import org.moonila.planning.manager.dao.bo.ProjectBO;
import org.springframework.stereotype.Repository;

@Repository("projectDao")
public class ProjectDaoImpl extends GenericHibernateDAOImpl<ProjectBO, Long>
		implements IProjectDao {

	public ProjectDaoImpl() {
		super();
		setType();
	}

	public void setType() {
		super.setType(ProjectBO.class);
	}

}
