package net.gobro.plauen.dao;

import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.model.Sms;

public interface SmsDao extends GenericDAO<Sms, Integer> {

	List<Sms> fetchAll(Integer gameId);

	List<Sms> fetchAll(Calendar timestamp);

}
