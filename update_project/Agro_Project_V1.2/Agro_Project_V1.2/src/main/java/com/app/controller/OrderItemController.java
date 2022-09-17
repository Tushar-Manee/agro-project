package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AddToCart;
import com.app.dto.ApiResponse;
import com.app.entities.Cart;
import com.app.entities.Customer;
import com.app.entities.OrderItem;
import com.app.service.CartServiceImpl;
import com.app.service.CustomerServiceImpl;
import com.app.service.OrderItemServiceImpl;
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderItemController {
	
   @Autowired 
   OrderItemServiceImpl orderItemService;
   
   @Autowired
   CartServiceImpl cartService;
   
   @Autowired
   CustomerServiceImpl customerService;
   
	@PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody AddToCart addToCartDto,HttpSession session){
		System.out.println("in add dtls " + addToCartDto );
		try {
			
			
		   Customer customer =customerService.findCustomerById(addToCartDto.getCustomerId());
		   System.out.println("this is customer"+ customer); 
				   
			if( addToCartDto.getCartId()==null) {
			      Cart cart=cartService.addCartToCustomer(customer);
			     System.out.println("this is new cart"+cart);
			     long  cartId= cart.getCartId();
			      session.setAttribute("cartId",cartId);
			      
			      OrderItem orderItem = orderItemService.addToOrderItem(addToCartDto,cartId); 
			      System.out.println("this is added product in order item "+orderItem);
			      //updating cart
			      cartService.addItemToCart(orderItem,cartId,addToCartDto.getProductPrice(),addToCartDto.getProductQuantity());   
			  	return new ResponseEntity<>("product added to cart by creating new cart", HttpStatus.CREATED);
			     }
			else {
		                  
		    	OrderItem orderItem = orderItemService.addToOrderItem(addToCartDto,(Long)addToCartDto.getCartId());
		    	           
		    	cartService.addItemToCart(orderItem,addToCartDto.getCartId(),addToCartDto.getProductPrice(),addToCartDto.getProductQuantity());
		    	
		    	return new ResponseEntity<>("product added to cart", HttpStatus.CREATED);
			}
		} 
		
		catch (RuntimeException e) {
			System.out.println("err in add to  cart " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
																									// clnt
		}
	
	
	}
}
