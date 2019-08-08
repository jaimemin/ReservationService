package com.nts.reserve.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.ProductPrice;

@Repository
public interface ProductPriceDao {
	List<ProductPrice> selectProductPrices(int displayInfoId);
}
