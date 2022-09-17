package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AddToCart {

	private Long cartId;

	private int orderQuantity;

	private Long customerId;

	private int orderItemsQuantity;
	
	private int productId;
	
	private int productQuantity;
	
	private double productPrice;


}
