package net.gobro.plauen.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.dao.UserDao;
import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.User;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDAOHibernateImpl<User, Integer>
		implements UserDao {

	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		super(User.class);
		setSessionFactory(sessionFactory);
	}

	public User fetch(final Integer id) {
		return (User) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				User user = (User) session.get(User.class, id);

				if (user != null) {
					Hibernate.initialize(user);
					Hibernate.initialize(user.getUserPlays());
				}

				return user;
			}
		});
	}

	/**
	 * @see net.gobro.plauen.dao.UserDao#fetch(java.lang.String,
	 *      java.lang.String)
	 * @todo remove this method
	 */
	public User fetch(final String username, final String passwd) {
		return (User) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = session.createCriteria(User.class);

				return criteria.add(
				// TODO check username ignoring case
						Restrictions.and(Restrictions.eq("login", username),
								Restrictions.eq("passwd", passwd)))
						.setCacheable(true).uniqueResult();
			}
		});
	}

	public User fetch(final String username) {
		return (User) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = session.createCriteria(User.class);

				return criteria.add(
				// TODO check username ignoring case
						Restrictions.eq("login", username)).setCacheable(true)
						.uniqueResult();
			}
		});
	}

	public User fetchByEmail(final String email) {
		return (User) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = session.createCriteria(User.class);

				return criteria.add(Restrictions.eq("email", email))
						.setCacheable(true).uniqueResult();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<User> fetchAll(final RoleEnum role) {
		return (List<User>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(User.class);

						return criteria.add(Restrictions.eq("role", role))
								.setCacheable(true).list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<User> fetchAll(final Calendar timestamp) {
		return (List<User>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(User.class);

						Calendar today = Calendar.getInstance();
						today.setTime(DateUtils.truncate(timestamp.getTime(),
								Calendar.DATE));

						Calendar tomorrow = Calendar.getInstance();
						tomorrow.setTime(DateUtils.truncate(
								timestamp.getTime(), Calendar.DATE));
						tomorrow.add(Calendar.DATE, 1);

						return criteria.add(
								Restrictions.eq("role", RoleEnum.USER)).add(
								Restrictions.ge("signedUp", today)).add(
								Restrictions.le("signedUp", tomorrow))
								.setCacheable(true).setResultTransformer(
										Criteria.DISTINCT_ROOT_ENTITY).list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<User> fetchAll(final int notifications) {
		return (List<User>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(User.class);

						return criteria.add(
								Restrictions.eq("role", RoleEnum.USER))
								.add(
										Restrictions.eq("notifications",
												notifications)).setCacheable(
										true).setResultTransformer(
										Criteria.DISTINCT_ROOT_ENTITY).list();
					}
				});
	}
}
