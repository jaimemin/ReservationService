package com.nts.reserve.service;

import java.util.List;

import com.nts.reserve.dto.ProductImage;

public interface ProductImageService {
	List<ProductImage> getProductImages(int displayInfoId);

	int getProductImageCount(int displayInfoId);
}
