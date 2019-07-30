package com.nts.reserve.dao;

import java.util.List;

import com.nts.reserve.dto.ProductImage;

public interface ProductImageDao {
	List<ProductImage> selectProductImages(int displayInfoId);
	
	int selectProductImageCount(int displayInfoId);
}
