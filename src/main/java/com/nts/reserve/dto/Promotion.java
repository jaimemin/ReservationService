package com.nts.reserve.dto;

public class Promotion {
	int id;
	int productId;
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

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", saveFileName=" + saveFileName + "]";
	}

}
