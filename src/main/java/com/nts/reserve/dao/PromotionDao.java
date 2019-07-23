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
	private static final String SELECT_PROMOTION_IMAGE 
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

	/*
	 * imageType
	 * th: thumbnail (썸네일 사진 - 상품리스트 혹은 프로모션 정보에서 보여주는 이미지) 
	 * ma: main (메인 사진 - 상품 상세정보에서 보여주는 이미지) 
	 * et: etc (기타 사진 - 상품 상세정보에서 추가적으로 보여주는 기타 이미지)
	 */
	public List<Promotion> selectPromotionImages() {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("imageType", "th");
		
		return jdbcTemplate.query(SELECT_PROMOTION_IMAGE, parameter, rowMapper);
	}
}
