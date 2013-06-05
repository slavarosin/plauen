package net.gobro.plauen.web.beans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.gobro.plauen.model.Sms;
import net.gobro.plauen.model.UserPlay;

public class UserPoints {

	private UserPlay userPlay;

	private Integer points = 0;

	private Calendar lastPoints;

	private List<Sms> sms = new ArrayList<Sms>();

	public void addPoints(Integer points, Calendar timestamp) {
		this.points += points;
		if (this.lastPoints == null || this.lastPoints.before(timestamp)) {
			this.lastPoints = timestamp;
		}
	}

	public Calendar getLastPoints() {
		return lastPoints;
	}

	public Integer getPoints() {
		return points;
	}

	public List<Sms> getSms() {
		return sms;
	}

	public UserPlay getUserPlay() {
		return userPlay;
	}

	public void setLastPoints(Calendar lastPoints) {
		this.lastPoints = lastPoints;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public void setSms(List<Sms> sms) {
		this.sms = sms;
	}

	public void setUserPlay(UserPlay userPlay) {
		this.userPlay = userPlay;
	}
}
