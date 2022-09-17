package com.app.service;

import com.app.dto.ReviewDto;

public interface IReviewService {
	
	ReviewDto createReview(ReviewDto reviewDto , Long productId, Long customerId);
	
	void deleteReview(Integer reviewId);

}
