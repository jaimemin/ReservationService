package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.ProductDao;
import com.nts.reserve.dto.Product;
import com.nts.reserve.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static final int UPPER_LIMIT_COUNT = 4;
	private static final String THUMB_NAIL = "th";
	private ProductDao productDao;

	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public int getCategoryProductCount(int categoryId) {
		return productDao.selectCategoryProductCount(categoryId, THUMB_NAIL);
	}

	@Override
	public List<Product> getProducts(int categoryId, int start) {
		return productDao.selectProducts(categoryId, start, UPPER_LIMIT_COUNT, THUMB_NAIL);
	}

}
