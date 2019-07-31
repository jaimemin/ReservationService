package com.nts.reserve.dto;

import java.util.List;

public class DisplayInfoResponse {
	private Double averageCommentScore;
	private List<Comment> comments;
	private DisplayInfo displayInfo;
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private List<ProductPrice> productPrices;

	public Double getAverageCommentScore() {
		return averageCommentScore;
	}

	public void setAverageCommentScore(Double averageCommentScore) {
		this.averageCommentScore = averageCommentScore;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public DisplayInfoImage getDisplayInfoImage() {
		return displayInfoImage;
	}

	public void setDisplayInfoImage(DisplayInfoImage displayInfoImage) {
		this.displayInfoImage = displayInfoImage;
	}

	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

	@Override
	public String toString() {
		return "DisplayInfoEntity [averageCommentScore=" + averageCommentScore + ", comments=" + comments + ", displayInfo="
				+ displayInfo + ", displayInfoImage=" + displayInfoImage + ", productImages=" + productImages
				+ ", productPrices=" + productPrices + "]";
	}

}
