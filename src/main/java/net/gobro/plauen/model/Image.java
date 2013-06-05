package net.gobro.plauen.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "images")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Image {
	private Integer id;

	private byte[] image;

	private Present present;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public byte[] getImage() {
		return image;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "presents_id", nullable = false)
	public Present getPresent() {
		return present;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setPresent(Present present) {
		this.present = present;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
