package net.gobro.plauen.service.impl;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.dao.SmsDao;
import net.gobro.plauen.dao.UserPlayDao;
import net.gobro.plauen.model.SMSRejectedException;
import net.gobro.plauen.model.ScoreRule;
import net.gobro.plauen.model.Sms;
import net.gobro.plauen.model.StatusEnum;
import net.gobro.plauen.model.UserPlay;
import net.gobro.plauen.service.SmsService;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService {
	private static final Logger LOG = LoggerFactory
			.getLogger(SmsServiceImpl.class);

	@Autowired
	private SmsDao smsDao;

	@Autowired
	private UserPlayDao userPlayDao;

	@Override
	public Sms fetch(Integer id) {
		return getSmsDao().fetch(id);
	}

	public SmsDao getSmsDao() {
		return smsDao;
	}

	public void setSmsDao(SmsDao smsDao) {
		this.smsDao = smsDao;
	}

	@Override
	public Sms store(Sms sms, boolean validate) throws SMSRejectedException {
		if (sms.getId() == null) {
			// find owner of SMS
			UserPlay userPlay = userPlayDao.fetch(sms.getMessage());

			if (userPlay == null || userPlay.isDisabled()) {
				if (LOG.isInfoEnabled()) {
					if (userPlay == null) {
						LOG
								.info("SMS ["
										+ sms
										+ "] rejected because it is sent for non-registered alias");
					} else if (userPlay.isDisabled()) {
						LOG
								.info("SMS ["
										+ sms
										+ "] rejected because it is sent for disabled alias");
					}
				}

				throw new SMSRejectedException("sms.error.alias-does-not-exist");
			}

			sms.setUserPlay(userPlay);
		}

		sms.setKeyword(StringUtils.upperCase(sms.getKeyword()));
		sms.setTimestamp(Calendar.getInstance());

		getSmsDao().store(sms);

		if (LOG.isInfoEnabled()) {
			LOG.info("SMS stored: " + sms);
		}

		return sms;
	}

	@Override
	public List<Sms> fetchAll(Integer gameId) {
		return getSmsDao().fetchAll(gameId);
	}

	@Override
	public List<Sms> fetchAll(Calendar timestamp) {
		return getSmsDao().fetchAll(timestamp);
	}

	@Override
	public Sms createFakeSms(String alias, ScoreRule scoreRule) {
		Sms sms = new Sms();
		sms.setCountry(scoreRule.getCountry());
		sms.setCurrency(scoreRule.getCurrency());
		sms.setKeyword(scoreRule.getKeyword());
		sms.setMessage(alias);
		sms.setMessageId("0");
		sms.setPrice(scoreRule.getPrice());
		sms.setSender("localhost");
		sms.setServiceId(scoreRule.getServiceId());
		sms.setShortCode(scoreRule.getShortCode());
		sms.setStatus(StatusEnum.OK);
		return sms;
	}
}
