package com.nts.reserve.dao;

import static com.nts.reserve.dao.sql.PromotionDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.nts.reserve.dto.Promotion;

public class PromotionDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Promotion> rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);

	public PromotionDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Promotion> selectAll() {
		return jdbc.query(SELECT_ALL_PROMOTION, rowMapper);	
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
		return jdbc.query(SELECT_PROMOTION_IMAGE, parameter, rowMapper);
	}
}
