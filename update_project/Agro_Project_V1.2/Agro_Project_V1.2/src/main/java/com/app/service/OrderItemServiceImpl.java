package com.app.service;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CartRepository;
import com.app.dao.OrderItemRepository;
import com.app.dto.AddToCart;
import com.app.entities.Cart;
import com.app.entities.OrderItem;

@Service
@Transactional
public class OrderItemServiceImpl implements IOrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private IProductService productServiceImpl;
	
	
 
	
	/*
	 * @Override public OrderItem createOrderItem(AddToCart addToCartDto) {
	 * 
	 * System.out.println("in orderitem service creating item");
	 * System.out.println("2--------------------------"); OrderItem orderItem =
	 * mapper.map(addToCartDto, OrderItem.class);
	 * System.out.println("--------------------------");
	 * 
	 * System.out.
	 * println("in orderitem service mapped item after this insert querry must be fired "
	 * + orderItem); System.out.println("--------------------------"); //return
	 * orderItemRepo.save(orderItem); return orderItem; }
	 * 
	 * 
	 * @Override public OrderItem addCartId(Cart cart, OrderItem orderItem) {
	 * System.out.println("in order item add cart id 1"); // OrderItem
	 * orderItemPersistant=orderItemRepo.findByOrderItemId(orderItem.getOrderItemId(
	 * )); System.out.println("the order item before setting cart" +orderItem);
	 * System.out.println("--------------------------"); System.out.println("2");
	 * orderItem.setCart(cart); System.out.println("--------------------------");
	 * System.out.println("--------------------------");
	 * System.out.println("the order item  is" +orderItem);
	 * 
	 * orderItemRepo.save(orderItem); //System.out.println(
	 * orderItemRepo.addCartIdToProduct(orderItem.getProductQuantity(),orderItem.
	 * getProductPrice(), cart, orderItem.getOrderItemId())); // return null; return
	 * null; }
	 */
	public OrderItem addToOrderItem(AddToCart addToCartDto,Long cartId) {

		System.out.println("in order item service creating item");
		  System.out.println("2--------------------------");
		OrderItem orderItem = mapper.map(addToCartDto, OrderItem.class);
		System.out.println("--------------------------");
		Cart cart=cartRepo.findByCartId(cartId);
		orderItem.setCart(cart);
		
		System.out.println(" adding product to item");
		
		orderItem.setProduct(productServiceImpl.findProductByProductId(addToCartDto.getProductId()));
		
		
		System.out.println("in orderitem service mapped item after this insert querry must be fired " + orderItem);
		System.out.println("--------------------------");
		
		return orderItemRepo.save(orderItem);
	}

	public OrderItem findOrderItem(Long orderItemId) {
		
		return orderItemRepo.findByOrderItemId(orderItemId);
	}

	//this is called by order service
	
	//transaction propagated here so we add method to change product quantity 
	@Override
	public void updateOrderItemOrderId(Set<OrderItem> orderItems, long orderId) {
		
		//updating order id  of every orderItem
		for (OrderItem orderItem : orderItems ) {
			System.out.println("updating each order items order id in order service");
			orderItemRepo.updateOrderId(orderItem.getOrderItemId(),orderId); 
			//order item has product id
		    productServiceImpl.updateProductQuantity(orderItem);
		}
	        	
	}
	
	@Override
	public List<OrderItem> findOrderItemsByOrderId(Long orderId) {
        
		return orderItemRepo.findOrderItemsByOrderId(orderId);
	}

	@Override
	public void deleteOrderItem(Long orderItemId) {
		//before deleting item we have to add corresponding quantity to product 
	    OrderItem orderItem=orderItemRepo.findByOrderItemId(orderItemId);
	   
		productServiceImpl.updateProductAddQuantity(orderItem);
		orderItemRepo.deleteByOrderItemId(orderItemId);
		
	}


	
}
