package net.gobro.plauen.service;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.model.SMSRejectedException;
import net.gobro.plauen.model.ScoreRule;
import net.gobro.plauen.model.Sms;

public interface SmsService {

	Sms fetch(Integer id);

	Sms store(Sms sms, boolean validate) throws SMSRejectedException;

	List<Sms> fetchAll(Integer gameId);

	List<Sms> fetchAll(Calendar timestamp);

	Sms createFakeSms(String alias, ScoreRule scoreRule);

}
