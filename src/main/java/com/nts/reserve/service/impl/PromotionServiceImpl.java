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
	private PromotionDao promotionDao;

	@Autowired
	public PromotionServiceImpl(PromotionDao promotionDao) {
		this.promotionDao = promotionDao;
	}
	
	@Override
	public List<Promotion> getPromotionImages() {
		return promotionDao.selectPromotionImages();
	}

}
