package net.gobro.plauen.dao;

import java.math.BigDecimal;
import java.util.List;

import net.gobro.plauen.model.CountryEnum;
import net.gobro.plauen.model.ScoreRule;


public interface ScoreRuleDao extends GenericDAO<ScoreRule, Integer> {

	ScoreRule getScoreRule(CountryEnum country, BigDecimal price);

	ScoreRule getScoreRule(CountryEnum country, String keyword);

	List<ScoreRule> getScoreRules(CountryEnum country);

}
