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
import com.app.entities.Product;
import com.app.service.IFarmerService;
import com.app.service.IProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class ProductController {
	@Autowired
	IProductService productService;
	
	@Autowired
	IFarmerService farmerService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addProductDetails(@RequestBody @Valid Product transientproduct,HttpSession session) {
		System.out.println("in add dtls " + transientproduct);
		try {
			long farmerId = (long)session.getAttribute("farmer_dtls");
			return new ResponseEntity<>(productService.addNewProduct(transientproduct, farmerId), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add emp " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<?> deleteProductDetails(@PathVariable long productId) {
		System.out.println("in del product " + productId);
		try {
			return ResponseEntity.ok(new ApiResponse(productService.deleteProduct(productId)));
		} catch (RuntimeException e) {
			System.out.println("err in del  product " + e);
			return new ResponseEntity<>(new ApiResponse("Invalid Product ID !!!"), HttpStatus.NOT_FOUND);																											// id
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateProductDetails(@RequestBody Product detachedProduct,HttpSession session) {

		System.out.println("in update product " + detachedProduct);
		try {
			long farmerId = (long)session.getAttribute("farmer_dtls");
			return ResponseEntity.ok(productService.updateProduct(detachedProduct,farmerId));
		} catch (RuntimeException e) {
			System.out.println("err in update  product " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
}
