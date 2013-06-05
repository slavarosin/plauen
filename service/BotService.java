package net.gobro.plauen.service;

import java.util.List;

import net.gobro.plauen.model.ScoreRule;

public interface BotService {

	void addBot(String name, Integer botId, Integer gameId);

	void addBots(Integer gameId);

	void addRandomBot();

	void addRandomPoints(Integer gameId, List<ScoreRule> scoreRules);

	void addRandomPointsToRandomOne();

	void addRandomPointsOnFinish();

	void addRandomPointsToRandomOne(Integer gameId, List<ScoreRule> scoreRules);

}
