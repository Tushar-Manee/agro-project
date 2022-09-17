package com.app.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.SubCategoryRequest;
import com.app.entities.SubCategory;
import com.app.service.ISubCategoryService;
@RestController
@RequestMapping("/subcategory")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class SubCategoryController {
@Autowired
private ISubCategoryService subCatgService;

//this method's access should be given to ADMIN and transientSubCategory will include categoryId 
@PostMapping("/create")
public ResponseEntity<?> createNewSubCategory(@RequestBody @Valid SubCategoryRequest subCategoryRequest,HttpSession session) {
	System.out.println("in add dtls " + subCategoryRequest);
	try {
		long adminId = (long) session.getAttribute("admin_dtls");
		return new ResponseEntity<>(subCatgService.createSubCategory(subCategoryRequest,adminId) , HttpStatus.CREATED);
	} catch (RuntimeException e) {
		System.out.println("err in create subcatg " + e);
		return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}

@PutMapping("/update")
public ResponseEntity<?> updateCategoryDetails(@RequestBody SubCategory transientSubCategory ,HttpSession session) {

	System.out.println("in update subcategory " + transientSubCategory);
	try {
		long adminId = (long) session.getAttribute("admin_dtls");
		return ResponseEntity.ok(subCatgService.updateSubCategory(transientSubCategory,adminId));
	} catch (RuntimeException e) {
		System.out.println("err in update  subcategory " + e);
		return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
	}
}

@DeleteMapping("/delete/{categoryId}")
public ResponseEntity<?> deleteSubCategoryDetails(@PathVariable long subCategoryId) {
	System.out.println("in del subcategory " + subCategoryId);
	try {
		return ResponseEntity.ok(new ApiResponse(subCatgService.deleteSubCategoryById(subCategoryId)));
	} catch (RuntimeException e) {
		System.out.println("err in del  subcategory " + e);
		return new ResponseEntity<>(new ApiResponse("Invalid Category ID !!!"), HttpStatus.NOT_FOUND);																											// id
	}
   }



}
