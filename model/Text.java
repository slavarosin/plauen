package net.gobro.plauen.model;

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
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "texts")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Text {
	private TextCodeEnum code;
	private Integer id;
	private LanguageEnum language;
	private Present present;
	private String text;

	@NotNull
	@Column(length = 32, nullable = false)
	@Enumerated(EnumType.STRING)
	public TextCodeEnum getCode() {
		return code;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@NotNull
	@Column(length = 2, nullable = false)
	@Enumerated(EnumType.STRING)
	public LanguageEnum getLanguage() {
		return language;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "presents_id", nullable = false)
	public Present getPresent() {
		return present;
	}

	@NotNull
	@Column(nullable = false)
	public String getText() {
		return text;
	}

	public void setCode(TextCodeEnum code) {
		this.code = code;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}

	public void setPresent(Present present) {
		this.present = present;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}
}
