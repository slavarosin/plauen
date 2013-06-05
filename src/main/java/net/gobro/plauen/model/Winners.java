package net.gobro.plauen.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "winners")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Winners {

	private Game game;
	private Integer id;
	private User user;
	private Calendar timestamp;

	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof Winners) == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		Winners rhs = (Winners) obj;

		return new EqualsBuilder().append(id, rhs.id).isEquals();
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "games_id", nullable = false)
	public Game getGame() {
		return game;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "users_id", nullable = false)
	public User getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(17, 37).append(id).toHashCode();
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	@NotNull
	@Column(nullable = false)
	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

}
