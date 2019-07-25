package com.nts.reserve.dao;

import java.util.List;

import com.nts.reserve.dto.Category;

public interface CategoryDao {
	List<Category> selectAllCategories();
}
