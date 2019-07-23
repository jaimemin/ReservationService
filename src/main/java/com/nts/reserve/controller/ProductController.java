package com.nts.reserve.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reserve.dto.Category;
import com.nts.reserve.dto.Product;
import com.nts.reserve.service.CategoryService;
import com.nts.reserve.service.ProductService;

@RestController
@RequestMapping(path = "/api")
public class ProductController {
	private static final int LIMIT_COUNT = 4;
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products")
	public List<Product> getProductItems(
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "start", required = false, defaultValue = "0") int start) {
		return productService.getProductItems(categoryId, start, LIMIT_COUNT, "th");
	}
}
