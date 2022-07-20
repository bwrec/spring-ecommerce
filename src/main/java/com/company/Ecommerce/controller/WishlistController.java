package com.company.Ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.Ecommerce.commons.APIResponse;
import com.company.Ecommerce.dto.ProductDto;
import com.company.Ecommerce.model.Product;
import com.company.Ecommerce.model.User;
import com.company.Ecommerce.model.Wishlist;
import com.company.Ecommerce.service.AuthenticationService;
import com.company.Ecommerce.service.WishlistService;

@RestController
@RequestMapping("/v1/wishlist")
public class WishlistController {
	
	@Autowired
	WishlistService wishlistService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	//post product in wishlist
	@PostMapping("/add")
	public ResponseEntity<APIResponse> addToWishlist(@RequestParam("token") String token, @RequestBody Product product){
		//authenticate token
		authenticationService.authenticateToken(token);
		
		//find user
		User user = authenticationService.getUser(token);
		
		//save item in wishlist
		Wishlist wishlist = new Wishlist(user, product);
		wishlistService.createWishlist(wishlist);
		
		return new ResponseEntity<>(new APIResponse(true, "added to wishlist"), HttpStatus.CREATED);
	}
	
	//get all wishlist items
	@GetMapping("/get-wishlist/{token}")
	public ResponseEntity<List<ProductDto>> getWishlist(@PathVariable("token") String token){
		authenticationService.authenticateToken(token);
		User user = authenticationService.getUser(token);
		List<ProductDto> wishlist =  wishlistService.getWishlistForUser(user);
		return new ResponseEntity<>(wishlist, HttpStatus.OK);
	}
	
}
