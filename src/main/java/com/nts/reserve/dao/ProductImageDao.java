package com.nts.reserve.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.ProductImage;

@Repository
public interface ProductImageDao {
	List<ProductImage> selectProductImages(int displayInfoId);

	int selectProductImageCount(int displayInfoId);
}
