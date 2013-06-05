package net.gobro.plauen.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.dao.GameDao;
import net.gobro.plauen.dao.criteria.GameCriteria;
import net.gobro.plauen.model.Game;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;


@Repository
public class GameDaoImpl extends GenericDAOHibernateImpl<Game, Integer>
		implements GameDao {

	@Autowired
	public GameDaoImpl(SessionFactory sessionFactory) {
		super(Game.class);
		setSessionFactory(sessionFactory);
	}

	@Override
	public Game fetch(final Integer id) {
		return (Game) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Game obj = (Game) session.get(Game.class, id);

				if (obj != null) {
					Hibernate.initialize(obj);
					Hibernate.initialize(obj.getUserPlays());
					Hibernate.initialize(obj.getPresent());
					Hibernate.initialize(obj.getPresent().getTexts());
					Hibernate.initialize(obj.getPresent().getImages());
				}

				return obj;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> find(final GameCriteria searchCriteria) {
		return (List<Game>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session
								.createCriteria(Game.class)
								.setResultTransformer(
										CriteriaSpecification.DISTINCT_ROOT_ENTITY)
								.setCacheable(true);

						if (searchCriteria.getRegistrationPeriod() != null) {
							if ((searchCriteria.getRegistrationPeriod()
									.getStart() != null)
									&& (searchCriteria.getRegistrationPeriod()
											.getEnd() != null)) {
								criteria.add(Restrictions.between("activateAt",
										searchCriteria.getRegistrationPeriod()
												.getStart(), searchCriteria
												.getRegistrationPeriod()
												.getEnd()));
							} else if (searchCriteria.getRegistrationPeriod()
									.getStart() != null) {
								criteria.add(Restrictions.ge("activateAt",
										searchCriteria.getRegistrationPeriod()
												.getStart()));
							} else if (searchCriteria.getRegistrationPeriod()
									.getEnd() != null) {
								criteria.add(Restrictions.lt("activateAt",
										searchCriteria.getRegistrationPeriod()
												.getEnd()));
							}
						}

						if (searchCriteria.getPlayPeriod() != null) {
							if ((searchCriteria.getPlayPeriod().getStart() != null)
									&& (searchCriteria.getPlayPeriod().getEnd() != null)) {
								criteria.add(Restrictions.between("startedAt",
										searchCriteria.getPlayPeriod()
												.getStart(), searchCriteria
												.getPlayPeriod().getEnd()));
							} else if (searchCriteria.getPlayPeriod()
									.getStart() != null) {
								criteria.add(Restrictions.ge("startedAt",
										searchCriteria.getPlayPeriod()
												.getStart()));
							} else if (searchCriteria.getPlayPeriod().getEnd() != null) {
								criteria.add(Restrictions
										.lt("startedAt", searchCriteria
												.getPlayPeriod().getEnd()));
							}
						}

						if (searchCriteria.getCountry() != null) {
							criteria.add(Restrictions.eq("country",
									searchCriteria.getCountry()));
						}

						List<Game> list = criteria.list();

						for (Game game : list) {
							if (game != null) {
								Hibernate.initialize(game);
								Hibernate.initialize(game.getPresent());
								Hibernate.initialize(game.getPresent()
										.getTexts());
								Hibernate.initialize(game.getPresent()
										.getImages());
								Hibernate.initialize(game.getUserPlays());
							}
						}

						return list;
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> findClosedGames(final Calendar forDate) {
		return (List<Game>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session
								.createCriteria(Game.class)
								.setResultTransformer(
										CriteriaSpecification.DISTINCT_ROOT_ENTITY)
								.setCacheable(true);
						criteria.add(Restrictions.lt("finishedAt", forDate));

						List<Game> list = criteria.list();

						for (Game game : list) {
							if (game != null) {
								Hibernate.initialize(game);
								Hibernate.initialize(game.getPresent());
								Hibernate.initialize(game.getPresent()
										.getTexts());
								Hibernate.initialize(game.getPresent()
										.getImages());
								Hibernate.initialize(game.getUserPlays());
							}
						}

						return list;
					}
				});
	}
}
