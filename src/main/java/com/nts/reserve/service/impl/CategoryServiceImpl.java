package com.nts.reserve.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reserve.dao.CategoryDao;
import com.nts.reserve.dao.ProductDao;
import com.nts.reserve.dto.Category;
import com.nts.reserve.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao;
	
	public CategoryServiceImpl(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.selectAllCategories();
	}

}
