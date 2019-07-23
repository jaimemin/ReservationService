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
	private ProductDao productDao;

	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public int getCategoryCount(int categoryId) {
		return productDao.selectProductCountByCategory(categoryId);
	}

	@Override
	public List<Product> getProductItems(int categoryId, int start) {
		return productDao.selectProductItems(categoryId, start);
	}

}
