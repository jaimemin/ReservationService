package com.nts.reserve.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reserve.dto.Category;
import com.nts.reserve.service.CategoryService;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

//	@GetMapping("/categories")
//	public Map<String, Object> getCategories() {
//		List<Category> categoryList = categoryService.getAllCategories();
//
//		Map<String, Object> map = new HashMap<>();
//		map.put("items", categoryList);
//
//		return map;
//	}
	
	@GetMapping("/categories")
	public List<Category> getCategories() {
		return categoryService.getAllCategories();
	}
}
