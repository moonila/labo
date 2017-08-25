package org.moonila.common.dao.persistence;

import java.io.Serializable;

/**
 * 
 * @author Sandra Trino
 * 
 */
public interface GenericInterfaceDao<T, PK extends Serializable> extends
		GenericORMDAO<T, PK> {
	void setType();
}
