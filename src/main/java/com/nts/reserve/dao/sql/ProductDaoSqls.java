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
				+ "WHERE category_id = :id";
	public static final String GET_COUNT_BY_CATEGORY_ID
		= "SELECT COUNT(*)"
				+ "FROM product"
				+ "where category_id = :categoryId";
	public static final String SELECT_PRODUCT_ITEMS
		= "SELECT product.id AS id,"
				+ "product.category_id AS categoryId,"
				+ "product.content AS content,"
				+ "product.description AS description,"
				+ "file_info.save_file_name AS saveFileName,"
				+ "display_info.place_name AS placeName"
				+ "FROM product"
				+ "INNER JOIN product_image"
				+ "ON product.id = product_image.product_id"
				+ "INNER JOIN file_info"
				+ "ON file_info.id = product_image.file_id"
				+ "INNER JOIN display_info"
				+ "ON product.id = display_info.product_id"
				+ "WHERE product_image.type = :imageType"
				+ "${dynamicQuery}" // if category is specific, categoryId
				+ "LIMIT :start, :count";
	public static final String SELECT_PRODUCT_PROMOTIONS
		= "SELECT product.id AS id,"
				+ "product_description AS description,"
				+ "promotion.id AS promotionId,"
				+ "category.id AS categoryId,"
				+ "category.name AS categoryName,"
				+ "product_image.id AS productImageId"
				+ "FROM product"
				+ "INNER JOIN promotion"
				+ "ON product.id = promotion.product_id"
				+ "INNER JOIN category"
				+ "ON category.id = product.category_id"
				+ "INNER JOIN product_image"
				+ "ON product.id = product_image.product_id"
				+ "AND type = :imageType";
}
	
