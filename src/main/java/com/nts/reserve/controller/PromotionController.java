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

	@Autowired
	private PromotionService promotionService;

	@GetMapping("/promotions")
	public Map<String, Object> promotions() {
		List<Promotion> imageList = promotionService.getPromotionImages();

		Map<String, Object> map = new HashMap<>();
		map.put("items", imageList);

		return map;
	}
}
