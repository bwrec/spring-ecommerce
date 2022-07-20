package com.company.Ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.Ecommerce.dto.ProductDto;
import com.company.Ecommerce.model.User;
import com.company.Ecommerce.model.Wishlist;
import com.company.Ecommerce.repository.WishlistRepository;

@Service
public class WishlistService {

	@Autowired
	WishlistRepository wishlistRepository;

	@Autowired
	ProductService productService;
	
	public void createWishlist(Wishlist wishlist) {
		wishlistRepository.save(wishlist);
	}

	public List<ProductDto> getWishlistForUser(User user) {
		List<Wishlist> wishlist = wishlistRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<ProductDto> productDtoList = new ArrayList<>();
		for(Wishlist w:wishlist) {
			productDtoList.add(productService.productToDto(w.getProduct()));
		}
		return productDtoList;
	}
}
