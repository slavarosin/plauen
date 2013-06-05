package net.gobro.plauen.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import net.gobro.plauen.dao.GenericDAO;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


/**
 * Implements base operations on DAO objects.
 *
 * @author Igor Bljahhin
 *
 * @since 1.0
 */
public abstract class GenericDAOHibernateImpl<T, PK extends Serializable>
		extends HibernateDaoSupport implements GenericDAO<T, PK> {
	private Class<T> type;

	public GenericDAOHibernateImpl(Class<T> type) {
		this.type = type;
	}

	protected void addOrder(Criteria criteria, boolean asc, String field) {
		if (field != null) {
			if (asc) {
				criteria.addOrder(Order.asc(field));
			} else {
				criteria.addOrder(Order.desc(field));
			}
		}
	}

	/**
	 *
	 * @see net.gobro.plauen.dao.GenericDAO#batchStore(List)
	 */
	public void batchStore(final List<T> objectList) {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				for (Object i : objectList) {
					session.saveOrUpdate(i);
				}

				return null;
			}
		});
	}

	/**
	 *
	 * @see net.gobro.plauen.dao.GenericDAO#evict(Object)
	 */
	public void evict(T entity) {
		if (entity != null) {
			getHibernateTemplate().evict(entity);
		}
	}

	/**
	 * @see net.gobro.plauen.dao.GenericDAO#fetch(java.io.Serializable)
	 */
	@SuppressWarnings("unchecked")
	public T fetch(final PK id) {
		return (T) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Object o = session.get(type, id);

				if (o != null) {
					Hibernate.initialize(o);
				}

				return o;
			}
		});
	}

	public Class<T> getType() {
		return type;
	}

	/**
	 *
	 * @see net.gobro.plauen.dao.GenericDAO#removeItem(java.lang.Object)
	 */
	public void remove(T object) {
		getHibernateTemplate().delete(object);
	}

	/**
	 *
	 * @see net.gobro.plauen.dao.GenericDAO#removeAndFlush(Object)
	 */
	public void removeAndFlush(T object) {
		getHibernateTemplate().delete(object);
		getHibernateTemplate().flush();
	}

	/**
	 *
	 * @see net.gobro.plauen.dao.GenericDAO#store(Object, boolean)
	 */
	public void store(T object) {
		getHibernateTemplate().saveOrUpdate(object);
	}
}
