package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.Cart;
import com.app.entities.OrderItem;

@Repository

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//Long orderItemId, int productQuantity, double productPrice
	@Query("select new com.app.entities.OrderItem(o.orderItemId,o.productQuantity,o.productPrice) from OrderItem o where o.orderItemId= ?1")
	OrderItem findByOrderItemId(Long orderItemId);

	@Modifying
	@Query("update OrderItem o set o.productQuantity=?1,o.productPrice=?2,o.cart=?3 where o.orderItemId=?4")
  void addCartIdToProduct(int productQuantity, double productPrice, Cart cart, Long orderItemId);

	
	@Modifying
	@Query("update OrderItem o set o.order= ?2 where orderItemId= ?1")
	void updateOrderId(Long orderItemId,Long orderId);
	
	@Query("select new com.app.entities.OrderItem(o.orderItemId,o.productQuantity,o.productPrice) from OrderItem o where o.order= ?1")
	List<OrderItem> findOrderItemsByOrderId(Long orderId);

	
	void deleteByOrderItemId(Long orderItemId);
	
	//@Query("insert into ")
	/*
	 * @Query("update OrderItem o set (o.productQuantity=?1,o.productPrice=?2,o.cart=?3) where o.orderItemId=?4"
	 * ) int addCartIdToProduct(int productQuantity,double productPrice,Cart cart,
	 * Long orderItemId);
	 */
}
