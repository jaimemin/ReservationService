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
import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.Promotion;

@Repository
public class PromotionDao {
	private static final String SELECT_PROMOTION_IMAGES 
		= "SELECT promotion.id AS id,"
				+ " promotion.product_id AS productId,"
				+ " file_info.save_file_name AS saveFileName" 
				+ " FROM promotion"
				+ " INNER JOIN product_image" 
				+ " ON promotion.product_id = product_image.product_id" 
				+ " INNER JOIN file_info"
				+ " ON file_info.id = product_image.file_id" 
				+ " WHERE product_image.type = :imageType";
	private NamedParameterJdbcTemplate jdbcTemplate;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	@Autowired
	public PromotionDao(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Promotion> selectPromotionImages(String imageType) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("imageType", imageType);
		
		return jdbcTemplate.query(SELECT_PROMOTION_IMAGES, parameter, rowMapper);
	}
}
