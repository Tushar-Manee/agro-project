package com.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.OrderDto;
import com.app.entities.Cart;
import com.app.entities.Customer;
import com.app.entities.Order;
import com.app.service.ICustomerService;
import com.app.service.IOrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

	@Autowired
	IOrderService orderService;

	@Autowired
	ICustomerService customerService;

	@PostMapping("/place")
	public ResponseEntity<?> placeOrder(@RequestBody OrderDto orderDto, HttpSession session) {
		System.out.println("in placeorder" + orderDto.getOrderItemsIdArray().toString());
		try {
			Order order = orderService.placeOrder(orderDto);
			// after placing order it should be reflected in farmer's view of orders as he
			// get order for his product
			// now we have to change order id's of the order item table in same transaction
			// ---done

			// make changes in product quantity in product table in same transaction

			// changes to quantity in subcategory

			return new ResponseEntity<>(order, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add order " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
																									// clnt
		}
	}

	@PostMapping("/list")
	public ResponseEntity<?> getAllOrders(@RequestBody long customerId) {
		try {
			Customer customer = customerService.findCustomerById(customerId);
			Cart cart = customer.getCart();
			List<Order> listOfOrders = orderService.getAllOrders(cart.getCartId());

			return new ResponseEntity<>(listOfOrders, HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add order " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
																									// clnt
		}

	}
	
	@PostMapping("/cancel")
	// this method for deleting wrong order
	public ResponseEntity<?> cancelOrder(@RequestBody Long orderId,HttpSession session) {	     
		
		System.out.println("in cancel order with order id"+ orderId);
	
		try {
			  
			orderService.cancelOrder(orderId);
			return new ResponseEntity<>("order cancelled", HttpStatus.CREATED);
		} catch (RuntimeException e) {
			System.out.println("err in add order " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
																									// clnt
		}
	}
}
