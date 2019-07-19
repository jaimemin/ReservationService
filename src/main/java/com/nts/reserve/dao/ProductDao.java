package com.nts.reserve.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.Product;

@Repository
public class ProductDao {
	private static final int LIMIT_COUNT = 4;
	private static final String SELECT_BY_ID
		= "SELECT id,"
			+ " category_id AS categoryId,"
			+ " content,"
			+ " event,"
			+ " create_date AS createdDate,"
			+ " modify_date AS modifiedDate"
			+ " FROM product"
			+ " WHERE category_id = :id";
	private static final String GET_COUNT_BY_CATEGORY_ID
		= "SELECT COUNT(*)"
			+ " FROM product"
			+ " where category_id = :categoryId";
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

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int getProductCountByCategory(int categoryId) {
		Map<String, Object> parameter = Collections.singletonMap("categoryId", categoryId);
		
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
