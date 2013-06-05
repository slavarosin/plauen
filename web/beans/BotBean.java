package net.gobro.plauen.web.beans;

import java.util.List;

import net.gobro.plauen.model.User;


public class BotBean {

	private Integer botId;

	private String botName;

	private List<User> bots;

	private Integer gameId;

	public Integer getBotId() {
		return botId;
	}

	public String getBotName() {
		return botName;
	}

	public List<User> getBots() {
		return bots;
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setBotId(Integer botId) {
		this.botId = botId;
	}

	public void setBotName(String botName) {
		this.botName = botName;
	}

	public void setBots(List<User> bots) {
		this.bots = bots;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
}
