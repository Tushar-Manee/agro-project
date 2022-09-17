package com.app.service;

import com.app.entities.Category;

public interface ICategoryService {
	Category createCategory(Category category,long adminId);
	
	
	
	String deleteCategory(long categoryId);

	Category updateCategory(Category detachedCategory, long adminId);
	
}
