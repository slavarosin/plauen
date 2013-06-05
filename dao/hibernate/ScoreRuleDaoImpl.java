package net.gobro.plauen.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import net.gobro.plauen.dao.ScoreRuleDao;
import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.ScoreRule;

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
public class ScoreRuleDaoImpl extends
		GenericDAOHibernateImpl<ScoreRule, Integer> implements ScoreRuleDao {

	@Autowired
	public ScoreRuleDaoImpl(SessionFactory sessionFactory) {
		super(ScoreRule.class);
		setSessionFactory(sessionFactory);
	}

	@Override
	public ScoreRule getScoreRule(final CountryEnum country,
			final BigDecimal price) {
		return (ScoreRule) getHibernateTemplate().execute(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(
								ScoreRule.class).add(
								Restrictions.and(Restrictions.eq("country",
										country), Restrictions.eq("price",
										price))).setCacheable(true);

						Object obj = criteria.uniqueResult();

						if (obj != null) {
							Hibernate.initialize(obj);
						}

						return obj;
					}

				});

	}

	@Override
	public ScoreRule getScoreRule(final CountryEnum country,
			final String keyword) {
		return (ScoreRule) getHibernateTemplate().execute(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(
								ScoreRule.class).add(
								Restrictions.and(Restrictions.eq("country",
										country), Restrictions.eq("keyword",
										keyword))).setCacheable(true);

						Object obj = criteria.uniqueResult();

						if (obj != null) {
							Hibernate.initialize(obj);
						}

						return obj;
					}

				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ScoreRule> getScoreRules(final CountryEnum country) {
		return (List<ScoreRule>) getHibernateTemplate().execute(
				new HibernateCallback() {

					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(
								ScoreRule.class).add(
								Restrictions.eq("country", country))
								.setCacheable(true);

						return criteria.list();
					}
				});
	}
}
