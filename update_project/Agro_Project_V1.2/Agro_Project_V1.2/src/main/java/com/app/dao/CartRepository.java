package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	boolean existsByCartId(long cartId);
	//Long cartId, double cartTotalPrice, int orderItemsQuantity
	@Query("select new com.app.entities.Cart(c.cartId,c.cartTotalPrice,c.orderItemsQuantity) from Cart c where c.cartId= ?1 ")
	Cart findByCartId(Long cartId);
	
	@Query("select new com.app.entities.Cart(c.cartId,c.cartTotalPrice,c.orderItemsQuantity,c.customer) from Cart c where c.cartId= ?1")
	Cart findByCartIdWithCustomer(long cartId);
	
}
 