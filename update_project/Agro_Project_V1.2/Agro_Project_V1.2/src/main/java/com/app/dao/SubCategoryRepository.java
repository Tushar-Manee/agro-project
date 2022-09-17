package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.SubCategory;
@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
	
	boolean existsBySubCatgId(long subCatgId );

	void deleteBySubCatgId(long subCategoryId);
	
	@Modifying
	@Query("update SubCategory s set s.totalStock=?2 where s.subCatgId=?1")
	void updateSubCategoryQuantity(long subCatgId, double newSubcategoryStock);
	
	//int subCatgId, String subCatgName, String description, double totalStock
	@Query("select new com.app.entities.SubCategory(s.subCatgId,s.subCatgName,s.description,s.totalStock) from SubCategory s where subCatgId= ?2 ")
	SubCategory findBySubCatgId(int subCatgId);
	
}
