package com.app.service;

import java.util.List;
import java.util.Set;

import com.app.dto.AddToCart;
import com.app.entities.OrderItem;

public interface IOrderItemService {
	
	/*
	 * OrderItem createOrderItem(AddToCart addToCart);
	 * 
	 * OrderItem addCartId(Cart cart, OrderItem orderItem);
	 */
	public OrderItem addToOrderItem(AddToCart addToCartDto,Long cartId);

	public OrderItem findOrderItem(Long orderItemId);

	public void updateOrderItemOrderId(Set<OrderItem> orderItems, long orderId);
	
	public List<OrderItem> findOrderItemsByOrderId(Long orderId);

	public void deleteOrderItem(Long orderItemId);
}


