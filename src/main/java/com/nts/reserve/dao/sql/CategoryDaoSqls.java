package com.nts.reserve.dao.sql;

public class CategoryDaoSqls {
	public static final String SELECT_ALL_CATEGORIES 
		= "SELECT category.id AS id," 
				+ "category.name AS name,"
				+ "COUNT(*) AS count" 
				+ "FROM display_info" 
				+ "INNER JOIN product" 
				+ "on product.id = display_info.product_id"
				+ "INNER JOIN category" 
				+ "ON id = product.category_id " 
				+ "GROUP BY category.id";
}
