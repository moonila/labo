package org.moonila.common.crud;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface ICrudManager<T> {

	@Transactional
    List<T> createOrUpdateObjects(List<T> objectsToBeInserted) throws CrudException;
	
	@Transactional
    String createOrUpdateSingleObject(T objectToBeInserted) throws CrudException;

    @Transactional
    List<T> deleteObjectsByIds(List<String> idsList) throws CrudException;
    
    @Transactional
    void deleteSingleObject(String id) throws CrudException;

    @Transactional(readOnly = true) 
    List<T> getAllObject();
    
    @Transactional(readOnly = true) 
    T getObject(String id) throws CrudException;
    

}
