package net.gobro.plauen.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Base DAO interface for DAOs. Defines common methods.
 *
 * @author Igor Bljahhin
 * @since 1.0
 */
public interface GenericDAO<T, PK extends Serializable> {
	/**
	 * Saves or updates multiple objects.
	 *
	 * @param objectList
	 *            list of objects to be stored
	 */
	void batchStore(List<T> objectList);

	/**
	 * Remove the given object from the Session cache. Ignores null input.
	 *
	 * @param entity
	 *            the persistent instance to evict
	 */
	void evict(T entity);

	/**
	 * Fetches object by primary key.
	 *
	 * @param id
	 *            the primary key of the object
	 *
	 * @return the fetched object
	 */
	T fetch(PK id);

	/**
	 * Removes object from data store.
	 *
	 * @param object
	 *            the object being removed
	 */
	void remove(T object);

	/**
	 * Removes the object from data store and flushes the session.
	 *
	 * @param object
	 *            the object being removed.
	 */
	void removeAndFlush(T object);

	/**
	 * Saves or updates object.
	 *
	 * @param object
	 *            the object being persisted
	 */
	void store(T object);
}
