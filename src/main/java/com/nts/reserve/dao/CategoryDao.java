package com.nts.reserve.dao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.Category;

@Repository
public class CategoryDao {
	private static final String SELECT_ALL_CATEGORIES 
		= "SELECT category.id AS id," 
			+ " category.name AS name,"
			+ " COUNT(*) AS count" 
			+ " FROM display_info" 
			+ " INNER JOIN product" 
			+ " ON product.id = display_info.product_id"
			+ " INNER JOIN category" 
			+ " ON category.id = product.category_id " 
			+ " GROUP BY category.id";
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	@Autowired
	public CategoryDao(NamedParameterJdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public List<Category> selectAllCategories() {
		return jdbc.query(SELECT_ALL_CATEGORIES, rowMapper);
	}
}
