package com.nts.reserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reserve.dto.Promotion;
import com.nts.reserve.service.PromotionService;

@RestController
@RequestMapping(path = "/api")
public class PromotionController {
	private final PromotionService promotionService;

	@Autowired
	public PromotionController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@GetMapping("/promotions")
	public List<Promotion> getPromotions() {
		return promotionService.getPromotions();
	}
}
