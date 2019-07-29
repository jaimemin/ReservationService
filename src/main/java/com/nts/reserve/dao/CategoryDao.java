package com.nts.reserve.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.reserve.dto.Category;

@Repository
public interface CategoryDao {
	List<Category> selectCategories();
}
