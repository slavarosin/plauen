package net.gobro.plauen.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "games")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Game {

	private Calendar activateAt;

	private boolean biddingOpened;

	private CountryEnum country;

	private Calendar finishedAt;

	private boolean gameClosed;

	private Integer id;

	private Present present;

	private boolean registrationOpened;

	private Calendar startedAt;

	private List<UserPlay> userPlays;

	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof Game) == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		Game rhs = (Game) obj;

		return new EqualsBuilder().append(id, rhs.id).isEquals();
	}

	@NotNull
	@Column(name = "activation_time", nullable = false)
	public Calendar getActivateAt() {
		return activateAt;
	}

	@NotNull
	@Column(length = 2, nullable = false)
	@Enumerated(EnumType.STRING)
	public CountryEnum getCountry() {
		return country;
	}

	@NotNull
	@Column(name = "end_time", nullable = false)
	public Calendar getFinishedAt() {
		return finishedAt;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "presents_id", nullable = false)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public Present getPresent() {
		return present;
	}

	@NotNull
	@Column(name = "start_time", nullable = false)
	public Calendar getStartedAt() {
		return startedAt;
	}

	@OneToMany(mappedBy = "game")
	public List<UserPlay> getUserPlays() {
		return userPlays;
	}

	@Override
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(17, 37).append(id).toHashCode();
	}

	@Transient
	public boolean isBiddingOpened() {
		return biddingOpened;
	}

	@Transient
	public boolean isGameClosed() {
		return gameClosed;
	}

	@Transient
	public boolean isRegistrationOpened() {
		return registrationOpened;
	}

	public void setActivateAt(Calendar activateAt) {
		this.activateAt = activateAt;
	}

	public void setBiddingOpened(boolean biddingOpened) {
		this.biddingOpened = biddingOpened;
	}

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public void setFinishedAt(Calendar finishedAt) {
		this.finishedAt = finishedAt;
	}

	public void setGameClosed(boolean gameClosed) {
		this.gameClosed = gameClosed;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPresent(Present present) {
		this.present = present;
	}

	public void setRegistrationOpened(boolean registrationOpened) {
		this.registrationOpened = registrationOpened;
	}

	public void setStartedAt(Calendar startedAt) {
		this.startedAt = startedAt;
	}

	public void setUserPlays(List<UserPlay> userPlays) {
		this.userPlays = userPlays;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append(
				"country", getCountry()).append("present", getPresent())
				.toString();
	}
}
