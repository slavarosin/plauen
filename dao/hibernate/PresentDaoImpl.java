package net.gobro.plauen.dao.hibernate;

import net.gobro.plauen.dao.PresentDao;
import net.gobro.plauen.model.Present;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class PresentDaoImpl extends GenericDAOHibernateImpl<Present, Integer>
		implements PresentDao {

	@Autowired
	public PresentDaoImpl(SessionFactory sessionFactory) {
		super(Present.class);
		setSessionFactory(sessionFactory);
	}

}
