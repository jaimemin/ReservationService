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
	public int getCategoryCount(int categoryId, String imageType) {
		return productDao.selectProductCountByCategory(categoryId, imageType);
	}

	
	@Override
	public List<Product> getProductItems(int categoryId, int start, int limitCount, String imageType) {
		return productDao.selectProductItems(categoryId, start, limitCount, imageType);
	}

}
