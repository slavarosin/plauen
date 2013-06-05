package net.gobro.plauen.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "score_rules")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ScoreRule {

	private CountryEnum country;
	private CurrencyEnum currency;
	private Integer id;
	private String keyword;
	private BigDecimal price;
	private Integer score;
	private String serviceId;
	private String shortCode;

	@NotNull
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

	@Column(length = 12, nullable = true)
	@Length(max = 12)
	public String getKeyword() {
		return keyword;
	}

	@Column(nullable = true)
	public BigDecimal getPrice() {
		return price;
	}

	@NotNull
	@Column(nullable = false)
	public Integer getScore() {
		return score;
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

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public void setCurrency(CurrencyEnum currency) {
		this.currency = currency;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setKeyword(String name) {
		this.keyword = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setScore(Integer price) {
		this.score = price;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
