package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.CustomerRepository;
import com.app.dao.ProductRepository;
import com.app.dao.ReviewRepo;
import com.app.dto.ReviewDto;
import com.app.entities.Customer;
import com.app.entities.Product;
import com.app.entities.Review;

@Service
@Transactional
public class ReviewServiceImpl implements IReviewService{
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	ReviewRepo reviewRepo ;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public ReviewDto createReview(ReviewDto reviewDto, Long productId, Long customerId) {
		
		Product product =this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found"));
		
		Customer customer = this.customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("customer not found"));
		
		Review review = this.modelMapper.map(reviewDto, Review.class);
		
		review.setProduct(product);
		review.setCustomer(customer);
		
		Review savedReview = this.reviewRepo.save(review);
			
		return this.modelMapper.map(savedReview, ReviewDto.class);
	}

	@Override
	public void deleteReview(Integer reviewId) {
		
		Review review = this.reviewRepo.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("review not found"));
		
		this.reviewRepo.delete(review);
		
	}

}
