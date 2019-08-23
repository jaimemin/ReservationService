package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nts.reserve.dao.ProductDao;
import com.nts.reserve.dao.ProductImageDao;
import com.nts.reserve.dao.ProductPriceDao;
import com.nts.reserve.dto.Product;
import com.nts.reserve.dto.ProductImage;
import com.nts.reserve.dto.ProductPrice;
import com.nts.reserve.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static final int UPPER_LIMIT_COUNT = 4;
	private static final String THUMBNAIL = "th";
	private final ProductDao productDao;
	private final ProductPriceDao productPriceDao;
	private final ProductImageDao productImageDao;

	@Autowired
	public ProductServiceImpl(ProductDao productDao
			, ProductPriceDao productPriceDao
			, ProductImageDao productImageDao) {
		this.productDao = productDao;
		this.productPriceDao = productPriceDao;
		this.productImageDao = productImageDao;
	}

	@Override
	public int getCategoryProductCount(int categoryId) {
		return productDao.selectCategoryProductCount(categoryId, THUMBNAIL);
	}

	@Override
	public int getProductImageCount(int displayInfoId) {
		return productImageDao.selectProductImageCount(displayInfoId);
	}
	
	public List<Product> getProductInfos(int productId) {
		return productDao.selectProductInfos(productId);
	}
	
	@Override
	public Product getProduct(int productId) {
		return productDao.selectProduct(productId);
	}

	@Override
	public List<Product> getProducts(int categoryId, int start) {
		List<Product> products = productDao.selectProducts(categoryId, start, UPPER_LIMIT_COUNT, THUMBNAIL);
		
		if (CollectionUtils.isEmpty(products)) {
			throw new DataRetrievalFailureException(
				"The expedted data could not be retrieved. categoryId: " + categoryId);
		}

		return products;
	}

	@Override
	public List<ProductPrice> getProductPrices(int displayInfoId) {
		return productPriceDao.selectProductPrices(displayInfoId);
	}

	@Override
	public List<ProductImage> getProductImages(int displayInfoId) {
		return productImageDao.selectProductImages(displayInfoId);
	}

}
