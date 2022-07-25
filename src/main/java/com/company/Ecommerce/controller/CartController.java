package com.company.Ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.Ecommerce.commons.APIResponse;
import com.company.Ecommerce.dto.cart.AddToCartDto;
import com.company.Ecommerce.dto.cart.CartDto;
import com.company.Ecommerce.model.User;
import com.company.Ecommerce.service.AuthenticationService;
import com.company.Ecommerce.service.CartService;

@RestController
@RequestMapping("v1/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	//post cart
	@PostMapping("/add-to-cart")
	public ResponseEntity<APIResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
												@RequestParam("token") String token){
		authenticationService.authenticateToken(token);
		User user = authenticationService.getUser(token);
//		Product product = productService.findById(addToCartDto.getProductId());
//        System.out.println("product to add"+product.getProductName());
//        cartService.addToCart(addToCartDto, product, user);
		System.out.println(addToCartDto.toString());
		cartService.addToCart(addToCartDto, user);
		return new ResponseEntity<>(new APIResponse(true, "added to cart"), HttpStatus.CREATED);
	}
	
	//get cart item
	@GetMapping("/get-from-cart")
	public ResponseEntity<CartDto> getFromCart(@RequestParam("token") String token){
		authenticationService.authenticateToken(token);
		User user = authenticationService.getUser(token);
		CartDto cartDto = cartService.listCartItems(user);
		return new ResponseEntity<>(cartDto, HttpStatus.OK);
	}
	
	//delete cart item
	@DeleteMapping("/remove-form-cart/{cartItemId}")
	public ResponseEntity<APIResponse> deleteFromCart(@PathVariable("cartItemId") int itemId,
													@RequestParam("token") String token){
		authenticationService.authenticateToken(token);
		User user = authenticationService.getUser(token);
		cartService.deleteCartItem(itemId, user);
		
		return new ResponseEntity<>(new APIResponse(true, "deleted from cart"), HttpStatus.OK);
	}
}
