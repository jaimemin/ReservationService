package com.nts.reserve.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.reserve.dto.Promotion;

public interface PromotionDao {
	List<Promotion> selectPromotions(String imageType);
}