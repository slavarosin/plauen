package net.gobro.plauen.dao.hibernate;

import net.gobro.plauen.dao.TextDao;
import net.gobro.plauen.model.Text;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class TextDaoImpl extends GenericDAOHibernateImpl<Text, Integer>
		implements TextDao {

	@Autowired
	public TextDaoImpl(SessionFactory sessionFactory) {
		super(Text.class);
		setSessionFactory(sessionFactory);
	}

}
