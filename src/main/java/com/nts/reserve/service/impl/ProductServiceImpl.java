package com.nts.reserve.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.ProductDao;
import com.nts.reserve.dto.Product;
import com.nts.reserve.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static final int LIMIT_COUNT = 4;
	private ProductDao productDao;

	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public int getProductCountByCategory(int categoryId) {
		return productDao.selectProductCountByCategory(categoryId, "th");
	}

	
	@Override
	public List<Product> getProductItems(int categoryId, int start) {
		return productDao.selectProductItems(categoryId, start, LIMIT_COUNT, "th");
	}

}
