package net.gobro.plauen.web.beans;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Transient;

import net.gobro.plauen.model.UserPlay;

public class GameBean {

	private Calendar finishedAt;

	private boolean biddingOpened;

	private String description;

	private String shortDescription;

	private boolean gameClosed;

	private Integer id;

	private List<Integer> images;

	private String internalDescription;

	private String internalName;

	private String members;

	private String name;

	private BigDecimal price;

	protected boolean registrationOpened;

	private Calendar startedAt;

	private UserPlay userPlay;

	private Integer userPosition;

	public String getDescription() {
		return description;
	}

	public Integer getId() {
		return id;
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

	public String getMembers() {
		return members;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Calendar getStartedAt() {
		return startedAt;
	}

	public UserPlay getUserPlay() {
		return userPlay;
	}

	public Integer getUserPosition() {
		return userPosition;
	}

	@Transient
	public boolean isBiddingOpened() {
		return biddingOpened;
	}

	@Transient
	public boolean isGameClosed() {
		return gameClosed;
	}

	public boolean isRegistrationOpened() {
		return registrationOpened;
	}

	public Calendar getFinishedAt() {
		return finishedAt;
	}

	public void setFinishedAt(Calendar finishedAt) {
		this.finishedAt = finishedAt;
	}

	public void setBiddingOpened(boolean biddingOpened) {
		this.biddingOpened = biddingOpened;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGameClosed(boolean gameClosed) {
		this.gameClosed = gameClosed;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setMembers(String members) {
		this.members = members;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setRegistrationOpened(boolean registrationOpened) {
		this.registrationOpened = registrationOpened;
	}

	public void setStartedAt(Calendar startedAt) {
		this.startedAt = startedAt;
	}

	public void setUserPlay(UserPlay userPlay) {
		this.userPlay = userPlay;
	}

	public void setUserPosition(Integer userPosition) {
		this.userPosition = userPosition;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}
