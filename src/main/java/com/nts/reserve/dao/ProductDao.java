package com.nts.reserve.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.Product;

@Repository
public class ProductDao {
	private static final int LIMIT_COUNT = 4;
	private static final String GET_COUNT_BY_CATEGORY_ID
		= "SELECT COUNT(*) "
				+ " FROM product"
				+ " INNER JOIN display_info" 
				+ " ON product.id = display_info.product_id"
				+ " INNER JOIN product_image"
				+ "	ON product.id = product_image.product_id"
				+ " INNER JOIN file_info"
				+ " ON product_image.file_id = file_info.id"
				+ " AND product_image.type = :imageType"
				+ " AND product.category_id = :categoryId";
	private static final String SELECT_PRODUCT_ITEMS
		= "SELECT product.id AS id,"
			+ " product.category_id AS categoryId,"
			+ " product.content AS content,"
			+ " product.description AS description,"
			+ " file_info.save_file_name AS saveFileName,"
			+ " display_info.place_name AS placeName"
			+ " FROM product"
			+ " INNER JOIN product_image"
			+ " ON product.id = product_image.product_id"
			+ " INNER JOIN file_info"
			+ " ON file_info.id = product_image.file_id"
			+ " INNER JOIN display_info"
			+ " ON product.id = display_info.product_id"
			+ " WHERE product_image.type = :imageType"
			+ " ${dynamicQuery}" // if category is specific, categoryId
			+ " LIMIT :start, :count";
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	@Autowired
	public ProductDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public int selectProductCountByCategory(int categoryId) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("imageType", "th");
		parameter.put("categoryId", categoryId);
		
		return jdbc.queryForObject(GET_COUNT_BY_CATEGORY_ID, parameter, Integer.class);
	}

	public List<Product> selectProductItems(int categoryId, int start) {
		Map<String, Object> parameter = new HashMap<>();
		String dynamicQuery = "";
		
		if(categoryId >= 1) {
			dynamicQuery = "AND product.category_id = :categoryId";
			parameter.put("categoryId", categoryId);
		}
		
		String sql = SELECT_PRODUCT_ITEMS.replace("${dynamicQuery}", dynamicQuery);
		parameter.put("start", start);
		parameter.put("count", LIMIT_COUNT);
		parameter.put("imageType", "th");
		
		return jdbc.query(sql, parameter, rowMapper);
	}
	
}
