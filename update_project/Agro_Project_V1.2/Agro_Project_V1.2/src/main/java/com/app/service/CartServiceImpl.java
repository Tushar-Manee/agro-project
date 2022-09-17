package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CartRepository;
import com.app.dao.OrderItemRepository;
import com.app.entities.Cart;
import com.app.entities.Customer;
import com.app.entities.OrderItem;

@Service
@Transactional
public class CartServiceImpl implements ICartService {
	@Autowired
	private IOrderItemService orderItemService;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	ICustomerService customerService;

	@Autowired
	private OrderItemRepository orderItemRepo;

	private double totalCartPrice;

	private int orderItemsQuantity;

	/*
	 * @Override public Cart addToCart(AddToCart addToCart) {
	 * 
	 * System.out.println("in cart service ");
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * if (addToCart.getCartId()!=null) { //this may give lazy init exception we
	 * have to write constructor
	 * 
	 * Cart cart = cartRepo.findByCartId(addToCart.getCartId());
	 * 
	 * OrderItem orderItem =orderItemService.createOrderItem(addToCart);
	 * System.out.println("in cart service after order item "+orderItem);
	 * 
	 * System.out.println("in cart service cart already exist with cart details"+
	 * cart); totalCartPrice= cart.getCartTotalPrice()+
	 * (orderItem.getProductQuantity() * orderItem.getProductPrice()); // this will
	 * set cart total price cart.setCartTotalPrice(totalCartPrice);
	 * 
	 * orderItemsQuantity=cart.getOrderItemsQuantity()+addToCart.
	 * getOrderItemsQuantity(); // this will set cart total order items quantity
	 * cart.setOrderItemsQuantity(orderItemsQuantity);
	 * 
	 * List<OrderItem> orderItems= new ArrayList<OrderItem> ();
	 * orderItems.add(orderItem); cart.setOrderItems(orderItems);
	 * orderItemService.addCartId(cart,orderItem); return cart; } else {
	 * 
	 * OrderItem orderItem =orderItemService.createOrderItem(addToCart);
	 * System.out.println("in cart service after order item "+orderItem);
	 * totalCartPrice = (orderItem.getProductQuantity() *
	 * orderItem.getProductPrice());
	 * 
	 * orderItemsQuantity += addToCart.getOrderItemsQuantity();
	 * 
	 * System.out.println("in cart service creating new cart"); Cart cart = new
	 * Cart(); //customer,totalCartPrice,orderItemsQuantity
	 * System.out.println("customer id is" +addToCart.getCustomerId()); Customer
	 * customer = customerService.findCustomerById(addToCart.getCustomerId());
	 * System.out.println("in cart service after customer"+customer);
	 * cart.setCustomer(customer); cart.setCartTotalPrice(totalCartPrice);
	 * cart.setOrderItemsQuantity(orderItemsQuantity); cart=cartRepo.save(cart);
	 * System.out.println("new cart details"+ cart);
	 * 
	 * System.out.println("cart saved "+ cart);
	 * System.out.println("----------------------------------");
	 * 
	 * orderItem.setCart(cart); orderItemRepo.save(orderItem);
	 * 
	 * //orderItemService.addCartId(cart,orderItem);
	 * 
	 * return cart; }
	 * 
	 * 
	 * }
	 */

//newcode

	public void addItemToCart(OrderItem orderItem, long cartId,double productPrice,int productQuantity) {
		Cart cart=cartRepo.findByCartIdWithCustomer(cartId);
		System.out.println("cart  ---------"+cart);
		
		
		System.out.println("cart  ---------updating cart total price");
		double newCartTotalPrice= cart.getCartTotalPrice()+( productPrice*productQuantity);
		cart.setCartTotalPrice(newCartTotalPrice);
		
		System.out.println("updating orderitem quantity");
	    int newOrderItemsQuantity=cart.getOrderItemsQuantity()+1;
		cart.setOrderItemsQuantity(newOrderItemsQuantity);
		
	    System.out.println("adding order item to item list in cart");
		List<OrderItem> orderItems = new ArrayList<>();
		orderItems.add(orderItem);
		
		
		System.out.println("saving the list of orderItem in cart");
		cart.setOrderItems(orderItems);
		cartRepo.save(cart);

	}

	public Cart addCartToCustomer(Customer customer) {

		Cart cart = new Cart();
		cart.setCustomer(customer);

		return cartRepo.save(cart);
	}

	public Cart findCartbyCartId(Long cartId) {
		return cartRepo.findByCartId(cartId);
	}
}
