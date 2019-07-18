package com.nts.reserve.dao;

import static com.nts.reserve.dao.sql.ProductDaoSqls.*;

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
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public Product selectProduct(int productId) {
		Map<String, Object> parameter = Collections.singletonMap("id", productId);
		
		return jdbc.queryForObject(SELECT_BY_ID, parameter, rowMapper);
	}

	public int getCount(int categoryId) {
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
	
	public List<Product> selectProductPromotions(){
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("imageType", "th");
		
		return jdbc.query(SELECT_PRODUCT_PROMOTIONS,  parameter, rowMapper);
	}
}
