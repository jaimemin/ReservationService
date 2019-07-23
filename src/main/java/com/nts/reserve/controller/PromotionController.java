package com.nts.reserve.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reserve.dto.Promotion;
import com.nts.reserve.service.PromotionService;

@RestController
@RequestMapping(path = "/api")
public class PromotionController {
	private PromotionService promotionService;
	
	@Autowired
	public PromotionController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	/*
	 * imageType
	 * th: thumbnail (썸네일 사진 - 상품리스트 혹은 프로모션 정보에서 보여주는 이미지) 
	 * ma: main (메인 사진 - 상품 상세정보에서 보여주는 이미지) 
	 * et: etc (기타 사진 - 상품 상세정보에서 추가적으로 보여주는 기타 이미지)
	 */
	@GetMapping("/promotions")
	public List<Promotion> getPromotionImages(){
		return promotionService.getPromotionImages("th");
	}
}
