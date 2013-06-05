package net.gobro.plauen.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.dao.UserPlayDao;
import net.gobro.plauen.model.UserPlay;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;


@Repository
public class UserPlayDaoImpl extends GenericDAOHibernateImpl<UserPlay, Integer>
		implements UserPlayDao {

	@Autowired
	public UserPlayDaoImpl(SessionFactory sessionFactory) {
		super(UserPlay.class);
		setSessionFactory(sessionFactory);
	}

	@Override
	public UserPlay fetch(final String alias) {
		return (UserPlay) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						return session.createCriteria(UserPlay.class).add(
								Restrictions.eq("alias", alias)).setCacheable(
								true).uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<UserPlay> findInactivePlayers(final Calendar forDate) {
		return (List<UserPlay>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) {
						Criteria criteria = session
								.createCriteria(UserPlay.class);
						criteria.add(Restrictions.eq("disabled", false));
						criteria.add(Restrictions.isEmpty("sms"));
						criteria.createCriteria("game").add(
								Restrictions.and(Restrictions.le("startedAt",
										forDate), Restrictions.gt("finishedAt",
										forDate)));

						List<UserPlay> list = criteria.list();

						return list;
					}
				});
	}
}
