package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	/*int orderId, double orderTotalPrice, double orderItemCount, LocalDateTime orderTime,
	Set<OrderItem> orderItems, Cart cart, Admin admin, OrderStatus orderStatus*/
	
	//any changes in query will have to be reflected in constructor
	@Query("select new com.app.entities.Order(o.orderId,o.orderTotalPrice,o.orderItemCount,o.orderTime,o.orderItems,o.orderStatus) from Order o where o.orderId= ?1")
	Order findOrderById(Long orderId);
	
	
	@Query("select new com.app.entities.Order(o.orderId,o.orderTotalPrice,o.orderItemCount,o.orderTime,o.orderItems,o.orderStatus) from Order o where o.cart= ?1")
	List<Order> findAllOrderByCartId(long cartId);
	
	void deleteByOrderId(Long orderId);

}
