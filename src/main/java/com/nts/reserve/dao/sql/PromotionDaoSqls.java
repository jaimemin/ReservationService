package com.nts.reserve.dao.sql;

public class PromotionDaoSqls {
	public static final String SELECT_ALL_PROMOTION 
		= "SELECT id, product_id FROM promotion";
	public static final String SELECT_PROMOTION_IMAGE 
		= "SELECT promotion.id AS id,"
			+ "promotion.product_id AS productId,"
			+ "display_info.id AS displayInfoId," 
			+ "file_info.save_file_name AS saveFileName" 
			+ "FROM promotion"
			+ "INNER JOIN product_image" 
			+ "ON promotion.product_id = product_image.product_id" 
			+ "INNER JOIN file_info"
			+ "ON file_info.id = product_image.file_id" 
			+ "INNER JOIN display_info"
			+ "ON promotion.product_id = display_info.product_id" 
			+ "WHERE product_image.type = :imageType";
}
