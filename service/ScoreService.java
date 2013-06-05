package net.gobro.plauen.service;

import java.util.List;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.ScoreRule;
import net.gobro.plauen.model.Sms;


public interface ScoreService {
	Integer getScore(Sms sms);

	List<ScoreRule> getScoreRules(CountryEnum currentCountry);

	ScoreRule fetch(Integer id);
}
