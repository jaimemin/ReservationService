package com.nts.reserve.service.implementation;

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
public class CategoryServiceImplementation implements CategoryService {
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.selectAll();
	}

}
