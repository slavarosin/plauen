package net.gobro.plauen.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.dao.SmsDao;
import net.gobro.plauen.model.RoleEnum;
import net.gobro.plauen.model.Sms;
import net.gobro.plauen.model.StatusEnum;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class SmsDaoImpl extends GenericDAOHibernateImpl<Sms, Integer> implements
		SmsDao {

	@Autowired
	public SmsDaoImpl(SessionFactory sessionFactory) {
		super(Sms.class);
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sms> fetchAll(final Integer gameId) {
		return (List<Sms>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {

						Criteria criteria = session
								.createCriteria(Sms.class)
								.add(
										Restrictions.ne("status",
												StatusEnum.FAILED))
								.setResultTransformer(
										Criteria.DISTINCT_ROOT_ENTITY)
								.setCacheable(true)
								.createCriteria("userPlay")
								.add(Restrictions.eq("disabled", false))
								.createCriteria("game", "game")
								.add(Restrictions.eq("id", gameId))
								.add(
										Expression
												.sql("timestamp between {alias}.start_time and {alias}.end_time"));

						List<Sms> list = criteria.list();

						for (Sms sms : list) {
							if (sms != null) {
								Hibernate.initialize(sms);
								Hibernate.initialize(sms.getUserPlay());
								Hibernate.initialize(sms.getUserPlay()
										.getUser());
							}
						}

						return list;
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sms> fetchAll(final Calendar timestamp) {
		return (List<Sms>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {

						Calendar today = Calendar.getInstance();
						today.setTime(DateUtils.truncate(timestamp.getTime(),
								Calendar.DATE));

						Calendar tomorrow = Calendar.getInstance();
						tomorrow.setTime(DateUtils.truncate(
								timestamp.getTime(), Calendar.DATE));
						tomorrow.add(Calendar.DATE, 1);

						Criteria criteria = session.createCriteria(Sms.class)
								.add(
										Restrictions.between("timestamp",
												today, tomorrow))
								.setResultTransformer(
										Criteria.DISTINCT_ROOT_ENTITY)
								.setCacheable(true).createCriteria("userPlay")
								.createCriteria("user").add(
										Restrictions.eq("role", RoleEnum.USER));

						List<Sms> list = criteria.list();

						for (Sms sms : list) {
							if (sms != null) {
								Hibernate.initialize(sms);
								Hibernate.initialize(sms.getUserPlay());
								Hibernate.initialize(sms.getUserPlay()
										.getUser());
							}
						}

						return list;
					}
				});
	}
}
