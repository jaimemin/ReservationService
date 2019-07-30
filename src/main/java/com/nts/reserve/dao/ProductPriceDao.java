package com.nts.reserve.dao;

import java.util.List;

import com.nts.reserve.dto.ProductPrice;

public interface ProductPriceDao {
	List<ProductPrice> selectProductPrices(int displayInfoId);
}
