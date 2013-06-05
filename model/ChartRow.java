package net.gobro.plauen.model;

public class ChartRow {

	private String alias;

	private boolean bot;

	private Integer points;

	private Sms sms;

	private String username;

	public String getAlias() {
		return alias;
	}

	public Integer getPoints() {
		return points;
	}

	public Sms getSms() {
		return sms;
	}

	public String getUsername() {
		return username;
	}

	public boolean isBot() {
		return bot;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setBot(boolean bot) {
		this.bot = bot;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public void setSms(Sms sms) {
		this.sms = sms;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
