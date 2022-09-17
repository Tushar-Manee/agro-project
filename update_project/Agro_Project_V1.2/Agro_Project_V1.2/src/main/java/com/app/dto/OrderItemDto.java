package com.app.dto;

import com.app.entities.Product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderItemDto {

	private Product product;
	private int productQuantity;
	private double productPrice;
	
	public OrderItemDto(Product product, int productQuantity, double productPrice) {
		super();
		this.product = product;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
	}
	
}
