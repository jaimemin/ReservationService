package com.nts.reserve.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reserve.service.CategoryService;
import com.nts.reserve.service.ProductService;
import com.nts.reserve.service.PromotionService;

@RestController
@RequestMapping(path = "api")
public class RestApiController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	PromotionService promotionService;

	@GetMapping("/categories")
	public String getCategories() {
		return "{\"category-list\": " 
				+ categoryService.getAllCategories() 
				+ "}";
	}

	@GetMapping("/products")
	public String getProducts(@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
			return "{\"num-of-category\": " 
					+ productService.getCount(categoryId) 
					+ ",\"product-list\": " 
					+ productService.selectProductItems(categoryId, start) 
					+ "}";
	}

	@GetMapping("/promotions")
	public String getPromotion() {
		return "{\"promotion-image-list\": " 
				+ promotionService.getPromotionImages() 
				+ "}";
	}
}
