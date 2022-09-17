package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReviewDto {
	
	private int reviewId;
	
	private String reviewContent;

	public ReviewDto(int reviewId, String reviewContent) {
		super();
		this.reviewId = reviewId;
		this.reviewContent = reviewContent;
	}
	
}
