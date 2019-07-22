package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.ProductDao;
import com.nts.reserve.dto.Product;
import com.nts.reserve.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	public int getCategoryCount() {
		return productDao.getProductCountByCategory();
	}

	@Override
	public List<Product> selectProductItems(int categoryId, int start) {
		return productDao.selectProductItems(categoryId, start);
	}

}
