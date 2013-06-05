package net.gobro.plauen.service.impl;

import net.gobro.plauen.dao.TextDao;
import net.gobro.plauen.model.Text;
import net.gobro.plauen.service.TextService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TextServiceImpl implements TextService {
	private static final Logger LOG = LoggerFactory
			.getLogger(TextServiceImpl.class);

	@Autowired
	private TextDao textDao;

	@Override
	public Text fetch(Integer id) {
		return getTextDao().fetch(id);
	}

	public TextDao getTextDao() {
		return textDao;
	}

	public void setTextDao(TextDao textDao) {
		this.textDao = textDao;
	}

	@Override
	public Text store(Text text) {
		textDao.store(text);

		if (LOG.isInfoEnabled()) {
			LOG.info("Text stored: " + text);
		}

		return text;
	}
}
