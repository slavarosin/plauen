package net.gobro.plauen.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "presents")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Present {
	private String comment;

	private Integer id;

	private List<Image> images;

	private String name;

	private BigDecimal price;

	private List<Text> texts;

	@NotNull
	@Column(name = "comments", length = 32, nullable = false)
	@Length(max = 32)
	public String getComment() {
		return comment;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	@OneToMany(mappedBy = "present", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public List<Image> getImages() {
		return images;
	}

	@NotNull
	@Column(length = 32, nullable = false)
	@Length(max = 32)
	public String getName() {
		return name;
	}

	@NotNull
	@Column(nullable = false)
	public BigDecimal getPrice() {
		return price;
	}

	@NotNull
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "present", cascade = CascadeType.ALL)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	public List<Text> getTexts() {
		return texts;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setTexts(List<Text> texts) {
		this.texts = texts;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append("name",
				getName()).toString();
	}

}
