package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartDto {
	
	private long cartId;
	
	private Long customerId;
	
	private int orderItemsQuantity;

	public CartDto(long cartId, Long customerId, int orderItemsQuantity) {
		super();
		this.cartId = cartId;
		this.customerId = customerId;
		this.orderItemsQuantity = orderItemsQuantity;
	}
	
}
