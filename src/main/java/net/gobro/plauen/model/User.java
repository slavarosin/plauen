package net.gobro.plauen.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "users")
@Transactional
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User implements Serializable {

	private static final long serialVersionUID = -6469422485711130258L;

	private String address;

	private String city;

	private CountryEnum country;

	private String email;

	private String firstName;

	private Integer id;

	private String lastName;

	private Calendar loggedIn;

	private String login;

	private int notifications;

	private String passwd;

	private String phoneNumber;

	private RoleEnum role;

	private Calendar signedUp;

	private String zip;

	private List<UserPlay> userPlays;

	public boolean equals(Object obj) {
		if (obj instanceof User == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		User rhs = (User) obj;

		return new EqualsBuilder().append(id, rhs.id).isEquals();
	}

	@NotNull
	@Column(length = 32, nullable = false)
	@Length(max = 32)
	public String getAddress() {
		return address;
	}

	@NotNull
	@Column(length = 32, nullable = false)
	@Length(max = 32)
	public String getCity() {
		return city;
	}

	@NotNull
	@Column(length = 2, nullable = false)
	@Enumerated(EnumType.STRING)
	public CountryEnum getCountry() {
		return country;
	}

	@NotNull
	@Column(length = 64, nullable = false)
	@Length(max = 64)
	@Email
	public String getEmail() {
		return email;
	}

	@NotNull
	@Column(name = "first_name", length = 32, nullable = false)
	@Length(max = 32)
	public String getFirstName() {
		return firstName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@NotNull
	@Column(name = "last_name", length = 32, nullable = false)
	@Length(max = 32)
	public String getLastName() {
		return lastName;
	}

	@NotNull
	@Column(name = "logged_in", nullable = false)
	public Calendar getLoggedIn() {
		return loggedIn;
	}

	@NotNull
	@Column(length = 32, nullable = false)
	@Length(max = 32)
	public String getLogin() {
		return login;
	}

	public int getNotifications() {
		return notifications;
	}

	@NotNull
	@Column(length = 32, nullable = false)
	@Length(max = 32)
	public String getPasswd() {
		return passwd;
	}

	@NotNull
	@Column(name = "phone", length = 32, nullable = false)
	@Length(max = 32)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	public RoleEnum getRole() {
		return role;
	}

	@NotNull
	@Column(name = "signed_up", nullable = false)
	public Calendar getSignedUp() {
		return signedUp;
	}

	@NotNull
	@Column(length = 32, nullable = false)
	@Length(max = 32)
	public String getZip() {
		return zip;
	}

	@OneToMany(mappedBy = "user")
	public List<UserPlay> getUserPlays() {
		return userPlays;
	}

	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(17, 37).append(id).toHashCode();
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLoggedIn(Calendar loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setNotifications(int notifications) {
		this.notifications = notifications;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public void setSignedUp(Calendar signedUp) {
		this.signedUp = signedUp;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setUserPlays(List<UserPlay> userPlays) {
		this.userPlays = userPlays;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append("login",
				getLogin()).append("firstName", getFirstName()).append(
				"lastName", getLastName()).append("phoneNumber",
				getPhoneNumber()).append("role", getRole()).toString();
	}
}
