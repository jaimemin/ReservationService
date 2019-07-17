package com.nts.reserve.dao.sql;

public class ProductDaoSqls {
	public static final String SELECT_BY_ID
		= "SELECT id,"
				+ "category_id AS categoryId,"
				+ "content,"
				+ "event,"
				+ "create_date AS createdDate,"
				+ "modify_date AS modifiedDate"
				+ "FROM product"
				+ "WHERE id = :id";
	public static final String GET_COUNT_BY_CATEGORY_ID
		= "SELECT COUNT(*)"
				+ "FROM product"
				+ "where category_id = :categoryId";
}
	
