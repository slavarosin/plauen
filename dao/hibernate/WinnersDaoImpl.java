package net.gobro.plauen.dao.hibernate;

import java.util.List;

import net.gobro.plauen.dao.WinnersDao;
import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.Winners;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class WinnersDaoImpl extends GenericDAOHibernateImpl<Winners, Integer>
		implements WinnersDao {

	@Autowired
	public WinnersDaoImpl(SessionFactory sessionFactory) {
		super(Winners.class);
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Winners> fetchList(final int number, final CountryEnum country) {
		return (List<Winners>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(
								Winners.class)
								.addOrder(Order.desc("timestamp"))
								.createCriteria("game").add(
										Restrictions.eq("country", country))
								.setFirstResult(0).setCacheable(true)
								.setResultTransformer(
										Criteria.DISTINCT_ROOT_ENTITY);

						List<Winners> list = criteria.list();
						list = list.subList(0, number);

						for (Winners obj : list) {
							if (obj != null) {
								Hibernate.initialize(obj);
								Hibernate.initialize(obj.getUser());
								Hibernate.initialize(obj.getGame());
							}
						}

						return list;
					}
				});

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Winners> fetchAll() {
		return (List<Winners>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(
								Winners.class)
								.addOrder(Order.desc("timestamp"))
								.setCacheable(true);

						List<Winners> list = criteria.list();

						for (Winners obj : list) {
							if (obj != null) {
								Hibernate.initialize(obj);
								Hibernate.initialize(obj.getUser());
								Hibernate.initialize(obj.getGame());
							}
						}

						return list;
					}
				});
	}
}
