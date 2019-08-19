package com.nts.reserve.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationInfo {
	private int id;
	private DisplayInfo displayInfo;
	private int displayInfoId;
	private int productId;
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private int cancelFlag;
	@JsonFormat(pattern = "yyyy. MM. dd.")
	private LocalDate reservationDate;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private int totalPrice;
	private List<ReservationPrice> reservationPrices;

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

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}
	
	public String getReservationDateView() {
		return reservationDate.format(DateTimeFormatter.ofPattern("yyyy. MM. dd."));
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
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

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<ReservationPrice> getReservationPrices() {
		return reservationPrices;
	}

	public void setReservationPrices(List<ReservationPrice> reservationInfoPrices) {
		this.reservationPrices = reservationInfoPrices;
	}

	@Override
	public String toString() {
		return "ReservationInfo [id=" + id + ", productId=" + productId + ", displayInfo=" + displayInfo
				+ ", displayInfoId=" + displayInfoId + ", reservationName=" + reservationName
				+ ", reservationTelephone=" + reservationTelephone + ", reservationEmail=" + reservationEmail
				+ ", reservationDate=" + reservationDate + ", cancelFlag=" + cancelFlag + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", totalPrice=" + totalPrice + ", reservationPrices="
				+ reservationPrices + "]";
	}

}
