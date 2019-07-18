package com.nts.reserve.dto;

import java.time.LocalDateTime;

public class Product {
	int id;
	int categoryId;
	String description;
	String content;
	String event;
	LocalDateTime createdDate;
	LocalDateTime modifiedDate;
	// category
	String categoryName;
	// promotion
	int promotionId;
	// display_info
	int diplayInfoId;
	String placeName;
	// product_image
	int productImageId;
	// file_info
	String saveFileName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public int getDiplayInfoId() {
		return diplayInfoId;
	}

	public void setDiplayInfoId(int diplayInfoId) {
		this.diplayInfoId = diplayInfoId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public int getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", categoryId=" + categoryId + ", description=" + description + ", content="
				+ content + ", event=" + event + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", categoryName=" + categoryName + ", promotionId=" + promotionId + ", diplayInfoId=" + diplayInfoId
				+ ", placeName=" + placeName + ", productImageId=" + productImageId + ", saveFileName=" + saveFileName
				+ "]";
	}

}
