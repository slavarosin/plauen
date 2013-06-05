package net.gobro.plauen.dao.criteria;

import net.gobro.plauen.model.CountryEnum;

public class GameCriteria {
	private CountryEnum country;
	private DateRange playPeriod;
	private DateRange registrationPeriod;

	public CountryEnum getCountry() {
		return country;
	}

	public DateRange getPlayPeriod() {
		return playPeriod;
	}

	public DateRange getRegistrationPeriod() {
		return registrationPeriod;
	}

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public void setPlayPeriod(DateRange playPeriod) {
		this.playPeriod = playPeriod;
	}

	public void setRegistrationPeriod(DateRange registrationPeriod) {
		this.registrationPeriod = registrationPeriod;
	}

}
