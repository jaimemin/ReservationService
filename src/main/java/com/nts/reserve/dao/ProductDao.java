package com.nts.reserve.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.Product;

@Repository
public interface ProductDao {
	int selectCategoryProductCount(@Param("categoryId") int categoryId, @Param("imageType") String imageType);

	List<Product> selectProducts(@Param("categoryId") int categoryId, @Param("start") int start,
			@Param("productCountLimit") int productCountLimit, @Param("imageType") String imageType);
}
