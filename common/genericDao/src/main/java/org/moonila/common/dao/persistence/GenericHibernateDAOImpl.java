package org.moonila.common.dao.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.trg.search.Filter;
import com.trg.search.IMutableSearch;
import com.trg.search.Search;
import com.trg.search.SearchResult;
import com.trg.search.hibernate.HibernateSearchProcessor;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * 
 * 
 * @author Sandra Trino
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 * 
 */
public class GenericHibernateDAOImpl<T, PK extends Serializable> implements
		GenericORMDAO<T, PK> {

	private final static Logger logger = LoggerFactory
			.getLogger(GenericHibernateDAOImpl.class);

	@Autowired
	@Qualifier("sessionFactory")
	private LocalSessionFactoryBean localSessionFactoryBean;

	public Class<T> type;

	public GenericHibernateDAOImpl() {

	}

	public Session getSession() {
		SessionFactory sessionFactory = localSessionFactoryBean.getObject();
		Session toto = sessionFactory.getCurrentSession();
		return toto;
	}

	public void setSession(LocalSessionFactoryBean localFactory) {
		if (localSessionFactoryBean == null) {
			localSessionFactoryBean = localFactory;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAllByCriteria(Class<T> persistentClass,
			Criterion... criterion) {
		Criteria crit = getSession().createCriteria(persistentClass);
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	public List<T> getAll() {
		return getAllByCriteria(this.type, new Criterion[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> getAllDistinct() {

		Collection result = new LinkedHashSet(getAll());
		return new ArrayList(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		return (T) getSession().load(this.type, id);
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	public T getByPropertyUnique(String propertyName, Object value) {
		Search search = new Search();
		search.addFilterAnd(Filter.equal(propertyName, value));

		return searchUnique(search);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean existsByUniqueKey(String propertyName, Object value) {
		T entity = (T) getByPropertyUnique(propertyName, value);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(PK id) {
		T entity = (T) getSession().load(this.type, id);
		return entity != null;
	}

	/**
	 * {@inheritDoc}
	 */
	public T saveOrUpdate(T object) {
		getSession().saveOrUpdate(object);
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		T object = this.get(id);
		// if object is null, skip removal
		if (object != null) {
			getSession().delete(object);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(T object) {
		// if object is null, skip removal
		if (object != null) {
			getSession().delete(object);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeAll(List<T> objects) {
		// if objects is null, skip removal
		if (objects != null) {
			for (T object : objects) {
				getSession().delete(object);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName,
			Map<String, Object> queryParams) {
		String[] params = new String[queryParams.size()];
		Object[] values = new Object[queryParams.size()];
		int index = 0;
		Iterator<String> it = queryParams.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			params[index] = key;
			values[index++] = queryParams.get(key);
		}

		Query queryObject = getSession().getNamedQuery(queryName);

		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				applyNamedParameterToQuery(queryObject, params[i], values[i]);
			}
		}
		return queryObject.list();

	}

	@SuppressWarnings("rawtypes")
	protected void applyNamedParameterToQuery(Query queryObject,
			String paramName, Object value) throws HibernateException {

		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection) value);
		} else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		} else {
			queryObject.setParameter(paramName, value);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Class<T> getManipulatedType() {
		return this.type;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> search(IMutableSearch search) {
		HibernateSearchProcessor searchProcessor = HibernateSearchProcessor
				.getInstanceForSessionFactory(localSessionFactoryBean
						.getObject());
		search.setSearchClass(this.type);
		return searchProcessor.search(getSession(), search);
	}

	/**
	 * {@inheritDoc}
	 */
	public int count(IMutableSearch search) {
		HibernateSearchProcessor searchProcessor = HibernateSearchProcessor
				.getInstanceForSessionFactory(localSessionFactoryBean
						.getObject());
		search.setSearchClass(this.type);
		return searchProcessor.count(getSession(), search);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public SearchResult<T> searchAndCount(IMutableSearch search) {
		HibernateSearchProcessor searchProcessor = HibernateSearchProcessor
				.getInstanceForSessionFactory(localSessionFactoryBean
						.getObject());
		search.setSearchClass(this.type);
		return searchProcessor.searchAndCount(getSession(), search);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T searchUnique(IMutableSearch search)
			throws NonUniqueResultException {
		HibernateSearchProcessor searchProcessor = HibernateSearchProcessor
				.getInstanceForSessionFactory(localSessionFactoryBean
						.getObject());
		search.setSearchClass(this.type);
		return (T) searchProcessor.searchUnique(getSession(), search);
	}

	public void setType(Class<T> type) {
		logger.debug("###### Create dao for entity: " + type.getSimpleName());
		this.type = type;
	}

}
