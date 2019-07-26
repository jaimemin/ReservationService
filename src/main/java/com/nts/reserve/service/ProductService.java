package com.nts.reserve.service;

import java.util.HashMap;
import java.util.List;

import com.nts.reserve.dto.Product;

public interface ProductService {
	int getCategoryProductCount(int categoryId);

	List<Product> getProductItems(int categoryId, int start);
}
