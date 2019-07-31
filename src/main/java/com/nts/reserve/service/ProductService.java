package com.nts.reserve.service;

import java.util.List;

import com.nts.reserve.dto.Product;
import com.nts.reserve.dto.ProductImage;
import com.nts.reserve.dto.ProductPrice;

public interface ProductService {
	int getCategoryProductCount(int categoryId);
	
	int getProductImageCount(int displayInfoId);

	List<Product> getProducts(int categoryId, int start);
	
	List<ProductImage> getProductImages(int displayInfoId);
	
	List<ProductPrice> getProductPrices(int displayInfoId);
}
