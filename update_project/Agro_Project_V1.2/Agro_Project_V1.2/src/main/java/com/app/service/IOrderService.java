package com.app.service;

import java.util.List;

import com.app.dto.OrderDto;
import com.app.entities.Order;

public interface IOrderService {

	   Order placeOrder(OrderDto orderDto);
	   
	   List<Order> getAllOrders(long cartId);
	   
	   void cancelOrder(Long orderId);
}
