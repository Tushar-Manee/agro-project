package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.ReviewDto;
import com.app.service.IReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	IReviewService reviewService ; 
	
	@PostMapping("customer/{customerId}/product/{productId}/reviews")
	public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto , @PathVariable long productId , @PathVariable long customerId ){
		
		ReviewDto createReview = this.reviewService.createReview(reviewDto, productId, customerId);
		
		return new ResponseEntity<ReviewDto>(createReview , HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	public ResponseEntity<ApiResponse> deleteReview(@PathVariable Integer reviewId){
		
		this.reviewService.deleteReview(reviewId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("review deleted successfully"),HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
}
