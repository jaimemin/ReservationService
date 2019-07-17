package com.nts.reserve.dao;

import static com.nts.reserve.dao.sql.ProductDaoSqls.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.nts.reserve.dto.Product;

public class ProductDao {
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

}
