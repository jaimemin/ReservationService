package com.nts.reserve.service.impl;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reserve.dto.Promotion;
import com.nts.reserve.dao.PromotionDao;
import com.nts.reserve.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	private static final String THUMB_NAIL = "th";
	private PromotionDao promotionDao;

	@Autowired
	public PromotionServiceImpl(PromotionDao promotionDao) {
		this.promotionDao = promotionDao;
	}

	/*
	 * imageType
	 * th: thumbnail (썸네일 사진 - 상품리스트 혹은 프로모션 정보에서 보여주는 이미지) 
	 * ma: main (메인 사진 - 상품 상세정보에서 보여주는 이미지) 
	 * et: etc (기타 사진 - 상품 상세정보에서 추가적으로 보여주는 기타 이미지)
	 */
	@Override
	public List<Promotion> getPromotionImages() {
		return promotionDao.selectPromotionImages(THUMB_NAIL);
	}

}
