package com.nts.reserve.dto;

import java.time.LocalDateTime;

public class DisplayInfo {
	int id;
	int productId;
	String openingHours;
	String placeName;
	String placeLot;
	String placeStreet;
	String telephone;
	String homepage;
	String email;
	LocalDateTime createdDate;
	LocalDateTime modifiedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceLot() {
		return placeLot;
	}

	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "DisplayInfo [id=" + id + ", productId=" + productId + ", openingHours=" + openingHours + ", placeName="
				+ placeName + ", placeLot=" + placeLot + ", placeStreet=" + placeStreet + ", telephone=" + telephone
				+ ", homepage=" + homepage + ", email=" + email + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + "]";
	}

}
