package com.app.service;

import com.app.entities.OrderItem;

public interface ICartService {


	public void addItemToCart(OrderItem orderItem, long cartId,double productPrice,int productQuantity);
	
	
}
