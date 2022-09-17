package com.app.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.OrderRepository;
import com.app.dto.OrderDto;
import com.app.entities.Cart;
import com.app.entities.Order;
import com.app.entities.OrderItem;
import com.app.entities.OrderStatus;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Autowired
	IOrderItemService orderItemService;

	@Autowired
	CartServiceImpl cartService;

	@Autowired
	OrderRepository orderRepo;

	@Override
	public Order placeOrder(OrderDto orderDto) {
		// preparing list of items to be added in order
		Set<OrderItem> orderItems = new HashSet<>();

		// calculating order price
		Double orderPrice = 0.0;

		for (Long orderItemId : orderDto.getOrderItemsIdArray()) {
			OrderItem orderItem = orderItemService.findOrderItem(orderItemId);
			orderItems.add(orderItem);
			orderPrice = orderPrice + (orderItem.getProductPrice() * orderItem.getProductQuantity());
		}
		Long orderItemcount = (long) orderDto.getOrderItemsIdArray().size();

		LocalDateTime orderTime = LocalDateTime.now();

		Long cartId = orderDto.getCartId();
		Cart cart = cartService.findCartbyCartId(cartId);

		OrderStatus orderStatus = OrderStatus.ACCEPTED;
		// empty order object created to add in database
		Order order = new Order();
		// setting orderItems to order
		order.setOrderItems(orderItems);
		order.setCart(cart);
		order.setOrderItemCount(orderItemcount);
		order.setOrderStatus(orderStatus);
		order.setOrderTime(orderTime);
		order.setOrderTotalPrice(orderPrice);
		order = orderRepo.save(order);
		// now we have to change order ids of the order item table in same transaction
		updateOrderItem(orderItems, order.getOrderId());
		// make changes in products quantity in product table in same transaction for
		// each order item
		// as transaction has propagated till orderItemservice so
		// see order item service
		return order;
	}

	public void updateOrderItem(Set<OrderItem> orderItems, long orderId) {

		orderItemService.updateOrderItemOrderId(orderItems, orderId);

	}

	@Override
	public List<Order> getAllOrders(long cartId) {
		
		return 	orderRepo.findAllOrderByCartId(cartId);
	}
	
	@Override
	public void cancelOrder(Long orderId) {
		
	      
	         List<OrderItem> orderItems = orderItemService.findOrderItemsByOrderId(orderId);
		     
	         for(OrderItem orderItem : orderItems ) {
	        	 
	         orderItemService.deleteOrderItem(orderItem.getOrderItemId());
	         }
             orderRepo.deleteByOrderId(orderId);	
	}

}
