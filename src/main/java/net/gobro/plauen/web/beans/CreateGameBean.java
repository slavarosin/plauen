package net.gobro.plauen.web.beans;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import net.gobro.plauen.model.CountryEnum;

import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.list.LazyList;

@SuppressWarnings("unchecked")
public class CreateGameBean {

	private Integer id;

	private Calendar activateAt;

	private List<CountryBean> countries;

	private CountryEnum country;

	private List<String> description = LazyList.decorate(new LinkedList(),
			FactoryUtils.instantiateFactory(String.class));

	private byte[] image1;

	private byte[] image2;

	private byte[] image3;

	private List<Integer> images;

	private String internalDescription;

	private String internalName;

	private List<LanguageBean> languages;

	private List<String> name = LazyList.decorate(new LinkedList(),
			FactoryUtils.instantiateFactory(String.class));

	private BigDecimal price;

	private Calendar startedAt;

	public Calendar getActivateAt() {
		return activateAt;
	}

	public List<CountryBean> getCountries() {
		return countries;
	}

	public CountryEnum getCountry() {
		return country;
	}

	public List<String> getDescription() {
		return description;
	}

	public byte[] getImage1() {
		return image1;
	}

	public byte[] getImage2() {
		return image2;
	}

	public byte[] getImage3() {
		return image3;
	}

	public List<Integer> getImages() {
		return images;
	}

	public String getInternalDescription() {
		return internalDescription;
	}

	public String getInternalName() {
		return internalName;
	}

	public List<LanguageBean> getLanguages() {
		return languages;
	}

	public List<String> getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Calendar getStartedAt() {
		return startedAt;
	}

	public void setActivateAt(Calendar activatedAt) {
		this.activateAt = activatedAt;
	}

	public void setCountries(List<CountryBean> countries) {
		this.countries = countries;
	}

	public void setCountry(CountryEnum country) {
		this.country = country;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}

	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}

	public void setImages(List<Integer> images) {
		this.images = images;
	}

	public void setInternalDescription(String internalDescription) {
		this.internalDescription = internalDescription;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}

	public void setLanguages(List<LanguageBean> languages) {
		this.languages = languages;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setStartedAt(Calendar startedAt) {
		this.startedAt = startedAt;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
