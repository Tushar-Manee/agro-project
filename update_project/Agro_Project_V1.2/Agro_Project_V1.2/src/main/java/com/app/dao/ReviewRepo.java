package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

}
