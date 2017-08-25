package org.moonila.common.dao.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.NonUniqueResultException;
import org.springframework.transaction.annotation.Transactional;

import com.trg.search.IMutableSearch;
import com.trg.search.ISearch;
import com.trg.search.SearchResult;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * 
 * <p>
 * Extend this interface if you want typesafe (no casting necessary) DAO's for
 * your domain objects.
 * 
 * @author Sandra Trino
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 * 
 */
public interface GenericORMDAO<T, PK extends Serializable> {

    /**
     * Generic method used to get all objects of a particular type. This is the
     * same as lookup up all rows in a table.
     * 
     * @return List of populated objects
     */
    List<T> getAll();

    /**
     * Return the persistent instance of an entity with the given identifier, or
     * <code>null</code> if not found.
     * 
     * @param id
     *            the identifier of the persistent instance
     * @return the persistent instance, or <code>null</code> if not found
     * @throws org.springframework.dao.DataAccessException
     *             in case of Hibernate errors
     * @see org.hibernate.Session#get(Class, java.io.Serializable)
     */
    T get(PK id);
    
    /**
     * Return the persistent instance of an entity with the given name, or
     * <code>null</code> if not found.
     * 
     * @param propertyName
     *            the property of the persistent instance
     * @param value
     *            value of the property
     * @return the persistent instance, or <code>null</code> if not found
     */
    T getByPropertyUnique(String propertyName, Object value);

    /**
     * Checks for existence of an object of type T using the id arg.
     * 
     * @param id
     *            the id of the entity
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);
    
    /**
     * Checks for existence of an object of type T using an unique property.
     * 
     * @param propertyName 
     * 			  the name of property to be tested
     * @param value
     *            the value of the property to be tested
     * @return - true if it exists, false if it doesn't
     */
    boolean existsByUniqueKey(String propertyName, Object value);

    /**
     * Generic method to save an object - handles both update and insert.
     * 
     * @param object
     *            the object to save
     * @return the persisted object
     */
    @Transactional
    T saveOrUpdate(T object);

    /**
     * Generic method to delete an object based on class and id
     * 
     * @param id
     *            the identifier (primary key) of the object to remove
     */
    void remove(PK id);

    /**
     * Generic method to delete an object
     * 
     * @param object
     *            the object to remove
     */
    void remove(T object);

    /**
     * Generic method to delete a list of objects
     * 
     * @param objects
     *            the object list to remove
     */
    void removeAll(List<T> objects);

    /**
     * Gets all records without duplicates.
     * <p>
     * Note that if you use this method, it is imperative that your model
     * classes correctly implement the hashcode/equals methods
     * </p>
     * 
     * @return List of populated objects
     */
    List<T> getAllDistinct();

    /**
     * Find a list of records by using a named query
     * 
     * @param queryName
     *            query name of the named query
     * @param queryParams
     *            a map of the query names and the values
     * @return a list of the records found
     */
    List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams);

    /**
     * Return the type of entity manipulated by this DAO
     * 
     * @return the {@link Class} of the manipulated entity
     */
    Class<T> getManipulatedType();


    /**
     * Search for objects based on the search parameters in the specified
     * <code>ISearch</code> object.
     * 
     * @see ISearch
     */
    public List<T> search(IMutableSearch search);

    /**
     * Returns the total number of results that would be returned using the
     * given <code>ISearch</code> if there were no paging or maxResult limits.
     * 
     * @see ISearch
     */
    public int count(IMutableSearch search);

    /**
     * Returns a <code>SearchResult</code> object that includes the list of
     * results like <code>search()</code> and the total length like
     * <code>searchLength</code>.
     * 
     * @see ISearch
     */
    public SearchResult<T> searchAndCount(IMutableSearch search);

    /**
     * Search for a single result using the given parameters.
     */
    public T searchUnique(IMutableSearch search) throws NonUniqueResultException;

}
