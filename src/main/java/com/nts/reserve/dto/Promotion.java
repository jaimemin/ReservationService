package com.nts.reserve.dto;

public class Promotion {
	int id;
	int productId;

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

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + "]";
	}

}
