package com.nts.reserve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reserve.dao.CategoryDao;
import com.nts.reserve.dto.Category;
import com.nts.reserve.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao;

	@Autowired
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> getCategories() {
		return categoryDao.selectCategories();
	}

}
