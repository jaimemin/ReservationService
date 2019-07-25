package com.nts.reserve.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.reserve.dto.Product;

public interface ProductDao {
	int selectCategoryProductCount(@Param("categoryId") int categoryId, @Param("imageType") String imageType);

	List<Product> selectProductItems(@Param("categoryId") int categoryId, @Param("start") int start,
			@Param("productCountLimit") int productCountLimit, @Param("imageType") String imageType);
}
