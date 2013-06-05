package net.gobro.plauen.service.impl;

import java.util.List;

import net.gobro.plauen.dao.ScoreRuleDao;
import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.ScoreRule;
import net.gobro.plauen.model.Sms;
import net.gobro.plauen.service.ScoreService;

import org.springframework.beans.factory.annotation.Autowired;


public class PriceBasedScoreServiceImpl implements ScoreService {
	@Autowired
	private ScoreRuleDao scoreRuleDao;

	@Override
	public Integer getScore(Sms sms) {
		Integer score = null;

		if (sms != null) {
			ScoreRule scoreRule = scoreRuleDao.getScoreRule(sms.getCountry(),
					sms.getPrice());

			if (scoreRule != null) {
				score = scoreRule.getScore();
			}
		}

		return score;
	}

	public ScoreRuleDao getScoreRuleDao() {
		return scoreRuleDao;
	}

	@Override
	public List<ScoreRule> getScoreRules(CountryEnum currentCountry) {
		throw new RuntimeException("The method is not implemented");
	}

	public void setScoreRuleDao(ScoreRuleDao scoreRuleDao) {
		this.scoreRuleDao = scoreRuleDao;
	}

	@Override
	public ScoreRule fetch(Integer id) {
		throw new RuntimeException("The method is not implemented");
	}

}
