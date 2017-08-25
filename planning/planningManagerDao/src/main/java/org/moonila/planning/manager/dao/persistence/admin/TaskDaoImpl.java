package org.moonila.planning.manager.dao.persistence.admin;

import org.moonila.common.dao.persistence.GenericHibernateDAOImpl;
import org.moonila.planning.manager.dao.bo.TaskBO;
import org.springframework.stereotype.Repository;

@Repository("taskDao")
public class TaskDaoImpl extends GenericHibernateDAOImpl<TaskBO, String> implements ITaskDao {
	
	
	
	public TaskDaoImpl() {
		super();
		setType();
	}

	public void setType() {
		super.setType(TaskBO.class);
	}

}
