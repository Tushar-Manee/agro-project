package com.app.service;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.CategoryRepository;
import com.app.entities.Admin;
import com.app.entities.Category;

@Service
@Transactional

public class CategoryServiceImpl implements ICategoryService {
	@Autowired
	private CategoryRepository catgRepo;
	
	@Autowired
	private IAdminService adminService;

	@Override
	public Category createCategory(Category transientCategory,long adminId) {
		Admin admin=adminService.findAdminById(adminId);
		transientCategory.setAdmin(admin);
		return catgRepo.save(transientCategory);
	}

	@Override
	public Category updateCategory(Category detachedCategory,long adminId) {
		
		Admin admin=adminService.findAdminById(adminId);
		
		System.out.println("in service admin id = " + adminId);
		
		if (catgRepo.existsByCategoryId(detachedCategory.getCategoryId())) {
			Category updatedCategory = catgRepo.save(detachedCategory);
			updatedCategory.setAdmin(admin);
			
			return updatedCategory;
		}
		throw new ResourceNotFoundException("Invalid Category ID : Updation Failed !!!!!!!!!" + detachedCategory.getCategoryId());
	}

	@Override
	public String deleteCategory(long categoryId) {
		catgRepo.deleteByCategoryId(categoryId);
		return "Category details deleted for category id " + categoryId;
	}
	
}
