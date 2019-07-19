package com.nts.reserve.service;

import java.util.List;

import com.nts.reserve.dto.Product;

public interface ProductService {
	public int getCount(int categoryId);

	public List<Product> selectProductItems(int categoryId, int start);
}
