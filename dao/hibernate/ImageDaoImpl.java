package net.gobro.plauen.dao.hibernate;

import net.gobro.plauen.dao.ImageDao;
import net.gobro.plauen.model.Image;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ImageDaoImpl extends GenericDAOHibernateImpl<Image, Integer>
		implements ImageDao {

	@Autowired
	public ImageDaoImpl(SessionFactory sessionFactory) {
		super(Image.class);
		setSessionFactory(sessionFactory);
	}

}
