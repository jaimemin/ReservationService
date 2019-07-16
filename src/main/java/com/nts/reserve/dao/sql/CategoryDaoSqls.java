package com.nts.reserve.dao.sql;

public class CategoryDaoSqls {
	public static final String SELECT_ALL_CATEGORIES 
		= "SELECT category.id AS id," 
				+ "category.name AS name,"
				+ "COUNT(*) as count" 
				+ "FROM display_info" 
				+ "LEFT JOIN product" 
				+ "on product.id = display_info.product_id"
				+ "LEFT JOIN category" 
				+ "ON category.id = product.category_id " 
				+ "GROUP BY category.id";
}
