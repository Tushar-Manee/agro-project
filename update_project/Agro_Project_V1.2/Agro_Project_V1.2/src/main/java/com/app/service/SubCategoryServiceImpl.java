package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.CategoryRepository;
import com.app.dao.SubCategoryRepository;
import com.app.dto.SubCategoryRequest;
import com.app.entities.Admin;
import com.app.entities.Category;
import com.app.entities.SubCategory;

@Service
@Transactional
public class SubCategoryServiceImpl implements ISubCategoryService {
	@Autowired
	private SubCategoryRepository subCatgRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private IAdminService adminService;

	@Override
	public SubCategory createSubCategory(SubCategoryRequest subCategoryRequest, long adminId) {

		Admin admin = adminService.findAdminById(adminId);
		SubCategory transientSubCategory = mapper.map(subCategoryRequest, SubCategory.class);

		if (categoryRepo.existsByCategoryId(subCategoryRequest.getCategoryId())) {
			Category category = categoryRepo.findByCategoryId(subCategoryRequest.getCategoryId());
			transientSubCategory.setCategory(category);
			transientSubCategory.setAdmin(admin);
			return subCatgRepo.save(transientSubCategory);
		} else
			throw new ResourceNotFoundException(
					"Category does exist with Category name" + transientSubCategory.getCategory().getCatgName());
	}

	@Override
	public SubCategory updateSubCategory(SubCategory detachedSubCategory,long adminId) {

		Admin admin = adminService.findAdminById(adminId);
		if (subCatgRepo.existsBySubCatgId(detachedSubCategory.getSubCatgId())) {
			SubCategory updatedSubCategory = subCatgRepo.save(detachedSubCategory);
			updatedSubCategory.setAdmin(admin);
			return subCatgRepo.save(updatedSubCategory);
		}
		throw new ResourceNotFoundException(
				"Invalid SubCategory ID : Updation Failed !!!!!!!!!" + detachedSubCategory.getSubCatgId());
	}

	@Override
	public String deleteSubCategoryById(long subCategoryId) {
		subCatgRepo.deleteBySubCatgId(subCategoryId);
		return "SubCategory details deleted for category id " + subCategoryId;
	}

	@Override
	public void updateSubCategoryQuantity(int subCatgId, double orderedItemQuantity) {
		SubCategory subCategory= subCatgRepo.findBySubCatgId(subCatgId);
		double newCategoryStock=subCategory.getTotalStock() - orderedItemQuantity;
		subCatgRepo.updateSubCategoryQuantity(subCatgId,newCategoryStock);
		
	}
	
	@Override
	public void updateSubCategoryAddQuantity(int subCatgId, double orderedItemQuantity) {
		SubCategory subCategory= subCatgRepo.findBySubCatgId(subCatgId);
		//add quantity in subcategory
		double newCategoryStock=subCategory.getTotalStock() + orderedItemQuantity;
		subCatgRepo.updateSubCategoryQuantity(subCatgId,newCategoryStock);
	}
}
