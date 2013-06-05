package net.gobro.plauen.service.impl;

import net.gobro.plauen.dao.PresentDao;
import net.gobro.plauen.model.Present;
import net.gobro.plauen.service.PresentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PresentServiceImpl implements PresentService {
	private static final Logger LOG = LoggerFactory
			.getLogger(PresentServiceImpl.class);

	@Autowired
	private PresentDao presentDao;

	@Override
	public Present store(Present present) {
		// store present
		presentDao.store(present);

		if (LOG.isInfoEnabled()) {
			LOG.info("Present stored: " + present);
		}

		return present;
	}

}
