package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.ProductImageDao;
import com.nts.reserve.dto.ProductImage;
import com.nts.reserve.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {
	private final ProductImageDao productImageDao;

	@Autowired
	public ProductImageServiceImpl(ProductImageDao productImageDao) {
		this.productImageDao = productImageDao;
	}

	@Override
	public List<ProductImage> getProductImages(int displayInfoId) {
		return productImageDao.selectProductImages(displayInfoId);
	}

	@Override
	public int getProductImageCount(int displayInfoId) {
		return productImageDao.selectProductImageCount(displayInfoId);
	}

}
