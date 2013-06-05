package net.gobro.plauen.service.impl;

import net.gobro.plauen.dao.ImageDao;
import net.gobro.plauen.model.Image;
import net.gobro.plauen.service.ImageService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ImageServiceImpl implements ImageService {

	private static final Logger LOG = LoggerFactory
			.getLogger(ImageServiceImpl.class);

	@Autowired
	private ImageDao imageDao;

	@Override
	public byte[] fetch(Integer id) {
		Image image = fetchObject(id);

		if (image == null) {
			return null;
		} else {
			return image.getImage();
		}
	}

	@Override
	public void remove(Integer id) {
		Image imageObj = imageDao.fetch(id);

		imageDao.remove(imageObj);

		if (LOG.isInfoEnabled()) {
			LOG.info("Image [" + imageObj + "] removed");
		}
	}

	@Override
	public Image fetchObject(Integer id) {
		return imageDao.fetch(id);
	}
}
