package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.ICartService;



@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
	
	@Autowired
	ICartService cartService;
//	@PostMapping("/add")
//    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
//                                                 @RequestParam("token") String token) throws AuthenticationFailException, ProductNotExistException {
//        authenticationService.authenticate(token);
//        User user = authenticationService.getUser(token);
//        Product product = productService.getProductById(addToCartDto.getProductId());
//        System.out.println("product to add"+  product.getName());
//        cartService.addToCart(addToCartDto, product, user);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
//
//    }
	/*@PostMapping("/add")
      public ResponseEntity<?> addToCart(@RequestBody AddToCart addToCartDto,HttpSession session){
		System.out.println("in add dtls " + addToCartDto );
		try {
		    	Cart cart =cartService.addToCart(addToCartDto);
		    	session.setAttribute("cart_id",cart.getCartId() );
		    	
		    	return new ResponseEntity<>("product added to cart", HttpStatus.CREATED);
		} 
		
		catch (RuntimeException e) {
			System.out.println("err in add to  cart " + e);
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);// => invalid data from
																									// clnt
		}	}*/
	
	
	
	
	
	
	
	
	
}
