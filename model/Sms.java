package net.gobro.plauen.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "sms")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sms {

	private CountryEnum country;

	private CurrencyEnum currency;

	private Integer id;

	private String keyword;

	private String message;

	private String messageId;

	private BigDecimal price;

	private String sender;

	private String serviceId;

	private String shortCode;

	private StatusEnum status;

	private Calendar timestamp;

	private UserPlay userPlay;

	private boolean disabled;

	@Column(length = 2, nullable = false)
	@Enumerated(EnumType.STRING)
	public CountryEnum getCountry() {
		return country;
	}

	@Column(length = 3, nullable = false)
	@Enumerated(EnumType.STRING)
	public CurrencyEnum getCurrency() {
		return currency;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@Column(length = 12, nullable = false)
	@Length(max = 12)
	public String getKeyword() {
		return keyword;
	}

	@Column(length = 64, nullable = false)
	@Length(max = 64)
	public String getMessage() {
		return message;
	}

	@Column(name = "message_id", nullable = false)
	public String getMessageId() {
		return messageId;
	}

	@Column(nullable = false)
	public BigDecimal getPrice() {
		return price;
	}

	@Column(length = 32, nullable = false)
	@Length(max = 32)
	public String getSender() {
		return sender;
	}

	@Column(name = "service_id", length = 32, nullable = false)
	@Length(max = 32)
	public String getServiceId() {
		return serviceId;
	}

	@Column(name = "shortcode", length = 5, nullable = false)
	@Length(max = 5)
	public String getShortCode() {
		return shortCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "msg_status", nullable = false)
	public StatusEnum getStatus() {
		return status;
	}

	@NotNull
	@Column(nullable = false)
	public Calendar getTimestamp() {
		return timestamp;
	}

	@ManyToOne
	@JoinColumn(name = "user_plays_id", nullable = false)
	public UserPlay getUserPlay() {
		return userPlay;
	}

	@NotNull
	@Column(nullable = false)
	public boolean isDisabled() {
		return disabled;
	}

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	public void setUserPlay(UserPlay userPlay) {
		this.userPlay = userPlay;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append(
				"country", getCountry()).append("currency", getCurrency())
				.append("keyword", getKeyword())
				.append("message", getMessage()).append("messageId",
						getMessageId()).append("price", getPrice()).append(
						"sender", getSender()).append("serviceId",
						getServiceId()).append("shortCode", getShortCode())
				.append("status", getStatus()).toString();
	}
}
