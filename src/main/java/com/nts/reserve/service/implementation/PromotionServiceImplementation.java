package com.nts.reserve.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dto.Promotion;
import com.nts.reserve.dao.PromotionDao;
import com.nts.reserve.service.PromotionService;

@Service
public class PromotionServiceImplementation implements PromotionService {

	@Autowired
	PromotionDao promotionDao;

	@Override
	public List<Promotion> getAllPromotions() {
		return promotionDao.selectAll();
	}

	@Override
	public List<Promotion> getPromotionImages() {
		return promotionDao.selectPromotionImages();
	}

}
