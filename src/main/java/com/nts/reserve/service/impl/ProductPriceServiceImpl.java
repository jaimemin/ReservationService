package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.ProductPriceDao;
import com.nts.reserve.dto.ProductPrice;
import com.nts.reserve.service.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
	private final ProductPriceDao productPriceDao;

	@Autowired
	public ProductPriceServiceImpl(ProductPriceDao productPriceDao) {
		this.productPriceDao = productPriceDao;
	}

	@Override
	public List<ProductPrice> getProductPrices(int displayInfoId) {
		return productPriceDao.selectProductPrices(displayInfoId);
	}

}
