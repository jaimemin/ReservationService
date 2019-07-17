package com.nts.reserve.dto;

public class Promotion {
	int id;
	int productId;
	// display_info
	int displayInfoId;
	// file_info
	String saveFileName;

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

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
				+ ", saveFileName=" + saveFileName + "]";
	}

}
