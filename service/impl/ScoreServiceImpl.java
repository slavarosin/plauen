package net.gobro.plauen.service.impl;

import java.util.List;
import java.util.Map;

import net.gobro.plauen.dao.ScoreRuleDao;
import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.ScoreRule;
import net.gobro.plauen.model.Sms;
import net.gobro.plauen.service.ScoreService;

import org.springframework.beans.factory.annotation.Autowired;

public class ScoreServiceImpl implements ScoreService {
	private Map<CountryEnum, ScoreService> countryMap;
	@Autowired
	private ScoreRuleDao scoreRuleDao;

	public ScoreServiceImpl(Map<CountryEnum, ScoreService> countryMap) {
		this.countryMap = countryMap;
	}

	@Override
	public Integer getScore(Sms sms) {
		if (sms != null) {
			if (sms.isDisabled())
				return null;
			ScoreService scoreService = countryMap.get(sms.getCountry());

			if (scoreService != null) {
				return scoreService.getScore(sms);
			} else {
				throw new RuntimeException(
						"ScoreService is not assigned to the country "
								+ sms.getCountry());
			}
		} else {
			return null;
		}
	}

	@Override
	public List<ScoreRule> getScoreRules(CountryEnum country) {
		return scoreRuleDao.getScoreRules(country);
	}

	@Override
	public ScoreRule fetch(Integer id) {
		return scoreRuleDao.fetch(id);
	}

}
