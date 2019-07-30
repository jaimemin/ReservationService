package com.nts.reserve.service;

import java.util.List;

import com.nts.reserve.dto.ProductPrice;

public interface ProductPriceService {
	List<ProductPrice> getProductPrices(int displayInfoId);
}
