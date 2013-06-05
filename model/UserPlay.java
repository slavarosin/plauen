package net.gobro.plauen.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "user_plays")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserPlay {
	private String alias;
	private Game game;
	private Integer id;
	private List<Sms> sms;
	private User user;
	private boolean disabled;

	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof UserPlay) == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		UserPlay rhs = (UserPlay) obj;

		return new EqualsBuilder().append(id, rhs.id).isEquals();
	}

	@NotNull
	@Column(length = 6, nullable = false)
	@Length(max = 6)
	public String getAlias() {
		return alias;
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

	@OneToMany(mappedBy = "userPlay")
	public List<Sms> getSms() {
		return sms;
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

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSms(List<Sms> sms) {
		this.sms = sms;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isDisabled() {
		return disabled;
	}

}
