package com.nts.reserve.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.Promotion;

@Repository
public interface PromotionDao {
	List<Promotion> selectPromotions(String imageType);
}