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
import com.app.entities.Category;
import com.app.service.ICategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CategoryController {
	@Autowired
	private ICategoryService categService;

	
	@PostMapping("/create")
	public ResponseEntity<?> createNewCategory(@RequestBody @Valid Category transientCategory,HttpSession session) {
		System.out.println("in add dtls " + transientCategory);
		try {
			long adminId = (long) session.getAttribute("admin_dtls");
			System.out.println("in controoler admin id " + adminId);
			return new ResponseEntity<>(categService.createCategory(transientCategory,adminId) , HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in create catg " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCategoryDetails(@RequestBody Category detachedCategory,HttpSession session) {

		System.out.println("in update category " + detachedCategory);
		try {
			long adminId = (long) session.getAttribute("admin_dtls");
			return ResponseEntity.ok(categService.updateCategory(detachedCategory, adminId));
		} catch (RuntimeException e) {
			System.out.println("err in update  category " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<?> deleteCategoryDetails(@PathVariable long categoryId) {
		System.out.println("in del category " + categoryId);
		try {
			return ResponseEntity.ok(new ApiResponse(categService.deleteCategory(categoryId)));
		} catch (RuntimeException e) {
			System.out.println("err in del  category " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Category ID !!!"), HttpStatus.NOT_FOUND);																											// id
		}
	}
}
