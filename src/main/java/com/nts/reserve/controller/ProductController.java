package com.nts.reserve.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reserve.dto.Product;
import com.nts.reserve.service.ProductService;

@RestController
@RequestMapping(path = "/api")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public Map<String, Object> getProducts(
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		List<Product> productList = productService.selectProductItems(categoryId, start);

		Map<String, Object> map = new HashMap<>();
		map.put("items", productList);

		return map;
	}
}
